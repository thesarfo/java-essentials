There are about 4 loops in java. The first one is a for loop. see below
## For Loop
```java
class Main{
    public static void main(String[] args){
        for (int number = 1; number <= 10; number++){
            System.out.println(number);
        }
        // outputs 1 to 10
    }
}
```
1. We initialize a variable 
2. Check against a condition
3. What you want to do if the condition is true(print the number)
4. After executing the loop body, what we wanna do

### Using a for loop to loop over an array
```java
class Main{
    public static void main(String[] args){
        int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        
        for (int index = 0; index < numbers.length; index++){
            System.out.println((numbers[index]));
        }
        
    }
}
```

We can also use the loop to find the sum of all the numbers in the array. see below
```java
class Main{
    public static void main(String[] args){
        int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        
        for (int index = 0; index < numbers.length; index++){
            sum += numbers[index];
        }
        System.out.println(sum);
    }
}
```

We can also print out multiplication tables of any number using nested for loops.
```java
class Main{
    public static void main(String[] args){
        for(int number = 1; number <= 10; number++){
            for(int multiplier = 1; multiplier <= 10; multiplier++ ){
                System.out.printf("%d x %d = %d \n", number, multiplier, number*multiplier);
            }
        }
    }
}
```

There is another version of the for loop that makes looping over collections like arrays very easy. see below
```java
class Main{
    public static void main(String[] args){
        int numbers[] = {1, 2, 3, 4, 5};

        for(int number : numbers){
            System.out.println(number);
        }
    }
}
```
What this loop simply says is that, for every integer number in the numbers array, we want to print out that number. Note that the 'int number' in the for loop matches the type 'int' of the numbers array.


## While Loop
```java
class Main{
    public static void main(String[] args){
        int number = 5;
        int multiplier = 1;
        
        while(multiplier <= 10){
            System.out.printf("%d x %d = %d \n", number, multiplier, number * multiplier);
            
            multiplier++;
        }
    }
}
```
The difference btn the while and for loops is that the for loops have the initialization, condition, loop body, and the update. But a while loop only has the condition. The initializations should be done before the while loop, and also the update should be done in the loop body.


## Do while
It works similarly to a while loop but works differently. see below
```java
class Main{
    public static void main(String[] args){
        int number = 5;
        int multiplier = 1;
        
        do{
            System.out.printf("%d x %d = %d \n", number, multiplier, number * multiplier);
            
            multiplier++;
        } while ( multiplier <= 10);
    }
}
```
The main difference between this and a regular while loop is that in a do while loop, the loop body will be executed, and then the condition will be checked, whereas it works the other way round in a while loop.

