The char data type is only for a single character. But if you wanna store multiple characters together, like your name for instance, you can do that inside a string. In java, there are multiple ways of defining a string.

##### first way - using the String keyword
```java
class HelloWorld{
    public static void main(String[] args){
        String name = "Ernest";
        
        System.out.println(name);
    }
}
```

##### second way - using the new keyword
the new keyword is used to create a new object from a class
```java
class HelloWorld{
    public static void main(String[] args){
        String name = new String("Ernest");
        
        System.out.println(name);
    }
}
```

### String Methods
```java
class Main{
public static void main(String[] args){
        String name = "Ernest";
        String country = "ghana";
        int age = 21;
        
        System.out.println("Hello I am " + name + ", I am from " + country + " and I am " + age " years old");
    }
}
```

The above works well, but it can become very complicated, the more we introduce new variables and plus operators. There is a better way to do it and thats called string formatting using format specifiers. see below
```java
class Main{
    public static void main(String[] args){
        String name = "Ernest";
        String country = "ghana";
        int age = 21;

        String formattedString = String.format("My name is %s. I am from %s and I am %d years old.", name, country,
                age);
    }
}
```
This way, our code looks cleaner since we use format specifiers to tell the compiler which values we want to output, and then at the end of the string, we add the values in the order of which we want them to be displayed. just like we have done above.

#### Examples of format specifiers
%s or %S - string
%f - decimal floating point
%d - decimal integer
%c - character
%b or %B - boolean
%t or %T - time and date
%n - inserts a newline character

Instead of storing the String.format() method inside a new variable and printing the variable, we can print it out directly. This works, but it not recommended. its better to use the way we did above
```java
class Main{
    public static void main(String[] args){
        System.out.println(String.format("formatted string here"));
    }
}
```


### Checking the length of a string
```java
class Main{
    public static void main(String[] args){
        String name = "Ernest Sarfo";
        
        System.out.println(name.length()); // it also counts the spaces in the strings
    }
}
```

### Checking whether a string is empty or not
```java
class Main{
    public static void main(String[] args){
        String name = "Ernest Sarfo";
        
        System.out.println(name.isEmpty()); // returns a boolean
    }
}
```

### Converting from uppercase to lowercase and vice versa
```java
class Main{
    public static void main(String[] args){
        String name = "Ernest Sarfo";
        
        System.out.println(name.toUpperCase());
        System.out.println(name.toLowerCase());
    }
}
```

### String comparisons
Note that when you use "==" to compare two strings, it just checks if the object on the right side is the same as the object on the left side, which may not be true for most cases. Especially when the strings were created with the "new" keyword. So to actually check whether the content of two strings are equal, you can use the "equals()" method. see below
```java
class Main{
    public static void main(String[] args){
        String string1 = new String("abc");
        String string2 = new String("abc");
        
        System.out.println(string1.equals(string2)); // outputs true
        
    }
}
```
The above method is case sensitive. Which means if string1 is "ABC" and string2 is "abc", the above method will output false. To compare the strings while ignoring their cases, you can use the "equalsIgnoreCase()" method. see below
```java
class Main{
    public static void main(String[] args){
        String string1 = new String("abc");
        String string2 = new String("ABC");

        System.out.println(string1.equalsIgnoreCase(string2)); // outputs true
    }
}
```

### Replacing part of a string 
use the .replace() method.
```java
class Main{
    public static void main(String[] args){
        String today = "The sky is blue";
        
        System.out.println(today.replace("blue", "red"));
    }
}
```

### Check if a string contains a substring or not
use the .contains() method.
```java
class Main{
    public static void main(String[] args){
        String today = "The sky is blue";

        System.out.println(today.contains("sky")); // outputs true
    }
}
```