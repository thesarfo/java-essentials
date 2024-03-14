package programs;



public class HelloWorld {
    public static void main(String[] args){
        User user = new User("Ernest Sarfo", "2000-01-31");

        Book cinderella = new Book("Cinderella", "Walt Disney", 270);
        AudioBook dracula = new AudioBook("Dracula", "Bram Stoker", 30000);
        Ebook jeeves = new Ebook("Carry on Jeeves", "P.G Wodehouse", 280, "PDF");

        System.out.println(jeeves.toString());

    }
}


