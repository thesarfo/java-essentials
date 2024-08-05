The main componentts you need to set up for your authorization server to work properly are

1. **The configuration filter for protocol endpoints**: Helps you define configs specific to the authorization server capabilities, including various customizations.

2. **The authentication configuration filter**: Similar to any web application secured with Spring Security, you'll use this filter to define the authentication and authorization configurations and configurations to any other security mechanisms such as CORS and CSRF.

3. **The user details management components**: Like any authentiation process in spring security, these are established through a UserDetailsService bean and a PasswordEncoder.

4. **The client details management**: The authorization server uses a component called `RegisteredClientRepository` to manage the client credentials and other details.

5. **The key-pairs(used to sign and validate tokens) management**: When using non-opaque tokens, the authorization serer uses a private key to sign the tokens. The authorization server also offers access to a public key that the resoure server can use to validate the tokens. The authorizations erver manages the private-public key pairs through a "key source" component.

6. **The general app settings**: A component named `AuthorizationServerSettings` helps you configure generic customizations such as the endpoints the app exposes.


Below is how a simple implementation will look like
```java
@Configuration
@Order(1)
public class SecurityConfig {

    /* This is the first filterchain/config for the oauth2 config */
    @Bean
    @Order(1)
    public SecurityFilterChain authServerFilterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http); /* calling the utility method to apply default configurations for the authorization server endpoints */

        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults()); /* enabling the openid connect protocol */

        http.exceptionHandling(
                e -> e.authenticationEntryPoint(
                        new LoginUrlAuthenticationEntryPoint("/login") /* specify the authentication page for users */
                )
        );

        return http.build();

    }

    /* This will configure authentication and authorization like how you will normally do it for your app */
    @Bean
    @Order(2)
    public SecurityFilterChain appFilterChain(HttpSecurity http) throws Exception{
        http.formLogin(Customizer.withDefaults());

        http
                .authorizeHttpRequests(
                        auth -> auth
                                .anyRequest().authenticated()
                );
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        var u1 = User.withUsername("user")
                .password("password")
                .authorities("read")
                .build();


        return new InMemoryUserDetailsManager(u1);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    /* When using oauth2, you manage client details, and just like how you would have a userdetailsservice for userdetails, you will need a registeredclientrepository for client details */
    @Bean
    public RegisteredClientRepository registeredClientRepository(){
        RegisteredClient r1 = RegisteredClient
                .withId(UUID.randomUUID().toString())
                .clientId("client")
                .clientSecret("secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("https://www.manning.com/authorize")
                .scope(OidcScopes.OPENID)
                .build();

        return new InMemoryRegisteredClientRepository(r1);
    }


    /* If the authorization server uses non-opaque tokens, the authorization server uses private keys to sign the tokens and provies the clients with public keys that can be used to validat the tokens' authenticity. The JWKSource is the object that provides key management for the Spring Security Authorization server */
    @Bean
    public JWKSource<SecurityContext> jwkSource()
            throws NoSuchAlgorithmException {

        KeyPairGenerator keyPairGenerator =
                KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey =
                (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey =
                (RSAPrivateKey) keyPair.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();



        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }


    /* Lastly, you add the generic authorization server settings. WHich will allow you to customize all the endpoints paths that the authorization server exposes */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        return AuthorizationServerSettings
                .builder()
                .build();

    }

}
```

## Running the Authorization Code Grant Type
We expect that by using the registered client details, we'd be able to follow the authorization code flow and get an access token. We'll follow these steps:

1. Check the endpoints that the authorization server exposes
2. Use the authorization endpoint to get an authorization code
3. Use the authorization code to get an access token.


The first step is to find the endpoint paths that the authorization server exposes. This example uses the defaults, which can be found at `http://localhost:8080/.well-known/openid-configuration`

An example of a url where the 'client' can redirect the user to authorize will look like this `http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://springone.io/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256`

Once the user logs in, an authorization code will be generated(which will be in the "code=" side of the browser url). The client can then use this authorization code to request an access token.

the url will look like this `http://localhost:8080/oauth2/token?client_id=userclient&redirect_uri=https://springone.io/authorized&grant_type=authorization_code&code=dWlJMGpGlUAPz0sRU1y8suXDyWejo0_B4-WrLP-ks5kSlcdvlGG-u1OxOORvvpm7IMJaC_lMqzTX2Oh6AKHGOb2J4-Hp6PVPvGjLeUQMnWzz6h3Xyy1D0S6czbiTeU8f&code_verifier=qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI`

