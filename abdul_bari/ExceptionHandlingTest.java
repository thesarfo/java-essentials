package abdul_bari;

public class ExceptionHandlingTest {
    public static void main(String[] args) {
        int a, b, c;

        try{
            a = 5;
            b = 0;
            c = a / b;

            System.out.println("Result is " + c);
        } catch (ArithmeticException e){
            System.out.println("Division by zero error " + e);
        }

    }
}
