import java.util.ArrayList;
import java.util.List;

public class Film {
    private String name;
    private int year;
    private int rating;

    public List<String> comments = new ArrayList<String>();
   // int commentsCounter = 0;


    public Film(String name, int year, int rating, String comment) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.comments.add(comment);
    }

    public Film(String name, int year, int rating) {
        this.name = name;
        this.year = year;
        this.rating = rating;
    }

    public Film(String name, int year) {
        this.name = name;
        this.year = year;
    }





}
