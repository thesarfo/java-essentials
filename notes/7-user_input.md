To take input from the user, you need a bunch of stuff. See below
1. scanner object (which has to be imported from java.util.Scanner)
2. scanner.nextLine() - used to take strings as input from a user
3. scanner.close() - indicates that you've stopped receiving input

```java
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your name? ");
        String name = scanner.nextLine();

//        System.out.println(String.format("Hello %s", name));
//        System.out.print(String.format("Hello %s", name));
        System.out.printf("Hello %s. How old are you? ", name);
        int age = scanner.nextInt();
        
        scanner.nextLine(); // cleans up the input buffer. refer to 1:29:00 of the youtube tut
        
        // alternatively, you can use nextLine() to take all the input and convert them to the type you like.
        System.out.printf("Price of the last thing you bought? ");
        int price = Integer.parseInt(scanner.nextLine());
        
        System.out.printf("I see. you are %d years old", age);

        scanner.close();
    }
}
```
You can use "System.out.print" to make sure that everything is on the same line. It works the same as the "System.out.println" just that it doesn't have a newline character at the end of it.

Instead of using the String.format() inside the println and print functions whenever you want to output a string with format specifiers, there is another function called "printf" that makes you do that right out of the box, without you having to use the "String.format()" everytime you want to use some format specifiers