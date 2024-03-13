In a source file, put a class. In a class, put methods. In a method, put statements.

### What goes in a source file
A source file(a file with the java extension) typically holds one class definition. The class is a piece of your program, and the class must go within a pair of curly braces. see below
```java
public class Dog {

}
```

### What goes in a class
A class has one or more methods. In the Dog class, the bark method will hold instructions for how the Dog will bark. Your methods must be declared inside a class(within the curly braces of the class). see below
```java
public class Dog{
    void bark() {

    }
}
```

### What goes in a method
Inside your method, write your instructions for how that method should be performed. Method code is basically a set of statements, and you can think of a method like a function or procedure. see below
```java
public class Dog{
    void bark() {
        statement1;
        statement2;
    }
}
```

## Anatomy of a class
When the JVM starts running, it looks for the class you give it at the command line. Then it starts looking for a specially written method that is seen below
```java
public static void main (String[] args){
    // code goes here
}
```

Then, the JVM runs everything within the curly braces of the main method above. Every java app has to have at least one class, and at least one main method (not one main per class, just one main per application).

an example below

```java
public class MyFirstApp{
    public static void main (String[] args){
        System.out.print("I Rule!");
    }
}
```

public - so everyone can access it
class - definition of a class
MyFirstApp - the name of the class
static - we'll get there later
void - the return type of the method, void means it has no return value
main - the name of the method
String[] args - arguments to the method...this method must be given an array of Strings, and the array will be called args
System.out.print - print to the standart output the string "I rule"