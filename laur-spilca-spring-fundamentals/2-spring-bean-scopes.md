### Bean Scope
This basically refers to the lifecycle and visibility of a bean in the spring context. It determines how many instances of a bean are created and how they are shared.

By default, the scope of a bean in the Spring Context is the **Singleton**. This means that a single instance of the bean is created and shared across the entire Spring Container. This means that every request for the bean will return the same instance.

We can specify the scope of a bean using an **XML Based Configuration** or using **Annotations**.

Below are some other bean scopes in spring

1. **Prototype**: A new instance of the bean is created every time it is requested from the Spring Container. This is useful when you need a new instance for each use.

Let's say we have a interface called `NumberService`
```java
public interface NumberService {
    public int getValue();
}
```

And we have a service class that implements this interface
```java
@Service
public class RandomNumberService implements NumberService {

    private final int value;

    public RandomNumberService() {
        this.value = new Random().nextInt(1000);
    }

    public int getValue() {
        return value;
    }
}
```

We may also have a controller that perhaps renders a template.
```java
@Controller
public class IndexController {

    @Autowired
    private NumberService numberService; //proxy

    @GetMapping("/home")
    public String indexAction(Model model) {
        model.addAttribute("message", numberService.getValue());
        return "index.html";
    }
}
```

What happens here is that, anytime and every time we call the `getValue` method in our application, it is always going to return the same value, because the `RandomNumberService` constructor was only called once. This is the default **Singleton** bean scope in Spring. If it was a **Prototype** scope, the value will change anytime we call the `getValue` method.

#### Annotation based configuration for bean scopes

2. **Request Scope**: A new instance of the bean is created for each HTTP request. This scope is only available in a web-aware Spring Application context.

Taking our example above, we can define the scope of our bean using the `@Scope` annotation. 

```java
@Service
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RandomNumberService implements NumberService {

    private final int value;

    public RandomNumberService() {
        this.value = new Random().nextInt(1000);
    }

    public int getValue() {
        return value;
    }
}
```

Now, what happens when we run this application is that it will fail at startup. This is because the **Request** scope is only available in a web-aware context. But the `IndexController` is not a web scope, but is a **Singleton** in the spring context. While we are trying to inject the service, which is a web scope. 

The application fails at startup because there is a mismatch between the scopes of the beans being injected. Specifically, the `IndexController` is a singleton bean, but it is trying to inject a `RandomNumberService` bean that is defined with a request scope. This scope mismatch causes an issue because the Spring container cannot resolve how to inject a bean that is created and destroyed per HTTP request into a singleton bean that exists for the entire lifecycle of the application.

Spring is smart enough to link the Request Scoped bean and Singleton scoped bean together. But it cannot do this directly, so what we do is that, we specify a `targetProxy`. We do this by specifying the `proxyMode` attribute.
```java
@Service
@Scope(WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RandomNumberService implements NumberService {

    private final int value;

    public RandomNumberService() {
        this.value = new Random().nextInt(1000);
    }

    public int getValue() {
        return value;
    }
}
```

What happens here is that, instead of directly injecting the value of the bean in the Context, it will look at wherever you are auto wiring the bean, and it will inject a proxy that manages the bean. 

So basically, what happens is that, when the application context is started, Spring creates a proxy for the `RandomNumberService` bean instead of a direct instance.

The proxy is then injected into the `IndexController`'s `numberService` field. So when the `numberService.getValue` method is called within the `IndexController`, the proxy intercepts the call. 

The proxy then determines the appropriate instance of `RandomNumberService` for the current scope(Http Request Scope in this case) and then delegates the method call to that instance. 

Now, anytime we start our app, and then make a request to the `IndexController`, the value returned to us will always be different. Because the scope of the bean is different(per http request).

The `ScopedProxyMode` is used to handle the injection of beans with different scopes, especially when injecting beans with a shorter lifecycle(request or session scopes) into beans with a longer lifecycle(singleton scope). There are three types of ScopedProxyMode
* TARGET_CLASS
* INTERFACES
* NO

1. **TARGET_CLASS**: This is the most commonly used proxy mode. When proxyMode is set to TARGET_CLASS, Spring creates a subclass (CGLIB proxy) of the target bean to handle method calls. This mode is typically used when injecting scoped beans into other beans.

For example
```java
@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RandomNumberService implements NumberService {
    private final int value;

    public RandomNumberService() {
        this.value = new Random().nextInt(1000);
    }

    public int getValue() {
        return value;
    }
}
```

**Behind the scenes**, Spring creates a proxy subclass (using CGLIB) of the target bean.
This proxy intercepts method calls and delegates them to the appropriate scoped instance.

2. **INTERFACES**: When `proxyMode` is set to `INTERFACES`, Spring creates a JDK dynamic proxy that implements the same interfaces as the target bean. This mode is used when the target bean implements one or more interfaces.

For example
```java
@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
public class RandomNumberService implements NumberService {
    private final int value;

    public RandomNumberService() {
        this.value = new Random().nextInt(1000);
    }

    public int getValue() {
        return value;
    }
}
```

You use this when the target bean implements interfaces, and also when you prefer using JDK dynamic proxies over CGLIB proxies.

**Behind the scenes**, Spring creates a JDK dynamic proxy that implements the interfaces of the target bean. This proxy intercepts method calls and delegates them to the appropriate scoped instance.

3. **NO**: When `proxyMode` is set to NO`, no proxy is created. This is the default mode, and it can lead to scope mismatch issues if not used correctly.

```java
@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class RandomNumberService implements NumberService {
    private final int value;

    public RandomNumberService() {
        this.value = new Random().nextInt(1000);
    }

    public int getValue() {
        return value;
    }
}
```

You use this when no proxy is needed, and the target bean scope matches the scope of the injection point.

**Behind the scenes**, No proxy is created. Direct instances of the target bean are injected, which can cause scope mismatch issues if the scopes do not align.