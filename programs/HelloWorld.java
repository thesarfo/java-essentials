package programs;



public class HelloWorld {
    public static void main(String[] args){
        User user = new User("Ernest Sarfo", "2000-01-31");

        Book book = new Book("Cinderella", "Walt Disney");

        user.borrow(book);

        System.out.printf("Hello %s, you were born in %s, and he is now %d years old. \n", user.getName(), user.getBirthday(), user.age());

        System.out.printf("%s has borrowed these books: %s", user.getName(), user.borrowedBooks());
    }
}