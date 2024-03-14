package programs;

public class Book {
    public String title;
    public String author;

    public String toString(){
        return String.format("%s by %s", this.title, this.author);
    }
}
