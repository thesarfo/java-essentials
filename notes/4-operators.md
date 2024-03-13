### Arithmetic operators

There are 5 arithmetic operators in java. add, sub, div, mult, and mod operators
```java
class Main{
    public static void main(String[] args){
        int number1 = 13;
        int number2 = 6;
        
        // addition
        System.out.println(number1 + number2);
        
        // subtraction
        System.out.println(number1 + number2);
        
        // multiplication
        System.out.println(number1 * number2);
        
        // div
        System.out.println(number1 / number2);
        
        // modulus
        System.out.println(number1 % number2);

    }
}
```
With the division operator, when you divide an int by an int the result will always be an int. So even tho sometimes, dividing an int by another int might have some remainders, it will still print out a single number. So get around that, you have to divide an int by a double, or an int by a float in order to get such remainders behind the decimal point.


### Relational Operators
They check the relations among multiple operands and return a boolean value as the result.
```java
class Main{
    public static void main(String[] args){
        int number1 = 13;
        int number2 = 6;
        
        // is equal to
        System.out.println(number1 == number2);
        
        // is not equal to
        System.out.println(number1 != number2);
        
        // is greater than
        System.out.println(number1 > number2);
        
        // is less than
        System.out.println(number1 < number2);
        
        // is greater than or equal to
        System.out.println(number1 >= number2);

        // is less than or equal to
        System.out.println(number1 <= number2);

    }
}
```

### Logical operators
and - && - both comparisons have to be true for it to be true
or - || - either comparisons have to be true for it to be true. prints false when both comparisons are false
not - ! - reverses a boolean value
increment - += or ++ - adds a value to a variable
decrement - -= or -- - subtracts a value to a variable

You can add the increment or decrement operators before the variable.