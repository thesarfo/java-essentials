```java
public class Main{
    public static void main(String[] args){
        if (condition){
            // do this
        } else if (another_condition) {
            // do this
        } else{
            // do this
        }
    }
}
```

You can have multiple else if statements and nested if statements, but they can become too verbose and very complex. There is a simpler way called "switch-cases" that can do the same thing as a complicated if else ladder
```java
public class Main{
    public static void main(String[] args){
        switch(variable_you_wanna_check){
            case 'first':
                // do something
                break; 
            case 'second':
                // do something
                break;
            case 'third':
                // do something
                break;
            default: {
                // do this if all the above cases aren't met
                }
        }
    }
}
```