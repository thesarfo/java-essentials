If you have a functional interface, you can reference the method inside it when creating an instance of that interface. see below
```java
interface MyLambda{
    public void display(String str);
}

public class LambdaDemo {

    public static void reverse(String str){
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        System.out.println(sb);
    }
    public static void main(String[] args){
        MyLambda mlb = System.out::println;
        MyLambda ml = LambdaDemo::reverse;
        mlb.display("Hello");
        ml.reverse("Hello");
    }
}
```

If you look at the part where we do the "System.out::println", what happens is that the static method(println) after the "::"...will be assigned to the display method in the functional interface above. so when you call the ".display()" method, it will automatically print out the string you pass to it. The same thing with the static method "reverse". now when we call the "reverse" method o the "ml" object, it will call the reverse() static method above and print its result.


But what if the method is not static. If the reverse method is not static, you will not be able to reference it to the object "ml". So then, what do you do in this situation. Well, you should first of all create an instance of the class, and then instead of referencing the class name, use the object name. see below.

```java
interface MyLambda {
    public void display(String str);
}

public class LambdaDemo {

    public void reverse(String str) {
        StringBuffer sb = new StringBuffer(str);
        sb.reverse();
        System.out.println(sb);
    }

    public static void main(String[] args) {
        LambdaDemo ld = new LambdaDemo(); // instance of the class
        MyLambda ml = ld::reverse; // we are referencing the "ld" object instead of the LambdaDemo class.
        ml.reverse("Hello");
    }
}
```




















