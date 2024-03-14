package programs;

import java.time.LocalDate;

public class HelloWorld {
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