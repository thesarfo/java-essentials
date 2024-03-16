```java
import java.text.NumberFormat;

public class Practice {
    public static void main(String[] args){
        // formatting a number as a currency
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        String result = currency.format(1234567.891);
        System.out.println(result); // returns the value with its relevant currency unit

        // formatting a number as a percentage
        NumberFormat percent = NumberFormat.getPercentInstance();
        String result1 = percent.format(0.1);
        System.out.println(result1); // returns 10%
    }
}
```