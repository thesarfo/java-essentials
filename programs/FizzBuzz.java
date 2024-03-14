package programs;

import java.util.Scanner;

public class FizzBuzz {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        // A way of solving this
        if (number % 3 == 0 && number % 5 == 0){
            System.out.println("FizzBuzz");
            }
        else if (number % 3 == 0){
            System.out.println("Fizz");
        }
        else if (number % 5 == 0){
            System.out.println("Buzz");
        }
        else{
            System.out.println(number);
        }

        // Another way of solving this - Following the DRY principle
        if (number % 5 == 0){
            if (number % 3 == 0){
                System.out.println("FizzBuzz");
            }
            else{
                System.out.println("Fizz");
            }
        }
        else if ( number % 3 == 0){
            System.out.println("Buzz");
        }
        else{
            System.out.println(number);
        }
    }
}
