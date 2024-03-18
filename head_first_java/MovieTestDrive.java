package head_first_java;


class Movie{
    String title;
    String genre;
    int rating;

    void playIt(){
        System.out.println("Playing the movie");
    }
}
public class MovieTestDrive {
    public static void main(String[] args){
        Movie one = new Movie();
        one.title = "one with the stock";
        one.genre = "Tragic";
        one.rating = -2;

        Movie two = new Movie();
        two.title = "Lost in Cubicle Space)";
        two.genre = "Comedy";
        two.rating = 5;
        two.playIt();
    }
}