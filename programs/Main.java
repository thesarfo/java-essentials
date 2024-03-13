package programs;

//class Main {
//    public static void main(String[] args){
//        int age;
//        age = 21;
//
//        System.out.println("I am " + age + " years old");
//    }
//}



//class Main{
//    public static void main(String[] args){
//        // implicit type conversion, the compiler does it for us
////        int number1 = 5;
////        double number2 = number1;
//
//        //explicit type conversion
//        double number1 = 5.8;
//        int number2 = (int)number1;
//
//        System.out.println(number2);
//    }
//}



//class Main{
//    public static void main(String[] args){
//        double number1 = 12;
//        int number2 = 6;
//
//        // addition
//        System.out.println(number1 + number2);
//
//        // subtraction
//        System.out.println(number1 - number2);
//
//        // multiplication
//        System.out.println(number1 * number2);
//
//        // div
//        System.out.println(number1 / number2);
//
//        // modulus
//        System.out.println(number1 % number2);
//
//    }
//}
//


//class Main{
//    public static void main(String[] args){
//
//        String literalString1 = "abc";
//        String literalString2 = "abc";
//
//        String objectString1 = new String("efg");
//        String objectString2 = new String("efg");
//
//        System.out.println(literalString1 == literalString2);
//        System.out.println(objectString1 == objectString2);
//    }
//}



//class Main{
//    public static void main(String[] args){
//        String name = "Ernest";
//        String country = "ghana";
//        int age = 21;
//        double gpa = 3.88;
//
//        String formattedString = String.format("My name is %s. I am from %s and I am %d years old. My gpa is also %f",
//                name, country,
//                age, gpa);
//
//        System.out.println(formattedString);
//
////        System.out.println("Hello I am " + name + ", I am from " + country + " and I am " + age + " years old");
//    }
//}



//class Main{
//    public static void main(String[] args){
//        String name = "Ernest Sarfo";
//
//        System.out.println(name.length());
//        System.out.println(name.isEmpty());
//    }
//}
//import java.util.Scanner;
//
//class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("What is your name? ");
//        String name = scanner.nextLine();
//
//        System.out.printf("Hello %s. How old are you? ", name);
//        int age = scanner.nextInt();
//
//        System.out.printf("I see. you are %d years old", age);
//
//        scanner.close();
//    }
//}



//import java.util.Arrays;
//public class Main{
//    public static void main(String[] args){
//        char vowels[] = new char[5];
//
//        vowels[0] = 'a';
//        vowels[1] = 'e';
//        vowels[2] = 'i';
//        vowels[3] = 'o';
//        vowels[4] = 'u';
//
//        System.out.println(Arrays.toString(vowels));
//    }
//}



//import java.util.Arrays;
//
//public class Main{
//    public static void main(String[] args){
//        char vowels[] = {'a', 'e', 'i', 'o', 'u'};
//
//        char key = 'o';
//
//        Arrays.sort(vowels); // binarysearch only works on sorted arrays
//
//        int foundItemIndex = Arrays.binarySearch(vowels, key); // return the index of the key if found
//
//        System.out.println(foundItemIndex);
//    }
//}



//class Main{
//    public static void main(String[] args){
//        for (int number = 1; number <= 10; number++){
//            System.out.println(number );
//        }
//    }
//}


//class Main{
//    public static void main(String[] args){
//        int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//
//        for (int index = 0; index < numbers.length; index++){
//            System.out.println((numbers[index]));
//        }
//
//    }
//}
//
//
//class Main{
//    public static void main(String[] args){
//        for(int number = 1; number <= 10; number++){
//            for(int multiplier = 1; multiplier < 10; multiplier++ ){
//                System.out.printf("%d x %d = %d \n", number, multiplier, number*multiplier);
//            }
//        }
//    }
//}


//class Main{
//    public static void main(String[] args){
//        int numbers[] = {1, 2, 3, 4, 5};
//
//        for(int number : numbers){
//            System.out.println(number);
//        }
//    }
//}
//
//
//class Main{
//    public static void main(String[] args){
//        int number = 5;
//        int multiplier = 1;
//
//        while(multiplier <= 10){
//            System.out.printf("%d x %d = %d \n", number, multiplier, number * multiplier);
//
//            multiplier++;
//        }
//
//    }
//}