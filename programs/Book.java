package programs;

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
