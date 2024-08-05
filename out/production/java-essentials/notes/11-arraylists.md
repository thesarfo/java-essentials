So far, we know that we can use array to store a bunch of values. But perhaps the biggest issue with arrays is that, we cannot resize them, unless of course we create a copy of the array and change the length of that copy. Because of this, a type called arraylist exists for us. An arraylist works like a dynamic array.

To use an arraylist, you have to import it from 'java.util.arraylist'. And then to declare an array list, you use the ArrayList keyword, followed by a pair of angle brackets which contain the type of data to be stored in that array list. That datatype must be typed in full(bcuz is it a reference type form of the regular int). And then add the name of the arraylist, and then assign it to a new object of the ArrayList class which also has a pair of square brackets and its data types ending with brackets. see below

```java
import java.util.ArrayList;
import java.util.Comparator; // we use this for the sorting

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        numbers.add(1); // adding a value to an arraylist
        numbers.add(2); // adding a value to an arraylist
        numbers.add(3); // adding a value to an arraylist
        numbers.add(4); // adding a value to an arraylist
        numbers.add(5); // adding a value to an arraylist

        numbers.remove(4); // index of the element we want to remove i.e 4
        numbers.remove(Integer.valueOf(4)); // removes the element 4 from the arraylist

        numbers.clear(); // clears everything and makes it an empty arraylist
        
        numbers.indexOf(value); // gets the index of a value in the arraylist

        numbers.set(1, Integer.valueOf(30)); // updates the element at index one with 30

        numbers.sort(Comparator.naturalOrder()); // sorts the arraylist in ascending order
        numbers.sort(Comparator.reverseOrder()); // sorts the arraylist in descending order
        
        System.out.println(numbers.size()); // prints out the size or the length of the arraylist
        
        System.out.println(numbers.contains(Integer.valueOf(1))); // prints true if the arraylist contains the integer 1
        
        System.out.println(numbers.isEmpty()); // prints true if the arraylist is empty

        System.out.println(numbers.toString()); // convert it to string to print it out
        System.out.println(numbers.get(2)); // prints out the element in the arraylist at index 2 
    }
}
```
Arraylists are dynamic arrays, so you can add as many elements as you want.


### For each loop
```java
class Main{
    public static void main(String[] args){
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        
        numbers.forEach(number -> {
            System.out.println(number * 2);
        });
        System.out.println(numbers.toString());
    }
}
```
The logic is quite simple, for each number in the numbers arraylist, we want to double the number and print it out.