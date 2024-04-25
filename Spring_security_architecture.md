### Authentication Manager
The main interface for autentication in springboot is AuthenticationManager, which has only one method called "authenticate".
```java
public interface AuthenticationManager {

  Authentication authenticate(Authentication authentication)
    throws AuthenticationException;
}
```
The AuthenticationManager can do one of 3 things in its authenticate() method:

1. Return an Authentication (normally with authenticated=true) if it can verify that the input represents a valid principal.

2. Throw an AuthenticationException if it believes that the input represents an invalid principal.

3. Return null if it cannot decide.

### Authentication Provider
The AuthenticationManager doesn't perform the authentication itself. Instead, it delegates the task to an appropriate AuthenticationProvider. An AuthenticationPRovider is like the AuthenticationManager, but it has an extra method to allow the caller to query whether it supports a given Authentication type.
```java
public interface AuthenticationProvider {

	Authentication authenticate(Authentication authentication)
			throws AuthenticationException;

	boolean supports(Class<?> authentication);
}
```

1. **UserDetailsService** interface has a method to load User by username and returns a UserDetails object that Spring Security can use for authentication and validation.

2. **UserDetails** contains necessary information (such as: username, password, authorities) to build an Authentication object.

3. **UsernamePasswordAuthenticationToken** gets {username, password} from login Request, AuthenticationManager will use it to authenticate a login account.

4. **AuthenticationManager** has a DaoAuthenticationProvider (with help of UserDetailsService & PasswordEncoder) to validate UsernamePasswordAuthenticationToken object. If successful, AuthenticationManager returns a fully populated Authentication object (including granted authorities).

5. **OncePerRequestFilter** makes a single execution for each request to our API. It provides a doFilterInternal() method that we will implement parsing & validating JWT, loading User details (using UserDetailsService), checking Authorizaion (using UsernamePasswordAuthenticationToken).

6. **AuthenticationEntryPoint** will catch authentication error.

7. **Repository** contains UserRepository & RoleRepository to work with Database, will be imported into Controller.

8. **Controller** receives and handles request after it was filtered by OncePerRequestFilter.