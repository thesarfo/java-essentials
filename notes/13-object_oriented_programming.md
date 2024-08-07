The concept of OOP simply involves modelling your software around real world objects.

For instance, we can build a book borrowing system where a user can come register and login, check for a book's availability, borrow it if it's available, and return it on time. They can also check the list of books they have borrowed so far. 

In this aforementioned system, there can be two objects. The user and the book. We can store info like the user's name and birthday in the user object, and the book name and its author in the book object.

### User class
First we have to create a file(or class) called User.java, where we will initialize the User class, and its properties.

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

Note that when creating a new object we did it like this..
```java
        User youngerUser = new User();
```
There is nothing wrong with it, but note how we have repeated the 'User' keyword twice, this makes the code a bit noisy. To streamline this, we can use the var keyword.
```java
        var youngerUser = new User();
```


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


### Constructors
So far we have declared all our methods and properties as public, but that is not something you should be doing a lot. There is something called a constructor method, which is builtin, which is responsible for initializing our properties with their default values. So we can customize the constructor method and make it do things. 

A constructor is initialized by just typing the constructor name(which is the name of the class), and then specify what you want the class to contain, when it is being created. Then you need to store those stuff inside properties you have initialized at the class level
see below
```java
// User.java
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User {
    public String name; // class level property
    public LocalDate birthDay; // class level property
    public ArrayList<Book> books = new ArrayList<Book>();

    // constructor method
    User(String name, String birthDay){ // we want the name and birthday properties when a user class is created.
        this.name = name; // store the name argument in the class level property(name)
        this.birthDay = LocalDate.parse(birthDay); // store the birthday argument in the class level property(birthday)
    }

    public void borrow(Book book){
        this.books.add(book);
    }

    public int age(){
        Period age = Period.between(this.birthDay, LocalDate.now());

        return age.getYears();
    }
}
```

Now when we are creating an object of the user class above, we just have to put in the properties directly when creating it. Since the constructor method expects it. see below
```java
// Main.java

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
        User user = new User("Ernest Sarfo", "2000-01-31"); // put the values within the constructor call

        Book book = new Book();

        book.title = "Cinderella";
        book.author = "Walt Disney";

        user.borrow(book);

        System.out.printf("Hello %s, you were born in %s, and he is now %d years old. \n", user.name, user.birthDay.toString(), user.age());
        System.out.printf("%s has borrowed these books: %s", user.name, user.books.toString());
    }
}
```

Now since we can initialize the user object with the name and birthday right at the creation process, we can actually make the name and birthday properties private.
```java
// User.java
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User {
    private String name;
    private LocalDate birthDay;
    public ArrayList<Book> books = new ArrayList<Book>();

    // constructor method
    User(String name, String birthDay){
        this.name = name;
        this.birthDay = LocalDate.parse(birthDay);
    }

    public void borrow(Book book){
        this.books.add(book);
    }

    public int age(){
        Period age = Period.between(this.birthDay, LocalDate.now());

        return age.getYears();
    }
}
```

This means that we can no longer do things like 'user.birthDay' or 'user.Name'. But we still need to know the name and birthday of the user. For that we have something called getters, which simply return the value of our properties. see below
```java
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class User {
    private String name;
    private LocalDate birthDay;
    public ArrayList<Book> books = new ArrayList<Book>();


    // constructor method
    User(String name, String birthDay){
        this.name = name;
        this.birthDay = LocalDate.parse(birthDay);
    }
    
    // getters to return the name and birthday
    public String getName(){
        return this.name;
    }
    public String getBirthday(){
        return this.birthDay.toString();
    }

    public void borrow(Book book){
        this.books.add(book);
    }

    public int age(){
        Period age = Period.between(this.birthDay, LocalDate.now());

        return age.getYears();
    }
}
```

Now since we have implemented our getters, we can now call our getter methods on our object. ie 'user.getName()' and 'user.getBirthday()'. see below
```java
// Main.java
public class Main {
    public static void main(String[] args){
        User user = new User("Ernest Sarfo", "2000-01-31");


        Book book = new Book();

        book.title = "Cinderella";
        book.author = "Walt Disney";

        user.borrow(book);

        System.out.printf("Hello %s, you were born in %s, and he is now %d years old. \n", user.getName(), user.getBirthday(), user.age());
        System.out.printf("%s has borrowed these books: %s", user.getName(), user.books.toString()); // note how we called the getter methods to return the name and birthday of ur user object
    }
}
```

