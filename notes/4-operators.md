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