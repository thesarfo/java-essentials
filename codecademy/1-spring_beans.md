A spring bean is an object managed by the Spring IOC container. Bean instantiation/beanstation is the process of creating a new bean object, just like how you create an object from a class.

### Bean-stantiation
Normally, instantiating an object from the class requires the use of the new keyword. Take a look at the example below, lets say we are designing a racing game. For each round of the race we need a race track and drivers. We could create a class for both as shown below
```java
public class RaceTrack {
  private String location;
  private int miles;
  private String trackType;
}

public class Driver {
  private String name;
  private String team;
  private int yearsExperience;
}
```

Now lets create a class that can be used for each round of the race
```java
public class RaceRound {
  private String startTime;
  private RaceTrack currentRaceTrack = new RaceTrack();
  private Driver currentDriver = new Driver();
}
```

For each round of the race we need to instantiate a RaceTrack and a Driver, so we use the new keyword to do that. Any changes to those two classes would require additional changes in the RaceRound class.

Now lets take a look at how it would work with Spring beans. First, we would mark our RaceTrack and Driver classes as Spring beans using the @Component annotation:

```java
@Component
public class RaceTrack {
  private String location;
  private int miles;
  private String trackType;
}

@Component
public class Driver {
  private String name;
  private String team;
  private int yearsExperience;
}
```

Then, we can remove the actual instantiation code from the RaceRound, instead using the @Autowired annotation
```java
public class RaceRound {
  private String startTime;
  @Autowired
  private RaceTrack currentRaceTrack;
  @Autowired
  private Driver currentDriver;
}
```

Notice we are no longer using the new keyword when creating instances of RaceTrack and Driver. So, how are we able to declare and use an instance without instantiating it? We marked our dependent classes RaceTrack and Driver as Spring beans, which allows the IoC container to manage them, i.e. instantiate them and inject them into our RaceRound class.

As an additional bonus, we didn’t have to edit the existing code in our RaceTrack and Driver classes to turn them into Spring beans. All we had to add was the @Component annotation. 

Note: @Component and @Autowired are just two of many annotations for working with Spring beans and the IoC container. 


## Auto-beans loading
The fully automatic anotations approach is facilitated using three annotations:

1. @Configuration, which notifies the framework that beans may be created via the annotated class.

2. @ComponentScan, which tells the framework to scan our code for components such as classes, controllers, services, etc.

3. @EnableAutoConfiguration, which tells the container to auto-create beans from the found components.

Together these three annotations tell the framework where to start looking, how to search our code, and automatically instantiate beans from the found components.

Using three separate annotations may seem hard to recall. However, remember when we said “the Spring framework abstracts the complexity away with simple, easy-to-use annotations”? This remains true as these three significant annotations have been wrapped up into one.

The @SpringBootApplication annotation is a compilation of @Configuration, @ComponentScan, and @EnableAutoConfiguration. When we apply the @SpringBootApplication annotation to the class containing our main method, our application runs with all of this built-in functionality. Therefore, when our application starts up the container scans our code for components from which beans should be instantiated. 

We mostly find this annotation in most default Spring projects
```java
@SpringBootApplication
public class RecipeApplication {

  public static void main(String[] args) {
    SpringApplication.run(RecipeApplication.class, args);
  }
}
```

In our previous examples, we declared our dependencies in RaceRound with fields and annotated them with @Autowired. In other Spring applications, you’ll often see dependencies injected via the constructor (which doesn’t require an annotation). In this example, the dependency is CoffeeRepository:
```java
public class CoffeeController {

  private final CoffeeRepository coffeeRepository;

  public CoffeeController(CoffeeRepository coffeeRepo) {
    this.coffeeRepository = coffeeRepo;
  }
}
```
This example is just to show that there are many ways that the IoC container uses Spring beans in our applications.