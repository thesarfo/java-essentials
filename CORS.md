Sure, here's an expanded version of your notes:

Cross-Origin Resource Sharing (CORS) is a mechanism that allows many resources on a web page to be requested from another domain outside the domain from which the resource originated. It's a way to relax the same-origin policy, which is a security measure that prevents JavaScript from making requests across domain boundaries.

The simplest way of solving a CORS error is by allowing the origin on the controller itself using the `@CrossOrigin` annotation. For example:

```java
@RestController
public class DemoController{
    
    @GetMapping("/demo")
    @CrossOrigin("http://localhost:8080")
    public String demo(){
        return "hello world";
    }
}
```

In this example, the `@CrossOrigin` annotation allows requests to the `/demo` endpoint from the origin `http://localhost:8080`. This means that a webpage served from `http://localhost:8080` can make a request to this endpoint without being blocked by the browser's same-origin policy.

If a request to this endpoint comes from a different origin, the browser will block the request due to the same-origin policy, unless the server includes the appropriate CORS headers in the response. Using the wildcard `*` with `@CrossOrigin` will allow all origins to access the endpoint.

However, using `@CrossOrigin` directly on the controller is not typically done in production. It's more common to use a CORS configuration inside the security filter chain. This provides more control and flexibility over the CORS policy. Here's an example:

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http.authorizeHttpRequests().anyRequest().permitAll();

    http.cors(c -> {
        CorsConfigurationSource source = request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(
                List.of("www.example.com", "www.another.com"));
            config.setAllowedMethods(
                List.of("GET", "POST"));
            config.setAllowedHeaders(List.of("*"));
            return config;
        };
        c.configurationSource(source);
    });

    return http.build();
}
```

In this example, the CORS policy is configured to allow requests from `www.example.com` and `www.another.com`, and to allow `GET` and `POST` methods. The `setAllowedHeaders` method is used to specify which HTTP headers can be used when making the actual request.