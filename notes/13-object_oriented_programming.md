The concept of OOP simply involves modelling your software around real world objects.

For instance, we can build a book borrowing system where a user can come register and login, check for a book's availability, borrow it if it's available, and return it on time. They can also check the list of books they have borrowed so far. 

In this aforementioned system, there can be two objects. The user and the book. We can store info like the user's name and birthday in the user object, and the book name and its author in the book object.

### User class
First we have to create a file(or class) called User.java, where we will initial the User class, and its properties.

```java
// User.java
import java.time.LocalDate;

public class User {
    public static void main(String[] args) {
        public String name;
        public LocalDate birthDay;
    }
}
```

We have made the User class public, so it can be accessed anywhere in our filesystem. When we create a class and give it properties, by default, all those properties have a value of null. Now we need to create a new user object. How it's done is similar to how we create a string. see below

```java
import java.time.LocalDate;

// Main.java
public class Main {
    public static void main(String[] args) {
        User youngerUser = new User();

        youngerUser.name = "Ernest Sarfo";
        youngerUser.birthDay = LocalDate.parse( "2000-01-31");

        System.out.printf("%s was born on %s", youngerUser.name, youngerUser.birthDay.toString());
    }
}
```

Using something called method, we can implement some dynamic behaviour to our classes. Now lets say we want to calculate the age of our user. We can do that in our user class using a new method.
```java
// User.java
import java.time.LocalDate;
import java.time.Period;

public class User {
    public String name;
    public LocalDate birthDay;

    public int age(){
        Period age = Period.between(this.birthDay, LocalDate.now());

        return age.getYears();
    }
}
```

The period datatype is used to calculate time periods between specified date times, and then we return the age, using the .getYears() method.


#### Notice
The 'this' keyword in the above code simply points to the current object we are referring to. Assuming we created a new User object called olderUser, and then we set the age of the olderUser with 'olderUser.age' now when we print 'olderUser.age', the 'this' keyword knows to print the age value we assigned to the olderUser object.

Now in our main class, we can then print out the age of the user.
```java
// main.java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        User youngerUser = new User();

        youngerUser.name = "Ernest Sarfo";
        youngerUser.birthDay = LocalDate.parse("2000-01-31");

        System.out.printf("Hello %s, you were born in %s, and he is now %d years old", youngerUser.name, youngerUser.birthDay.toString(), youngerUser.age());
    }
}
```
Now we can then print our the age as an integer.


### Book class
Create a new file called Book, where we will define the book class and its properties.
```java
// Book.java
public class Book {
    public String title;
    public String author;

    public String toString(){
        return String.format("%s by %s", this.title, this.author);
    }
}
```
We have created a method called "toString" that will print out the string representation of our book class. This is because since it is a custom class, it doesn't have a builtin toString() method, and so when we print it, it will print out some gibberish large number. So we have to create our own toString() method. Just like python has the '__str__' method.

Now we want to give our user the ability to borrow a book, so in our User class, we create a new method called borrow, which takes an object of the Book class as an argument. We also have to add a books property to our user class, which will be an ArrayList. see below
```java
// User.java

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User {
    public String name;
    public LocalDate birthDay;
    public ArrayList<Book> books = new ArrayList<Book>();

    public void borrow(Book book){
        this.books.add(book);
    }

    public int age(){
        Period age = Period.between(this.birthDay, LocalDate.now());

        return age.getYears();
    }
}
```

Now in our main class, we create a new object of the book class, and we can let the user borrow it. see below
```java
// Main.java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        User user = new User();

        user.name = "Ernest Sarfo";
        user.birthDay = LocalDate.parse("2000-01-31");

        Book book = new Book();

        book.title = "Cinderella";
        book.author = "Walt Disney";

        user.borrow(book);

        System.out.printf("Hello %s, you were born in %s, and he is now %d years old. \n", user.name, user.birthDay.toString(), user.age());
        System.out.printf("%s has borrowed these books: %s", user.name, user.books.toString());
    }
}
```