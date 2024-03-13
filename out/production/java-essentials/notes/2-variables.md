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

You can also change the value of a variable many times you want, but you cannot declare the same variable twice