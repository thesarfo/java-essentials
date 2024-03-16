Hashmaps are key value pairs, and they are equivalent to dictionaries in python. To use a hashmap you first have to import it from 'java.util.HashMap', and then you type the hashmap keyword, followed by angle brackets, and then the datatype of the key and values, followed by the name of your hashmap, and then assign it to a new object of the HashMap class, which also included the angle brackets and the data types of the key and values, followed by brackets.

```java
import java.util.HashMap;

class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> examScores = new HashMap<String, Integer>();
        
        examScores.put("Math", 75); // put something into a hashmap
        examScores.put("English", 86); // put something into a hashmap
        examScores.put("Psychology", 100); // put something into a hashmap
        
        examScores.putIfAbsent("Science", 66); // add science to the hashmap, since it doesnt exit in our hashmap
        
        examScores.replace("English", 70); // updated the value of the English key
        
        examScores.clear(); // clear the hashmap into an empty curly braces
        
        examScores.remove("Math"); // removes the key value pair for Math
        
        System.out.println(examScores.toString()); // print out a hashmap
        System.out.println(examScores.get("English")); // print out the value for a single key
        System.out.println(examScores.getOrDefault("Religion", -1)); // return the value of the religion key, else return -1 if it doesn't exist
        System.out.println(examScores.size()); // prints the length of the examScores hashmap
        System.out.println(examScores.containsKey("Math")); // returns True if the key exists
        System.out.println(examScores.containsValue(100)); // returns True if the key exists
        System.out.println(examScores.isEmpty()); // returns True if the hashmap is empty
        
    }
}
```

### Looping through a hashmap with for each
```java
class Main{
    public static void main(String[] args){
        HashMap<String, Integer> examScores = new HashMap<String, Integer>();

        examScores.put("Math", 75);
        examScores.put("English", 86);
        examScores.put("Psychology", 100);
        
        examScores.forEach((subject, score) -> {
            System.out.println(subject + "-" + score);
            examScores.replace(subject, score - 10);
        });
        System.out.println(examScores.toString());
    }
}
```
Note that the forEach method for a hashmap takes the key, and the value as args.