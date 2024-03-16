So far we only store single values in a variable. But arrays help us to store multiple values inside a single variable. To declare an array, you need the 'char' keyword, followed by the name of the array, along with square braces, and then you assign it to a new object of the char class, along with some square braces and put the size of the array in the square braces.

see below
```java
public class Main{
    public static void main(String[] args){
        char vowels[] = new char[5]; // declaration

        vowels[0] = 'a'; // definition
        vowels[1] = 'e';
        vowels[2] = 'i';
        vowels[3] = 'o';
        vowels[4] = 'u';

        System.out.println(vowels[2]); // print out an element in the array
    }
}
```

When we use the 'println' function to print an element in our array, that element is printed as a string, which means that before we print out our array we have to convert it into a string. You do that by importing 'java.util.Arrays', and then print the array as a string using the 'toString()' method. see below
```java
import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        char vowels[] = new char[5];

        vowels[0] = 'a';
        vowels[1] = 'e';
        vowels[2] = 'i';
        vowels[3] = 'o';
        vowels[4] = 'u';

        System.out.println(Arrays.toString(vowels));
    }
}
```

You can also print out the entire array using a loop etc. We will come to that later

Now instead of declaring and defining your array in separate lines, we can do that all in one line.
```java
import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        char vowels[] = {'a', 'e', 'i', 'o', 'u'}; // declare and define it
        
        vowels[1] = 'f'; // change the element in the array at index 1

        System.out.println(Arrays.toString(vowels));

    }
}
```

### Array Methods

#### Finding the length of an array
```java
public class Main{
    public static void main(String[] args){
        char vowels[] = {'a', 'e', 'i', 'o', 'u'};

        System.out.println(vowels.length);
    }
}
```

#### Sorting an unsorted array
```java
import java.util.Arrays;
public class Main{
    public static void main(String[] args){
        char vowels[] = {'e', 'i', 'a', 'u', 'o'};
        
        int startingIndex = 1;
        int endingIndex = 3;
        
        Arrays.sort(vowels); // sorts the array
        Arrays.sort(vowels, startingIndex, endingIndex); // sorts the array from index 1 to 3(non inclusive)
        
        System.out.println(Arrays.toString(vowels));
    }
}
```

#### Search for a value in an array
```java
import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        char vowels[] = {'a', 'e', 'i', 'o', 'u'};
        char key = 'o';
        
        int startingIndex = 0;
        int endingIndex = 4;
        
        Arrays.sort(vowels); // binarysearch only works on sorted arrays
        
        int foundItemIndex = Arrays.binarySearch(vowels, key); // return the index of the key if found
        
        int anotherFoundItemIndex = Arrays.binarySearch(vowels, startingIndex, endingIndex, key); // you can specify the start and end of where to perform your search
        
        System.out.println(foundItemIndex);
    }
}
```


#### Filling an array
You can decide to fill an array with a value of your choice. see below
```java
import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        char vowels[] = {'a', 'e', 'i', 'o', 'u'};
        
        Arrays.fill(vowels, 'x');
        
        System.out.println(Arrays.toString(vowels)); // outputs [x, x, x, x, x]
        
        // this fill method also works with a starting and ending index, like we have done above
    }
}
```

#### Creating a copy of an array
```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int numbers[] = {1, 2, 3, 4, 5};

        int copyOfNumbers[] = Arrays.copyOf(numbers, numbers.length); // it takes the original array as first arg, and the length of the new array as second arg
        
        int startingIndex = 1;
        int endingIndex = 3; // this can also serve as the length of the new array
        
        int anotherCopyOfNumbers[] = Arrays.copyOfRange(numbers, startingIndex, endingIndex); // same as the one above but you can specify start and end indexes, and you dont specify the length of the new array
        
        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.toString(copyOfNumbers));
    }
}
```

#### Comparing arrays
```java
import java.util.Arrays;

public class Main{
    public static void main(String[] args){
        int numbers[] = {1, 2, 3, 4, 5};
        int copyOfNumbers[] = Arrays.copyOf(numbers, numbers.length);
        
        System.out.println(Arrays.equals(numbers, copyOfNumbers)); // outputs true
        
    }
}
```