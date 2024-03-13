To declare a variable in java, you need the datatype of the variable and the name of the variable. Declaring a variable means that the compiler will know that the variable exists.  see below
```java
class HelloWorld{
    public static void main(String[] args){
        int age; // we have declared a variable

        age = 21; // we have defined a variable
    }
}
```

Aside declaring a variable, you can also define a variable. This simply means assigning a value to the variable
```java
public class Main {
    public static void main(String[] args){
        int age; // variable declaration
        age = 21; // variable definition
        
        int another_age = 22; // declaring and defining on the same line 

        System.out.println("I am " + age + " years old");
    }
}
```

You can also change the value of a variable many times you want, but you cannot declare the same variable twice.

#### Scopes

Any variable you declare inside a method is only accessible inside that particular method. This is called a local variable, however you can also declare variables outside the method. see below
```java
public class Main {
    int another_age = 22; // accessible everywhere within the class
    public static void main(String[] args){
        int age = 21; // only accessible in the main method

        System.out.println("I am " + age + " years old");
    }
}
```

However, to use the "global" variable in the Main class inside the main method, you will have to declare that variable as static. see below
```java
public class Main {
    static int another_age = 22; // made it static
    public static void main(String[] args){
        int age = 21;

        System.out.println("I am " + age + " years old");
    }
}
```

Remember that, static methods can only work with static variables.

Note that, when you declare a variable on the class level, the java compiler assigns a default value to it. For integers, it is 0.
```java
public class Main {
    static int age;
    public static void main(String[] args){
        System.out.println("I am " + age + " years old"); // this will print the age variable as 0, unless you define the variable with your preferred value
    }
}
```

There are a bunch of variable naming convern