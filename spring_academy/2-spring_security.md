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
@EnableWebSecurity
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
@EnableWebSecurity
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


## Configuring Authorization in Spring Security
Now, we know that all APIs need authentication, but what we want to do is that we want different parts of our API to have different access requirements. We can have something like this


| API   | Roles allowed to access it |
|-------|----------------------------|
| /     | All (authenticated)        |
| /user | USER and ADMIN roles       |
| /admin| ADMIN role                 |


So before we do that, we need to get hold of our authorization object, to configure authorization, just like we did with authentication. When doing authentication, we were about to use the AuthenticationManagerbuilder, but since that was deprecated, we went with the InMemoryDetailsManager. For authorization, we would've used HttpSecurity, but since that is deprecated, we will use a SecurityFilterChain. Note that the below implementation assumes that you have two users, one with the role of "USER" and another with the role of "ADMIN". see below
We are configuring a SecurityFilterChain for HttpSecurity, specifying authorization rules for HTTP requests. All paths (/**) are restricted only to users with the role of ADMIN. Additionally, authentication is required for any other request. The configuration also enables default form-based authentication.

Our security configuration will look like this
```java
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("thesarfo")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminpassword")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults());
        return http.build();

    }
}
```

Now, note that the above implementation only allows admin users to log into our app, now what if we want to log out the admin user to try another user. Well, just like how Spring Security automatically created a login endpoint for us, there is also a logout endpoint we can visit, in order to sign out and then sign in as another user. If we wanted to provide different levels of access to different endpoints, we could do something like this.
```java
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/user").hasAnyRole("USER", "ADMIN") // both admin and users can access the /user endpoint
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults());
        return http.build();
    }
```

## How to do JDBC authentication in Spring Security
First of all, you need to add some dependencies, the database, and the jdbc api. For this demonstration, we will add the H2 and the JDBC api dependencies, as well as the spring security dependency. 

First, we need to specify a datasource(database), and then configure the datasource, with the AuthenticationManager builder. Since we are using jdbc, with h2, we need to configure jdbcAuthentication, and then specify our datasource within which we want spring security to query for user credentials.

After which, we will use a SecurityFilterChain to authorize the requests and endpoints to the users and roles we have created in our database. see below for a sample configuration
```java
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser(User.withUsername("thesarfo")
                        .password("password")
                        .roles("USER")
                )
                .withUser(User.withUsername("admin")
                        .password("adminpassword")
                        .roles("ADMIN")
                );

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated())
                .formLogin(withDefaults());

        return http.build();
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
```

Our datasource points to the h2 database, since that is the default, and that is what we have specified in our project too. But in case your datasource pointed to a postgresql db, spring security will create the necessary configurations for postgresql. So when the application starts up, spring security basically populates our database with the users we have created, with a default schema. Spring Security says we don't have to worry about creating a schema for users, so by using the ".withDefaultSchema()" we are telling spring security to create a user for us.


Obviously, this is not a practical way of doing stuff, especially in production apps. We dont want to populate the default schema and hardcode user details. We just assume that those things are in our database already.

This will eb our ideal configuration
```java
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource);
   }
```

What the above means is that we have a database, we have a schema, and we have users already existing in our database. And we are just configuring spring security to connect to the database. What this means is that there needs to be an extra configuration somewhere that defines the user schema as well as aids in the user creating process.

Spring security has the default user schema on their website, we can copy that and then create a "schema.sql" file(default mechanism in spring boot that populates our db with schema that we specify), to create a schema for our users. However, note that if we edit the default schema we took from the spring security website, we will be responsible for telling spring security to work with that edited schema. But for now let's go with the default schema(for h2 database)
```jdbc
// schema.sql
create table users(
	username varchar_ignorecase(50) not null primary key,
	password varchar_ignorecase(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar_ignorecase(50) not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);
```
The next thing is to create two users like we did before. These users will follow the schema we have, and then we will load them into the database. We do that by creating a file called "data.sql" and this is where our user data will be. see below
```sql
// data.sql
insert into users (username, password, enabled)
values ('thesarfo', 'password', true);

insert into users (username, password, enabled)
values ('admin', 'adminpassword', true);

insert into authorities (username, authority)
values ('thesarfo', 'ROLE_USER');

insert into authorities (username, authority)
values ('admin', 'ROLE_ADMIN');
```

Now, we have a schema for our users, and we have created users that will be populated into our database at runtime. So now we can access the endpoints in our controller, and we might be authorized or unauthorized based on the role of the user we are trying to log in as.

This is our ideal use case. We create our schema, and users ourselves and just tell spring security to connect to the database.


Now assuming we have a custom schema, or lets say our users/authorities table have different names than the default. How do we tell spring security to use that schema instead. Since spring security will need the username and authority from their respective tables. Well there are two methods for that. ".usersByUsernameQuery()" and ".authoritiesByUsernameQuery()". see below
```java
// assuming the user table and authorities table are called my_user and my_authorities
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled "
                        + "from my_users "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from my_authorities "
                        + "where username = ?");
   }
```

### What if you had a real database??
What if instead of h2/in memory, we had a postgres or oracledb somewhere that we want to connect to. It's simple. We just go to our application.properties and specify the following properties for our database
```yaml
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

## JPA Authentication and MySQL
Since this is a jpa project with mysql, we will need to add those dependencies. These are the dependencies we need
1. Spring web
2. Spring security
3. Spring jpa
4. Mysql driver


Refer to [this article](https://www.codejava.net/frameworks/spring-boot/spring-boot-security-authentication-with-jpa-hibernate-and-mysql) or the [github repo](https://github.com/thesarfo/sb-sec/tree/master/jpa-auth) for the full implementation.








