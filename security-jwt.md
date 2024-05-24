To begin with, we need to define the security configuration for our application. We will create a new class called SecurityConfigurer.java and add the following code to it.
```java

@Configuration
@EnableWebSecurity
public class SecurityConfigurer {
    
    private final JwtRequestFilter jwtRequestFilter;

    // the below is a hardcoded user
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> new User("foo", new BCryptPasswordEncoder().encode("foo"), new ArrayList<>());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean 
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                        
                        // now we need to make the session stateless, since we will be using jwt. But we need to add a filter to intercept the requests and validate the jwt token before we can make the session stateless. That is where we use the .addFilterBefore() method to add the JwtRequestFilter before the UsernamePasswordAuthenticationFilter which is the default filter that Spring Security uses to authenticate the user.
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
```

Then we customize our user details service to load the user by username. Create a new class called MyUserDetailsService.java and add the following code to it.
```java
@Service
public class MyUserDetailsService implements UserDetailsService
{
    private final PasswordEncoder passwordEncoder;

    public MyUserDetailsService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("foo", passwordEncoder.encode("foo"), new ArrayList<>());
    }
}
```


First you add a jwt package. called jjwt. Add the following dependency to your pom.xml file.
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
```
This package will help you to create and validate existing JWT tokens, it is like a java version of the jwt.io website.


The second dependency will be the jaxb-api package. Add the following dependency to your pom.xml file.
```xml
<dependency>
    <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
    <version>2.3.1</version>
</dependency>
```

Now we go ahead and create a Util class that will help us to abstract all the jwt related stuff. This will allow us to create a jwt from an authentication object, and other stuff like getting a username from jwt, checking if it is expired, validating jwttokens etc. Create a new class called JwtUtil.java and add the following code to it.
```java
// Import necessary libraries
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// Annotate the class with @Service to indicate it's a Spring service component
@Service
public class JwtUtil {

    // Define a secret key for signing the JWTs
    private String SECRET_KEY = "secret";

    // Method to extract the username (subject) from the JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Method to extract the expiration date from the JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic method to extract a specific claim from the JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Method to extract all claims from the JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // Method to check if the JWT has expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Method to generate a new JWT for a specific user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    // Method to create a JWT with specific claims, subject, issued date, expiration date, and signed with the secret key
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // Method to validate the JWT. It checks if the username in the JWT matches the username of the provided user and if the token has not expired
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
```

Now we need to specify the input and output that we will need and will be returned. So we can create a dto like AuthenticationRequest and AuthenticationResponse. Create a new class called AuthenticationRequest.java and add the following code to it.
```java
public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
```

Then create a new class called AuthenticationResponse.java and add the following code to it.
```java
public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
```


Then we need to create a controller which will take the username and password as a request body. A sample controller is shown below.
```java
private final AuthenticationManager authenticationManager; // this is what we will use to authenticate the authentication request in the requestbody)
private final MyUserDetailsService userDetailsService; // this is what we will use to load the user by username

@PostMapping("/login")
public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
    } catch (BadCredentialsException e) {
        throw new Exception("Incorrect username or password", e);
    }
    // if the authentication is successful, we will generate a jwt token
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername()); // extract the user details from the request body based on the username
    final String jwt = jwtUtil.generateToken(userDetails); // generate the jwt token
    
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
    
}
```

Now you can make a post request to the /login endpoint with the username and password in the request body. If the username and password are correct, you will get a jwt token in the response. You can use this token to authenticate future requests to the server.

But before you can use the jwt token to authenticate future requests, you need to add a filter that will intercept the requests and validate the jwt token. Like when there is a Bearer, treat it as a jwt and allow the request. Create a new class called JwtRequestFilter.java and add the following code to it.
```java
    @Component
    public class JwtRequestFilter extends OncePerRequestFilter {
        
        private final MyUserDetailsService userDetailsService;
        private JwtUtil jwtUtil;
    
        // below is the method that will intercept the request and check the Authorization header
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
                throws ServletException, IOException {
            // This filter intercepts each request only once and checks the Authorization header of the request
            final String authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String jwt = null;

            // If the Authorization header is not null and starts with "Bearer ", it treats the rest of the header as a JWT
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // Extract the JWT from the Authorization header
                jwt = authorizationHeader.substring(7);
                // Use the JWT utility to extract the username from the JWT
                username = jwtUtil.extractUsername(jwt);
            }
            // If the JWT is valid and the username exists, it allows the request to proceed
            // If not, it will throw an exception or return an unauthorized error response

            // Check if the username is not null and there is no authentication object in the SecurityContext
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Load the UserDetails object using the username
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                // Validate the JWT using the UserDetails object
                if (jwtUtil.validateToken(jwt, userDetails)) {

                    // Create a UsernamePasswordAuthenticationToken using the UserDetails object and its authorities
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    // Set the details of the authentication object using the request object
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set the Authentication object in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            // Proceed with the filter chain
            chain.doFilter(request, response);
        }
    }
```