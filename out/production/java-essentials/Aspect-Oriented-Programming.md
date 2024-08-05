AOP is a programming paradigm that enables the modularizaton of cross cutting concerns in software apps. 

1. ***Cross cutting*** concerns are the aspects of your application that affect multiple parts of the codebase. This can include logging, security, transactions and error handling. AOP lets you separate these concerns from the core logic, making your code more maintainable and less cluttered.

## Key Terminologies
* **Aspect**: An aspect is a module that encapsulates a cross-cutting concern. It contains advice and pointcuts

* **Advice**: An advice is the code that runs when a particular pointcut is matched. There are different types of advice, including "before", "after", "around" and "after throwing"

* **Pointcut**: A pointcut is an expression that defines where an aspect's advice should be applied in the codebase. It selects specific join points in your application.

* **Join Point**: A join point is a specific point in the execution of a program, such as a method call, constructor invocation, or field access.


## Creating Aspects
In Spring, you create aspects by defining a class annotated with @Aspect. This class should contain advice methods.
```java
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.example.demo.*.*(..))")
    public void logBefore() {
        System.out.println("Before method execution");
    }
}
```

In this example, the LoggingAspect class contains a ```@Before``` advice that logs a message before the execution of any method in the ```com.example.demo``` package.

## Defining Pointcuts
Pointcuts define where advice should be applied. You can use expressions to specify join points. For instance, ```"execution(* com.example.demo.*.*(..))"``` in the above example selects all method executions in the specified package.

A pointcut expression consists of a few parts:

1. ```execution```: This keyword indicates that you are defining a pointcut for method execution.
2. ```*```: This wildcard matches any return type
3. ```*.*(..)```: This part matches any method name and any number of arguments. Pointcut expressions can be highly customized to target specific methods and classes. 


## Writing Advices
1. ```@Before```: Runs before the join point.
2. ```@After```: Runs after the join point.
3. ```@Around```: Wraps around the join point, allowing you to modify its behaviour.
3. ```@AfterThrowing```: Runs if an exception is thrown at a join point.


The ```@Before``` advice in our example logs a message before a method is executed. Below is a short explanation of advice types:

1. ```@Before```: It is used for actions to be taken before the method execution. For example, you can use it for logging, security checks, or pre-processing.

2. ```@After```: It is executed after the method execution. It is useful for tasks like cleaning up resources or finalizing operations.

3. ```@Around```: This is the most powerful advice type. It wraps around the method and can control its execution. You can modify input and output, perform additional actions befoer and after the method, and even prevent the method from executing.

4. ```@AfterThrowing```: Executed when an exception is thrown during method execution. You can handle or log exceptions with this advice.


## Combining It All
To apply your aspect, you need to enable AOP in your Spring Boot application. Add the ```@EnableAspectJAutoProxy``` annotation to your main application class.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

This annotation enables AspectJ-based proxy creation and allows Spring to weave your aspects into the application’s components.

## Testing the AOP Implementation
Create a sample service and controller in your project, and run the application. You will notice that the "Before Method Execution" message is printed before the execution of methods in the specified package.

### Advanced AOP Techniques
1. **AspectJ and Custom Annotations**
While Spring AOP provides proxy-based AOP, AspectJ offers a more powerful and comprehensive AOP framework. You can use AspectJ along with custom annotations to define aspects.

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
}

