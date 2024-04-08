If an Interface has a single abstract method, then it is called a functional interface. It can even use an annotation "@FunctionalInterface". But if you add another method to the interface, you will get an error.

Assuming you have an interface like below
```java
@FunctionalInterface
interface MyLambda{
    public void display();
}
```

You can implement this interface and override the display method. like below
```java
class My implements MyLambda {
    @Override
    public void display() {
        System.out.println("Hello World");
    }
}
```
But there is a much neater way to do this, using lambda expressions. First of all, instead of creating a whole new class which implements the MyLambda interface, we can create an instance/reference of the MyLambda interface directly, and then use a lambda expression to actually override the display method. see below
```java
public class LambdaDemo {
    public static void main(String[] args){
        MyLambda M = () -> {System.out.println("Hello World");};
        M.display();
    }
}
```
In the above code, we have created a reference of the MyLambda interface directly, and we use the empty brackets "()" to represent the dispay method, and then we use the arrow "->" to show the flow or what the method returns, and then we provide the method body. Finally, we can do "M.display()" and see if it runs. It will


### Lambda expressions taking a parameters
Now, what if the method being overridden takes a single parameter. like below
```java
@FunctionalInterface
interface MyLambda{
    public void display(String str);
}
```
Well, we will have to include the parameter inside the brackets that denote the lambda expression. And then specify the content of that parameter when we call that method. see below
```java
public class LambdaDemo {
    public static void main(String[] args){
        MyLambda M = (s) -> { // pass the parameter in the brackets
            System.out.println(s);
        };
        M.display("Hello World"); // specify the contents of the parameter
    }
}
```

What about double/multiple parameters. we can also do that
```java
@FunctionalInterface
interface MyLambda{
    public int add(int x, int y);
}
public class LambdaDemo {
    public static void main(String[] args){
        MyLambda M = (a, b) -> a+b;

        int result = M.add(20, 30);
        System.out.println(result);

    }
}

```

Lambda expressions can have their own variables, and you can use them as much as you want in the lambda expression

Lambda expressions can only access or capture or modify local variables, only if they are final, or they are never modified inside the method.

Lambda expressions can access or modify instance variables...they do not need to be final.

Lambda expressions, in a way...are similar to inner classes

When any method is taking a functional interface as a parameter, you can pass in a Lambda expression as a parameter too. In other words, a lambda expression can be passed as a parameter to a method
```java
@FunctionalInterface
interface MyLambda{
    public void display();
}

class UseLambda{
    public void callLambda(MyLambda ml){
        ml.display();
    }
}

class Demo {
    public void method1(){
        UseLambda ul = new UseLambda();
        ul.callLambda(()-> System.out.println("Hello"));
    }
}
```























