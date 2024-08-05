
```java
public class Main{
    public static void main(String[] args){
        int result = Math.round(1.1F); // rounds the float to the nearest nt
        System.out.println(result);
        
        int result1 = (int)Math.ceil(1.1F);  // returns 2
        
        int result2 = (int)Math.floor(1.1F); // returns 1
        
        int result3 = Math.max(1, 2); // returns the max value, i.e 2.
        int result5 = Math.min(1, 2); // returns the min value, i.e 1.
        
        double result5 = Math.random(); // returns a random value between 0 and 1, it returns a double
        double result6 = Math.random() * 100; // returns a random value btn 0 and 100
        
        int result7 = (int) Math.round(Math.random() * 100); // we want our random value to be returned as an int instead of a double, so we first round it, and then we convert its type to an (int) and store it in an int variable.
        int result8 = (int) (Math.random() * 100); // does the same thing above but we don;t round the value this time
        
    }
}
```