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