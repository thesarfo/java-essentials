**Key Points** about the HashSet class

**Unordered and Unsorted**
The elements in a HashSet are not ordered or sorted. There is no guarantee that the order will remain constant over time.

**Unique Elements**
HashSet does not allow duplicate values. If you try to add the same value again, it will not replace the old value.

**Null Elements**
HashSet allows one null value.

**Non-Synchronized**
HashSet is non-synchronized, meaning it is not thread-safe and multiple threads can access it simultaneously.

**Underlying Data Structure**
HashSet is implemented in terms of a hash table and internally uses HashMap to store the elements.

**Performance**
The add, remove, and contains methods have constant time complexity O(1).

**Implements Set Interface**
HashSet implements the Set interface, inheriting all its methods.


### Creating a HashSet
```java
HashSet<String> set = new HashSet<>()
```

### Adding elements to a HashSet
This is done using the ```add``` method
```java
HashSet<String> set = new HashSet<>();

// add elements
set.add("Java");
set.add("Python")
set.add("JavaScript")

// display the elements
for (String language : set){
    System.out.println(language);
}
``` 

### Create HashSet from Another Collection
```java
List<Integer> list = new ArrayList<>();
list.add(5);
list.add(10);
list.add(15);
list.add(20);
list.add(25);

List<Integer> list2 = new ArrayList<>();
list2.add(3);
list2.add(6);
list2.add(9);
list2.add(12);
list2.add(15);

// Creating a HashSet from another collection (ArrayList)
Set<Integer> set = new HashSet<>(list);

// Adding all the elements from an existing collection to a HashSet
set.addAll(list2);

System.out.println(set);

Output = [3, 5, 6, 9, 10, 12, 15, 20, 25]
```


### Access Elements
In Java, the HashSet class does not have a `get()` method to access its elements as it is not an indexed collection. However, you can iterate through a HashSet to access its elements.
```java
public class Main {
    public static void main(String[] args) {
        // Creating a HashSet
        HashSet<String> set = new HashSet<>();

        // Adding new elements to the HashSet
        set.add("Java");
        set.add("Python");
        set.add("JavaScript");

        // Accessing elements using an iterator
        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()){
            String language = iterator.next();
            System.out.println(language);
        }
    }
}
```

### Removing Elements
We can remove elements from HashSet using these methods:

**remove(Object o)**: This method removes a single specified element from the set.
**removeAll(Collection c)**: This method removes all elements from the set that are contained in the specified collection.
**removeIf(Predicate<? super E> filter)**: This method removes all elements from the set that satisfy the given predicate.
```java
public class Main {
    public static void main(String[] args) {
        // Creating a HashSet
        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList("Apple", "Banana", "Cherry", "Date", "Elderberry"));

        System.out.println("Original HashSet: " + set);

        // Using remove(Object o)
        set.remove("Cherry");
        System.out.println("\nAfter remove('Cherry'): " + set);

        // Using removeAll(Collection c)
        set.removeAll(Arrays.asList("Apple", "Banana"));
        System.out.println("\nAfter removeAll(Arrays.asList('Apple', 'Banana')): " + set);

        // Using removeIf(Predicate<? super E> filter)
        set.removeIf(fruit -> fruit.startsWith("E"));
        System.out.println("\nAfter removeIf(fruit -> fruit.startsWith('E')): " + set);
    }
}
```