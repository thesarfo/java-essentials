
## Handy Notes - Sign in and Sign Up with JWT
When you create a user class, and you want to use that user as the user of your application, you have to implement the UserDetails interface, and the Principal interface as well. And then you need to implement the methods for both the Principal and UserDetails, for the Principal you only need getName(), but for UserDetails you have to implement all the methods under it(Intellij has a shortcut for that).

The getName for our principal, simply refers to the unique identifier of our user, so we can set it to email, and thus make our getName() method return email.

Below is how we will set up our user Entity
```java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean accountLocked;
    
    private boolean enabled;

    @CreatedDate
    @Column(nullable = false, updatable = false) // we want to track when our user was created, we use jpa auditing for that
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false) // we want to track when our user was last updated
    private LocalDateTime lastModifiedDate;


    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    private String fullName(){
        return firstname + " " + lastname; // we might need the full name of our user someday, so we create a method for that just in case
    }
}
```

Note that, since we are using JPA auditing on our entity, we need to go to our main application class, and then annotate it with the @EnableJpaAuditing for it to work
```java
@SpringBootApplication
@EnableJpaAuditing
public class BookNetworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookNetworkApiApplication.class, args);
	}

}
```

Now our user entity is almost done, but we need the "Roles" of our user. private List<Role> roles; and for that we need to implement the getAuthorities methods above.

But note that, we want our roles and users to have a many to many relationship. so we need to specify that as well. First of all create a Role.java file
```java
// Role.java
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;
 
    @ManyToMany(mappedBy = "roles") // many to many rlnshp btn roles and users
    @JsonIgnore // ignore users being fetched when we load the roles
    private List<User> users;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
}
```

And now, we need to add a roles column to our user table, and also implement the getAuthorities method. see below
```java
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }
```


After creating the user and role entities, we need to implement the user and role repositories
```java
// role repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String role);

}
```

```java
// user repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
```

And then since we will generate a jwt token, we have to also implement a Token entity and Token repository
```java
// token entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

  @Id
  @GeneratedValue
  public Integer id;

  @Column(unique = true)
  public String token;

  @Enumerated(EnumType.STRING)
  public TokenType tokenType = TokenType.BEARER;

  public boolean revoked;

  public boolean expired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public User user;
}
```

```java
// token repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
}
```

Now we need to set up our Security Configuration
### Security Configuration
```java
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true) // because of role based auth
public class SecurityConfig {

    private JwtFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(
                                "/auth/**",
                                "/v2/api-docs",
                                "/v3-api-docs",
                                "/v3-api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html"
                        ).permitAll()
                                .anyRequest()
                                    .authenticated()
                    )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
```

**Annotations:**

- `@Configuration`: Marks the class as a Spring configuration bean.
- `@EnableWebSecurity`: Enables Spring Security in your application.
- `@RequiredArgsConstructor`: Constructor injection for required dependencies (discussed later).
- `@EnableMethodSecurity(securedEnabled = true)`: Enables method-level security using `@Secured` annotations on controller methods (indicates role-based authorization).

**Properties:**

- `private JwtFilter jwtAuthFilter`: A reference to a `JwtFilter` bean (defined elsewhere) that handles JWT token processing for authentication.
- `private final AuthenticationProvider authenticationProvider`: A final required dependency holding an `AuthenticationProvider` bean (likely custom for JWT authentication) used by Spring Security.

**`securityFilterChain` Bean:**

