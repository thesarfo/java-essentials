package programs;

//class Main {
//    public static void main(String[] args){
//        int age;
//        age = 21;
//
//        System.out.println("I am " + age + " years old");
//    }
//}

class Main{
    public static void main(String[] args){
        // implicit type conversion, the compiler does it for us
//        int number1 = 5;
//        double number2 = number1;

        //explcit type conversion
        double number1 = 5.8;
        int number2 = (int)number1;

        System.out.println(number2);
    }
}