@Aspect
public class CustomAspect {
    @Before("@annotation(Loggable)")
    public void logMethod(JoinPoint joinPoint) {
        // Log method execution
    }
}
```

In this example, the ```@Loggabble``` annotation is used to mark methods that should be logged. The ```@Before``` advice then logs the execution of methods annotated with ```@Loggable```.

2. **AspsectJ Expressions**
AspectJ provides more advanced pointcut expressions, allowing for fine-grained control over which methods and classes to target. These expressions can match on class hierarchies, method parameters and more.

```java
@Around("execution(* com.example.service.*.*(..)) && args(arg1, arg2)")
public Object customAroundAdvice(ProceedingJoinPoint joinPoint, Object arg1, Object arg2) {
    // Custom around advice logic
    return joinPoint.proceed();
}
```

In this example, the pointcut expression matches methods in the com.example.service package with specific arguments.

3. **Aspect Ordering**
You can control the order of advice execution within aspects by setting an order value. Lower values execute before higher values.

```java
@Aspect
public class OrderingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void beforeAdvice() {
        // This advice runs before others
    }
}
```

By default, advice within the same aspect has an order of 0. You can set a specific order by annotating your aspect with ```@Order```.


4. **Exception Handling in AOP**
AOP provides a mechanism to handle exceptions gracefully. For example, you can use the ```@AfterThrowing``` advice to log or handle exceptions.

```java
@Aspect
public class ExceptionHandlingAspect {

    @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "ex")
    public void handleException(Throwable ex) {
        // Handle the exception
    }
}
```

This advice captures exceptions thrown by methods in the specified package and handles them appropriately.




## Differences Between AOP and OOP

Aspect-Oriented Programming (AOP) and Object-Oriented Programming (OOP) are both paradigms in Java that provide different ways to structure and organize code. Here are the key structural differences between AOP and OOP in Java:

### Object-Oriented Programming (OOP)

1. **Class-Based Structure**:
   - In OOP, the primary unit of structure is the class. Classes encapsulate data (attributes) and behavior (methods) related to an object.
   - Objects are instances of classes.

2. **Inheritance and Polymorphism**:
   - OOP uses inheritance to create a hierarchy of classes that share common behavior and attributes. This allows for code reuse and method overriding.
   - Polymorphism enables objects to be treated as instances of their parent class, allowing for flexible and interchangeable code.

3. **Encapsulation**:
   - OOP emphasizes encapsulation, where the internal state of an object is hidden from the outside world and can only be accessed through public methods. This promotes modularity and reduces dependencies.

4. **Focus on Entities and Relationships**:
   - The design of OOP systems typically revolves around entities (objects) and their relationships. The emphasis is on defining objects and how they interact with each other.

### Aspect-Oriented Programming (AOP)

1. **Aspect-Based Structure**:
   - AOP introduces aspects as the primary unit of modularity. Aspects encapsulate cross-cutting concerns that affect multiple classes, such as logging, security, or transaction management.
   - Aspects contain advice (code to be executed at certain points) and pointcuts (expressions that define where the advice should be applied).

2. **Separation of Concerns**:
   - AOP aims to separate cross-cutting concerns from the main business logic. This results in cleaner, more maintainable code as cross-cutting concerns are not tangled with core business logic.

3. **Join Points and Advice**:
   - AOP defines join points, which are specific points in the execution of a program (e.g., method calls, field access).
   - Advice is the code that is executed when a join point is reached. There are different types of advice, such as before, after, and around advice, depending on when the advice runs in relation to the join point.

4. **Pointcuts**:
   - Pointcuts are expressions that match join points. They allow aspects to target specific points in the program where advice should be applied.

5. **Weaving**:
   - AOP uses a process called weaving to integrate aspects into the main code base. Weaving can occur at compile time, load time, or runtime, depending on the AOP framework used (e.g., AspectJ, Spring AOP).

### Summary of Structural Differences

- **Primary Unit**: OOP uses classes, while AOP uses aspects.
- **Modularity**: OOP focuses on encapsulating data and behavior within classes. AOP focuses on modularizing cross-cutting concerns into separate aspects.
- **Execution Points**: OOP structures code around objects and their interactions. AOP structures code around join points and advice.
- **Concern Separation**: OOP encapsulates business logic within objects. AOP separates cross-cutting concerns from business logic, applying them through pointcuts and advice.
- **Integration**: OOP integrates behavior through inheritance and polymorphism. AOP integrates behavior through weaving of aspects.

These structural differences reflect the distinct philosophies of OOP and AOP. While OOP is centered around creating and interacting with objects, AOP provides a way to cleanly separate and modularize concerns that cut across multiple objects. Both paradigms can be used together to complement each other, leveraging the strengths of both approaches.