### Abstraction
Now that we have made the name and birthday properties private, our Main class doesn't have access to the name and birthday values. In fact, nobody has access to the name and birthday values outside the User class...and its values cannot be changed outside the User class as well. 

Another thing is that now we can pass the name and birthday as strings when creating our user object, the Main class has no business in knowing what datatype our name and birthDay class are, all it has to know is that when we pass the name and birthDay in string format, our object will be created.

This is called Abstraction - what it means is that we are hiding the complexities of the User class behind our getter methods and our constructor methods.

We can also modify our Book class with constructors and getters
```java
// Book.java
public class Book {
    private String title;
    private String author;

    Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public String toString(){
        return String.format("%s by %s", this.title, this.author);
    }
}
```

And we can update our Main class to reflect that change.
```java
// Main.java
public class HelloWorld {
    public static void main(String[] args){
        User user = new User("Ernest Sarfo", "2000-01-31");

        Book book = new Book("Cinderella", "Walt Disney");

        user.borrow(book);

        System.out.printf("Hello %s, you were born in %s, and he is now %d years old. \n", user.getName(), user.getBirthday(), user.age());

        System.out.printf("%s has borrowed these books: %s", user.getName(), user.borrowedBooks());
    }
}
```

### Inheritance
Now lets say we have different types of books, Audiobooks, Ebooks, Paperback books etc. Now all these are types of books, which means they will share some common properties, like title, author, pageCount.

This means that when we are creating an Audiobook class, it needs to inherit some properties from a Book class(which is our parent class), and then we add more properties to it. see below
```java
// AudioBook.java
public class AudioBook extends Book {
    private  int runTime;

    AudioBook(String title, String author, int runTime){
        super(title, author, 0); // the 0 is for pageCount
        this.runTime = runTime;
    }
}
```
Our child class(AudioBook) extends our parent class(Book). and then inside it, we specify the additional properties we want to add to our child class. Then we create a constructor method for our child class, which must include all the properties of our parent class, as well as the additional property we just defined. Then se use the 'super()' keyword to inherit the properties of the parent class, and then set our additional property to the property we've defined at the class level.

We can create an object of our AudioBook class and print it out. see below
```java
// Main.java
public class Main {
    public static void main(String[] args){
        User user = new User("Ernest Sarfo", "2000-01-31");

        Book cinderella = new Book("Cinderella", "Walt Disney", 270);
        AudioBook dracula = new AudioBook("Dracula", "Bram Stoker", 30000);

        System.out.println(dracula.toString());

    }
}

```

### Encapsulation
Here’s an encapsulation starter rule of thumb (all standard disclaimers about rules of thumb are in effect): mark your instance variables private and provide public getters and setters for access control. When you have more design and coding savvy in Java, you will probably do things a little differently, but for now, this approach will keep you safe.

1. Mark instance variables private
2. Mark getters and setters public

1. Encapsulation gives you control over who changes the data in your class and how. 
2. Make an instance variable private so it cant me changed by accessing the variable directly
3. Create a setter to control how other code interacts with our data. For instance, we can add validatioin code inside a setter to make sure the value isnt changed to something invalid
4. Instance variables are assigned values by default
5. Local variables are not assigned a value by default

```java
package head_first_java;


class GoodDog{
    private int size;

    public int getSize(){
        return size;
    }
    public void setSize(int s) {
        this.size = s;
    }
    void bark(){
        if (size > 60){
            System.out.println("Woof Woof");
        } else if (size > 14){
            System.out.println("Ruff Ruff");
        } else {
            System.out.println("Yip Yip");
        }
    }
}
public class GoodDogTestDrive {
    public static void main(String[] args){
        // create new objects of the above class
        GoodDog one = new GoodDog();
        one.setSize(79); // use the setter
        GoodDog two = new GoodDog();
        two.setSize(8);

        System.out.println("Dog one: " + one.getSize()); // use the getter
        System.out.println("Dog two: " + one.getSize());

        one.bark();
        two.bark();
    }
}
```