- This `@Bean` method defines a `SecurityFilterChain` bean, which is the core of your Spring Security configuration. It configures various aspects of security for your application.
- **`http` Block:** Configures web security specifically for HTTP requests.
    - `.cors(withDefaults())`: Enables Cross-Origin Resource Sharing (CORS) with default settings (allowing cross-origin requests).
    - `.csrf(AbstractHttpConfigurer::disable)`: Disables CSRF (Cross-Site Request Forgery) protection since JWT-based authentication typically doesn't require it.
    - `.authorizeHttpRequests(req -> ...)`: Defines authorization rules for different URL patterns:
        - `.requestMatchers(...)`: Permits access to listed paths (login, API docs, Swagger UI) without authentication. These are likely public endpoints.
        - `.anyRequest()`: Any request not explicitly mentioned above requires authentication.
    - `.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))`: Disables session management as JWT is a stateless authentication mechanism.
    - `.authenticationProvider(authenticationProvider)`: Registers the custom `AuthenticationProvider` for JWT authentication.
    - `.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)`: Adds the `JwtFilter` before the default `UsernamePasswordAuthenticationFilter`. This ensures that JWT token processing happens before attempting username/password authentication.


**Note:** This explanation assumes the presence of the `JwtFilter` bean and the custom `AuthenticationProvider` implementation, which are likely defined elsewhere in your codebase.  For a complete understanding, you'd need to see those parts as well.

Now we need to implement our authentication provider as well. we can create a class called BeansConfig, and use that to configure all the beans we will need for our app
### AuthenticationProvider
```java
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
```

**Annotations:**

- `@Configuration`: Marks the class as a Spring configuration bean.
- `@RequiredArgsConstructor`: Constructor injection for required dependencies (discussed later).

**Property:**

- `private final UserDetailsService userDetailsService`: A final required dependency holding a `UserDetailsService` bean (likely implementing user details loading for Spring Security).

**`authenticationProvider` Bean:**

- This `@Bean` method defines an `AuthenticationProvider` bean named `authenticationProvider`. This bean is crucial for Spring Security's authentication process.
- It creates a new `DaoAuthenticationProvider` instance, which is a standard provider for username/password authentication using a UserDetailsService.
- The configuration involves:
    - `.setUserDetailsService(userDetailsService)`: Sets the `userDetailsService` bean to be used by the `DaoAuthenticationProvider` for loading user details during authentication attempts (likely based on username).
    - `.setPasswordEncoder(passwordEncoder())`: Sets the `passwordEncoder` bean to be used for password encoding/decoding during authentication (ensuring secure password storage).

**`passwordEncoder` Bean:**

- This `@Bean` method defines a `PasswordEncoder` bean named `passwordEncoder`. This bean is responsible for securely hashing user passwords before storing them in the database.
- It creates a new `BCryptPasswordEncoder` instance, which is a recommended choice for password hashing due to its strong one-way encryption capabilities.

By combining these beans, Spring Security can perform secure username/password authentication by:

1. Receiving username and password from the login attempt.
2. Using the `UserDetailsService` to retrieve user details for the given username.
3. Comparing the provided password (hashed by the client) with the stored password (hashed in the database using the `passwordEncoder`).
4. Granting access if the credentials match and user is enabled.

And then we implement the our custom UserDetailsService
### User Details Service
```java
import com.thesarfo.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException{
        return repository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
```

And then we implement the jwt filter class
### JWT Filter
```java
@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(

            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
    }

}
```
**Annotations:**

- `@Service`: Marks the class as a Spring service component.
- `@RequiredArgsConstructor`: Constructor injection for required dependencies (discussed later).

**Properties:**

- `private final JwtService jwtService`: A final required dependency holding a `JwtService` bean (likely containing logic for JWT token processing).

**`doFilterInternal` Method:**

- This is the core method of the filter, overridden from `OncePerRequestFilter`. It's invoked for every incoming HTTP request.
- It takes three arguments:
    - `HttpServletRequest`: Provides information about the incoming request.
    - `HttpServletResponse`: Allows manipulating the outgoing response.
    - `FilterChain`: Represents the chain of filters in Spring Security.