The request params are 

1. client_id=client—Needed to identify the client
2.	redirect_uri= `https://www.manning.com/authorized`—The redirect URI through which the authorization server provided the authorization code after the successful user authentication
3.	grant_type=authorization_code—Shows which flow the client uses to request the access token
4.	code=ao2oz47zdM0D5… —The value of the authorization code the authorization server provided to the client
5.	code_verifier=qPsH306-ZDD…—The verifier based on which the challenge that the client sent at authorization was created


## OAUTH2 AUTHORIZATION SERVER BUT WITH A DATABASE
In the above implementation, all our configurations for both the user management and client management were done in memory. But in a real world app, we will need to query/manage them from a database. Because of that, we need a userdetailsservice database implementation, as well as a registered client repository database implementation. Note that we will have to use a real password encoder to encode a user's password, as well as the clients secret.

We will need two tables.
1. users [id, username, password, authority]
2. clients[id, client_id, secret, scope, auth_method, grant_type, redirect_uri]

Now, you should create entities for both tables. see below
```java
// User
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String username;
  private String password;
  private String authority;

// getters and setters
}
```

```java
// Client
@Entity
@Table(name = "clients")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String clientId;
  private String secret;
  private String redirectUri;
  private String scope;
  private String authMethod;
  private String grantType;

  // getters and setters
```

Of course, you will need repositories for them as well. Note that since we will be implementing our own userrepository, it requires that we be able to find our user by their username(that is the default method in the userdetailsservice).
```java
// UserRepository
public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM User u WHERE u.username = :username")
  Optional<User> findByUsername(String username);
}
```

And then we need our client repository as well, just like the userrepository, the client repository requires that we be able to find our client by their client id. so we will have to override that implementation as well.
```java
public interface ClientRepository extends JpaRepository<Client, Integer> {

  @Query("SELECT c FROM Client c WHERE c.clientId = :clientId")
  Optional<Client> findByClientId(String clientId);
}
```

Now we need to implement our custom userdetailsservice. This will implement the default userdetailsservice and loadUserByUsername.
```java
// CustomUserDetailsService
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(SecurityUser::new).
                orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
```

Note that the loadUserByUsername method returns a UserDetails object, and because of that, we also need to provide an implementation of the UserDetails. The most common way of doing it is to create a UserDetails wrapper around our User entity. We will call that Security User

```java
// SecurityUser
public class SecurityUser implements UserDetails {

  private final User user;

  public SecurityUser(User user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(user.getAuthority()));   // () -> user.getAuthority()
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
```

Now that we have created our own customuserdetailsservice bean, we can delete the inmemoryuserdetailsservice bean in our security config.

Just like we overrode our own CustomUserDetailsService, we have to do that for the client as well. We can create a class called CustomClientService where we can override the default implementation and perhaps make it read from a db.

```java
// Custom Client Service
@Service
@Transactional
public class CustomClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    public CustomClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        clientRepository.save(Client.from(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id) {
        var client =
                clientRepository.findById(Integer.valueOf(id))
                        .orElseThrow();
        return Client.from(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        var client =
                clientRepository.findByClientId(clientId)
                        .orElseThrow();
        return Client.from(client);
    }
}
```

Then you can setup your datasource connections in the application.properties.

# In Conclusion
The Spring Security authorization server framework helps you build a custom OAuth 2/OpenID Connect authorization server from scratch. Since the authorization server manages the user and client details, you must implement components defining how the app collects this data:

1. To manage the user details, the authorization server needs a similar Spring Security component like any other web app: an implementation of a **UserDetailsService**.

2. To manage the client details, the authorization server provides another contract you must implement: the **RegisteredClientRepository**

You can register clients that use various authentication flows (grant types). Preferably, the same client shouldn’t use both user-dependent (like the authorization code grant type) and user-independent (like client credentials grant type) flows.

When using non-opaque tokens (usually JWTs), you must also configure a component to manage the key pairs the authorization server uses to sign the tokens. This component is named the **JWKSource**.

When using opaque tokens (tokens that don’t contain data), the resource server must use the **introspection endpoint** to verify a token’s validity and collect data necessary for authorization.

Sometimes, you’d need a way to invalidate already issued tokens. The authorization server offers the revocation endpoint for this capability. When using revocation, the resource server must always introspect the tokens (even non-opaque
ones) to verify their validity
