package programs;

public class AudioBook extends Book {
    private  int runTime;

    AudioBook(String title, String author, int runTime){
        super(title, author, 0);
        this.runTime = runTime;
    }
}
