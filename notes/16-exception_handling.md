There are 3 types of exceptions in Java. There are checked, unchecked and errors.

1. Checked exception: You have to handle them using try catch. ie ClassNotFoundException, IOException, InterruptedException etc
2. Unchecked: Its optional to handle or not handle them. ie ArithmeticException, IndexOutOfBoundsException, NullPointerException.

To catch an error, you have to wrap your code in a try catch block. see below.
```java
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Practice {
    public static void main(String[] args) {
        try{
            FileReader reader = new FileReader("file.txt");
            System.out.println("File open");
        } catch (FileNotFoundException ex){
            System.out.println("The file does not exist");
        }
    }
}
```
Here we try to read from a file that doesn't exist, and we catch the exception in a catch block and then print a user-friendly message. The 'ex' in the catch block is simply a convention people use in java to denote an exception. So the 'ex' is an instance of the FileNotFoundException class.

But the 'ex' object, can also print out an exception message with the "ex.getMessage()" method.  

## The finally block
```java
import java.io.FileReader;
import java.io.IOException;

public class Practice {
    static FileReader reader = null;
    public static void main(String[] args) {
        try {
            reader = new FileReader("file.txt");
            var value = reader.read(); // read a single value from the file
        } catch (IOException e) {
            System.out.println("Could not read data.");
        } finally {
            if ( reader != null){
                try{
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
```

## Try with resources statement
This is when we initialize a try block with a resource in its parenthesis. That way we dont need to specify what we want to do with the resource in the finally block. Neither do we need to initialize the resource before the try block. Like we have done above
```java
import java.io.FileReader;
import java.io.IOException;

public class Practice {
    static FileReader reader = null;
    public static void main(String[] args) {
        try {
            reader = new FileReader("file.txt");
            var value = reader.read(); // read a single value from the file
        } catch (IOException e) {
            System.out.println("Could not read data.");
        } finally {
            if ( reader != null){
                try{
                    reader.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
```


## Throwing exceptions
```java
public class Account{
    public void deposit(float value){
        if (value <= 0){
            throw new IllegalArgumentException();
        }
    }
}

public class Demo{
    public static void show(){
        var account = new Account();
        account.deposit(-1); // will throw an exception to the user
    }
}
```

What if we want to throw a checked exception. We can do that as well

```java
import java.io.IOException;

public class Account {
    public void deposit (float value) throws IOException{
        if (value <= 0) {
            throw new IOException();
        }
    }
}

public class Demo{
    public static void show(){
        var account = new Account();
        try{
        account.deposit(-1);
    } catch (IOException e){
            e.printStackTrace();
        }
}
```