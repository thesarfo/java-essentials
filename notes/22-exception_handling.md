There are 3 types of exceptions in Java. There are checked, unchecked and errors.

1. Checked exception: You have to handle them using try catch. ie ClassNotFoundException, IOException, InterruptedException etc
2. Unchecked: Its optional to handle or not handle them. ie ArithmeticException, IndexOutOfBoundsException, NullPointerException.

### Exception Hierarchy
    Object 
    |
    |
    Throwable
    |
    |   
    Exception -------Error
    |
    |
    ClassNotFoundException, InterruptedException, IOException, NumberFormatException, RuntimeException
                                                                                            |
                                                                                            |
                                                                                        ArithmeticException, IndexOutofBoundsException, NullPointerException

## Exception Class Methods
1. String getMessage()
2. String toString() // called automatically by sys.out.println
3. void printStacktrace


## Custom Exceptions
ALl custom exceptions must inherit from the Exception class 
```java
class MinBalanceException extends Exception{
    public String toString(){
        return "Minimum balance should be 1.5k, try again with a smaller amount";
    }
}
```

## Throwing exception
For a method to throw an object of the Exception class we need to use the "throws" keyword
```java
static void method1() throws Exception{
    throw new Exception();
    
    // you can wrap the throw statements above in a try catch.
}
```