- The code performs the following steps:

  1. **Skips Filtering for Login Path (`/api/v1/auth`):**
      - It checks if the request path contains "/api/v1/auth" (likely the login endpoint). If it does, the filter allows the request to proceed without further processing (assuming JWT processing isn't required for login itself).

  2. **Extracts JWT Token from Authorization Header:**
      - If the request path doesn't match the login path, the filter proceeds.
      - It retrieves the `Authorization` header from the request.
      - If the header exists and starts with "Bearer ", it extracts the JWT token portion after "Bearer ".

  3. **Extracts User Email from JWT (if valid):**
      - If a valid JWT token is found, the filter attempts to extract the user email from the token using the injected `jwtService`. This assumes the JWT token encodes the user email claim.

**Functionality Summary:**

This `JwtFilter` acts as a gatekeeper for most API requests (excluding the login path). It checks for a JWT token in the `Authorization` header with the "Bearer " prefix. If a valid token is found, it likely extracts the user email from the token using the `jwtService`. However, the code doesn't show how this extracted email is used for further processing or authorization decisions.

We also need to implement the jwtService class
### JwtService
```java
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }


    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }
    private String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return buildToken(claims, userDetails, jwtExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long jwtExpiration
    ) {
        var authorities = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .claim("authorities", authorities)
                .signWith(getSignInKey())
                .compact();
    }
    
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

Now we can go ahead and finish up the jwtfilter class
### Final JWT Filter
```java
@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;;
    @Override
    protected void doFilterInternal(

            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getServletPath().contains("/api/v1/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if(jwtService.isTokenValid(jwt, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
        }
    }

}
```

The updated `JwtFilter` class you provided incorporates the `userDetailsService` dependency and sets the authenticated user in the `SecurityContext` based on a valid JWT token. Here's a breakdown of the changes and how it works in the context of JWT authentication:

**Dependency and Property:**

- `private final UserDetailsService userDetailsService;`: This additional dependency injects a `UserDetailsService` bean, which is crucial for loading user details by username.

**`doFilterInternal` Method:**

- **Extracting User Email and Checking Security Context:**
    - The code now checks if `userEmail` is not null and the current `SecurityContext` doesn't have an authentication object set. This ensures processing only if a valid username is extracted from the token and authentication hasn't already happened.

- **Loading User Details:**
    - If the conditions above are met, the filter retrieves user details from the database using the injected `userDetailsService.loadUserByUsername(userEmail)`.

- **Validating Token:**
    - It then calls `jwtService.isTokenValid(jwt, userDetails)` to validate the JWT token using the extracted user details. This ensures the token is valid for the specific user.

- **Creating Authentication Token:**
    - If the token is valid, the code creates a `UsernamePasswordAuthenticationToken` object. This object represents the authenticated user with the following details:
        - `userDetails`: The loaded user details object containing username, authorities, etc.
        - `null` for password (as JWT doesn't require sending the password in the request)
        - `userDetails.getAuthorities()`: User's authorities (roles or permissions)

- **Setting Authentication Details:**
    - It sets the authentication details using `new WebAuthenticationDetailsSource().buildDetails(request)`. This typically includes information like IP address and session ID.

- **Setting Authentication in SecurityContext:**
    - Finally, the filter sets the newly created `authToken` object as the authentication object in the `SecurityContext`. This informs Spring Security that the user has been successfully authenticated using the JWT token.

**Overall Functionality:**

This enhanced `JwtFilter` now integrates with the `userDetailsService` to leverage user details stored in the database. It performs the following steps for incoming requests (excluding the login path):

1. Checks for a JWT token in the `Authorization` header.
2. Extracts the username from the token (if valid).
3. Loads user details from the database using the username.
4. Validates the JWT token for the specific user.
5. If valid, creates a `UsernamePasswordAuthenticationToken` object representing the authenticated user.
6. Sets the authentication object in the `SecurityContext`, informing Spring Security about the authenticated user.

By setting the authentication in the `SecurityContext`, subsequent filters and authorization checks in Spring Security can access the user information and make authorization decisions based on the user's roles or permissions (typically retrieved from the `userDetails` object).