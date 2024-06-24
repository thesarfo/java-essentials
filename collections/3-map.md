### Key Points about Map Interface

- Map interface is a part of Java Collection Framework, but it doesn’t inherit `Collection` Interface.

- A Map cannot contain duplicate keys:  Each key can map to at most one value. It models the mathematical function abstraction.

- Each key at most must be associated with one value.

- Each key-value pairs of the map are stored as `Map.Entry` objects. `Map.Entry`is an inner interface of Map interface.

- The Java platform contains three general-purpose Map interface implementations: `HashMap`, `TreeMap`, and `LinkedHashMap`.

- Order of elements in a map is implementation dependent. `HashMap` doesn’t maintain any order of elements. `LinkedHashMap` maintains insertion order of elements. Where as `TreeMap` places the elements according to the supplied `Comparator`.

- The `Map` interface provides three methods, which allows map’s contents to be viewed as a set of keys (keySet() method), a collection of values (values() method), or set of key-value mappings (entrySet() method).

##### HashMap implementation of a Map
```java
public class CreateHashMapExample {
    public static void main(String[] args) {
        // Creating a HashMap
        Map<String, Integer> numberMapping = new HashMap<>();

        // Adding key-value pairs to a HashMap
        numberMapping.put("One", 1);
        numberMapping.put("Two", 2);
        numberMapping.put("Three", 3);

        // Add a new key-value pair only if the key does not exist in the HashMap, or is mapped to `null`
        numberMapping.putIfAbsent("Four", 4);

        // Access element by its key
        numberMapping.get("One");

        // Remove element by its key
        numberMapping.remove("Two");

        // Getting the size of the map
        int size = numberMapping.size();

        System.out.println(numberMapping);
    }
}
```

### Looping Through a HashMap
There are different ways we can loop through a HashMap:

1. Using entrySet and a for-each loop
2. Using keySet and a for-each loop
3. Using values and a for-each loop
4. Using an Iterator
5. Using Java 8's forEach method


The below program demonstrates the different ways to iterate over a HashMap

```java
public class Main {
    public static void main(String[] args) {
        // Creating a HashMap
        HashMap<String, String> map = new HashMap<>();
        map.put("Apple", "Red");
        map.put("Orange", "Orange");
        map.put("Banana", "Yellow");

        // Using `entrySet` and a `for-each` loop:
        System.out.println("Using `entrySet` and a `for-each` loop:");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        System.out.println();

        // Using `keySet` and a `for-each` loop:
        System.out.println("Using `keySet` and a `for-each` loop:");
        for (String key : map.keySet()) {
            System.out.println("Key = " + key + ", Value = " + map.get(key));
        }
        System.out.println();

        // Using `values` and a `for-each` loop:
        System.out.println("Using `values` and a `for-each` loop:");
        for (String value : map.values()) {
            System.out.println("Value = " + value);
        }
        System.out.println();

        // Using an `Iterator`:
        System.out.println("Using an `Iterator`:");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        System.out.println();

        // Using Java 8's `forEach` method:
        System.out.println("Using Java 8's `forEach` method:");
        map.forEach((key, value) -> System.out.println("Key = " + key + ", Value = " + value));
    }
}
```

### Output of the Loops
```shell
Using `entrySet` and a `for-each` loop:
Key = Apple, Value = Red
Key = Orange, Value = Orange
Key = Banana, Value = Yellow

Using `keySet` and a `for-each` loop:
Key = Apple, Value = Red
Key = Orange, Value = Orange
Key = Banana, Value = Yellow

Using `values` and a `for-each` loop:
Value = Red
Value = Orange
Value = Yellow

Using an `Iterator`:
Key = Apple, Value = Red
Key = Orange, Value = Orange
Key = Banana, Value = Yellow

Using Java 8's `forEach` method:
Key = Apple, Value = Red
Key = Orange, Value = Orange
Key = Banana, Value = Yellow
```

<br>
Below are the Methods available in the Map Interface
<br>
<br>
<img src="/images/map-interface.png">