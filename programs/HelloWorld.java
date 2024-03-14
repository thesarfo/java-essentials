package programs;



public class HelloWorld {
    public static void main(String[] args){
        User user = new User("Ernest Sarfo", "2000-01-31");

        Book cinderella = new Book("Cinderella", "Walt Disney", 270);
        AudioBook dracula = new AudioBook("Dracula", "Bram Stoker", 30000);

        System.out.println(dracula.toString());

    }
}


