## Spring Security

Spring Security is a powerful framework for implementing robust security features in Spring-based applications. It offers tools and mechanisms to address various security challenges, helping developers protect their applications and users from common web exploits.

1. **Authentication**: Authentication is the process of proving a user's identity to the system. This typically involves providing credentials, such as a username and password, to gain access. Spring Security handles authentication by creating authentication sessions using session tokens stored in cookies, providing efficient and secure authentication mechanisms.

2. **Authorization**: Authorization occurs after authentication and determines the permissions granted to authenticated users. Spring Security implements role-based access control (RBAC), where users are assigned roles defining their permissions. This allows for granular control over which users can perform specific actions.

3. **Security Policies**:
   - **Same Origin Policy (SOP)**: Ensures that scripts in a web page can only make requests to the same origin (URI) as the web page itself, preventing unauthorized access to sensitive resources.
   - **Cross-Origin Resource Sharing (CORS)**: Allows servers to explicitly define which origins are permitted to access resources, relaxing SOP restrictions for legitimate cross-origin requests.
4. **Common Web Exploits**:
   - **Cross-Site Request Forgery (CSRF)**: Exploits the user's authenticated session to perform unauthorized actions on a website. Spring Security provides built-in support for CSRF protection using CSRF tokens.
   - **Cross-Site Scripting (XSS)**: Injects malicious scripts into web pages, allowing attackers to execute arbitrary code on the client or server. Proper data validation and escaping are crucial for mitigating XSS vulnerabilities.
5. **Principal**: The principal is the person you've identified through the process of authentication. In other words, it is the currently logged-in user


### How does Authorization Happen
How does the application decide whether to allow a particular access. It has to be specified before hand, a bunch of permissions need to be laid down for each user before that user actually accesses the application. This is called "Granted Authority". The user is trying to do something in our application, and we only allow them to do so only if the user has been Granted that Authority to do what they want to do.

1. **Role**: A role is a group of authorities you give to each user. 

# IMPLEMENTING SPRING SECURITY
You need to add the spring security dependency to your pom.xml or build.gradle

The dependency you need for spring security is "spring-boot-starter-security".
 
As soon as you add the dependency to your application, Spring Security starts taking effect. You will be prompted to sign in when you try to visit any endpoint in your application. What is the sign-in form that appears? and which user do you use to sign in?

How does spring security automatically demand a sign in to any endpoint, just by adding it to our dependencies. It does it by using something called filters. Filters are one of the core concepts associated with servlet technologies. We know that servlets are what implement the functionality in our application, like when a user sends a request to do something, that request is handled by a servlet. So, a filter stands between the user and the servlet. A filter intercepts every request, and it gives you the opportunity to do something with every request. Servlets are mapped to a url, but filters are mapped to all urls. So what spring security is doing is that it has placed a filter between the user and the application, and uses that filter to either allow or deny requests. Below are the bunch of things that Spring Security does by default, just by adding the dependency.

1. Adds mandatory authentication for URLs
2. Adds login form ("/login" route)
3. Handles login error
4. Creates a user and sets a default password

Now, how do we know the credentials of the default user that Spring Security has generated for us, when you start the server, and you check the console logs...you will see something like this
```bash
Using generated security password: 664d498c-d86b-451f-8b34-c1cc44f1071b
```
This is going to be the password you will use. The username is just "user". Now when you provide these credentials in the browser, you will be able to access the protected endpoints.

Now, you can customize these default credentials that are provided to you by spring security. All you have to do is to go to your "application.properties" file, and then provide these variables
```yaml
spring.security.user.name=example_username
spring.security.user.password=example_password
```
the above user will become the new default credentials to access your app.

## Configuring Authentication in Spring Security
In the above example, we just overrode the default user that spring security gives us. But what if we want to check against a list of users, and then log them in if they have the right credentials. To do that, we have to configure spring security for authentication. We will create some users in memory and use spring security to authenticate against them.

The way to configure authentication in Spring Security is to affect what we call the "AuthenticationManager". The authentication manager sits inside a spring security app, and all it does is to authenticate. In fact, it has a method called ".authenticate()" for that purpose, and throws an exception if it cant authenticate. Now how do we affect the AuthenticationManager class, we dont create our own AuthenticationManager, but instead we configure what the AuthenticationManager does using a builder pattern. We work with the AuthenticationManagerBuilder class to configure the AuthenticationManager class. We use the AuthenticationManagerBuilder to
1. Get hold of AuthenticationManagerBuilder
2. Set the configuration on it

So every interaction we have, is through the Configuration we set. For instance, see an example configuration,
1. AuthMgBuilder - What type of auth do you need
2. You - In Memory Auth please
3. AuthMgBuilder - Tell me the username, password and role
4. You - Passes those details to the builder

Now, once we configure the AuthenticationBuilderManager with the above configuration properties, it will create a new Authentication Manager, which has the values you want for you

HOW DO YOU GET HOLD OF THE AUTHENTICATION BUILDER MANAGER ?
In a Spring Security application, developers can define a method named userDetailsService() within a class called SecurityConfiguration. This method configures user details for in-memory authentication. If developers don't override this method, Spring Security will use default details. Inside userDetailsService(), developers create user details using User.withDefaultPasswordEncoder(). They then initialize an InMemoryUserDetailsManager with these details. The method returns the configured InMemoryUserDetailsManager instance as a bean, registered in the application context for authentication. see below for an example
```java
@Configuration
public class SecurityConfiguration{
    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("thesarfo")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
```

In the above example, we are using a plain text password, but it is essential to hash our passwords, normally by creating a bean to hash/encode our passwords. something like this
```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("thesarfo")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
```

Now, when you start the application and visit the endpoint, you can provide the signin page with the credentials you have provided above. and you will be able to sign in





