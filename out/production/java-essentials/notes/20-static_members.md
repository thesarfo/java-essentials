1. Static variables
2. Static methods
3. Static nested classes
4. Static blocks


### Static variables
Static variables are used to represent metadata. So basically, they are used to represent the information or data related to the class.

If you have only data related to a class, use a "Static Variable".

If the data you have needs extra processing, then you use a "Static Method".

If you have a lot of data that can be grouped together, you can use the "Static Nested Class".

Static members are shared by all the members(objects) of the class. Therefore, if any member of the class modifies the static member, the value of the static member will change.

Static members can also be accessed using just the class name, with the dot notation. you dont have to create an object.


```java
class HondaCity{
    static long price = 10;
    int a, b; // the static method below cannot access these variables, since they are not static

    static double OnRoadPrice(String city){
        switch (city){
            case "delhi":
                return price + price * 0.1;
            case "mumbai":
                return price + price * 0.09;
        }
        return price;
    }
}

public class StaticMembers {
    public static void main(String[] args) {
        HondaCity n1 = new HondaCity();
        HondaCity h2 = new HondaCity();
        System.out.println(HondaCity.price); // access static member from the class itself
    }
}
```

### Static Methods
1. They belong to a class
2. They are common for all the objects
3. They can be called either using the class name or object name
4. Static methods can only access static variables/members, thats it.


### Static Nested Classes
In the above example we have just one static method. If we have multiple static methods, we can create a static class for them, that way..members of the static class can be accessed independently of the outer class.

NB: You cannot used the 'this' or 'super' keywords in a static method or static classes.


