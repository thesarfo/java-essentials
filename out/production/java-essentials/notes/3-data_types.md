There are 2 types of datatype in java. Primitive and reference types.

### Primitive data types
Primitives stores values. for instance "int" is a primitive type, and it stores integer values.
in Java, primitives come in different sizes, and those sizes have names. When you declare any variable in Java, you must declare it with a specific type. There are 8 primitive data types in java. and 6 of them store different number types. see below
```java
class HelloWorld{
    public static void main(String[] args){
        // integer types
        byte aSingleByte = 100; // -128 to 127
        short aSmallNumber = 20000; // -32,768 to 32,767
        int anInteger = 2147483647; // -2147483648 to 2147483647
        long aLargeNumber = 9223372036854775807L;
        
        // decimal types
        double aDouble = 1.79769313; // 4.9E-324 to 1.7976931348...
        float aFloat = 3.4028F; // 1.4E-45 to 3.4028235E38
        
        // booleans
        boolean isWeekend = false;
        boolean isWeekday = true;
        
        // characters
        char copyrightSymbol = '\u00A9';
        char single = 'a';
        
        System.out.println(("This is the copyright symbol: " + copyrightSymbol));
    }
}
```

Usually for numbers, the int and double types should suffice for normal numbers and numbers that have decimal points respectively


### Reference type
They store the reference to a memory location where a dynamic object is being stored. we will come there later

An example is an array


#### Type conversion
You can convert a variable to another datatype. for instance, we can convert an integer to a double. We have implicit and explicit type conversions, with implicit, the compiler itself does it for us...whilst with explicit, we have to explicitly tell the compiler that we wanna perform a type conversion. see below.
```java
class Main{
    public static void main(String[] args){
        // implicit type conversion, the compiler does it for us
        int number1 = 5;
        double number2 = number1;


        System.out.println(number2); // outputs 5.0 ie a double
    }
}
```

But conversion from a double to an int is not possible, unless we do that ourselves. see below
```java
class Main{
    public static void main(String[] args){
        //explcit type conversion
        double number1 = 5.8;
        int number2 = (int)number1; // we put the (int) in brackets to tell the compiler that we wanna convert number1 into an int and store it in number2

        System.out.println(number2); // outputs 5
    }
}
```