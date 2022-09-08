import java.io.*;
import java.util.Comparator;

public class Film {
    private String name;
    private int year;
    private double rating;
    private String category;

   // public List<String> comments = new ArrayList<String>();

    // int commentsCounter = 0;
    public Film() {
this.category = "l";
    }

    public Film(String name, int year, double rating) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.category = "l";
    }
    public Film(String name, int year, double rating, String category) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.category = category;
    }

    public Film(String name, int year) {
        this.name = name;
        this.year = year;
        this.category = "l";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    /*public void addComment(String comment) {
        comments.add(comment);
    }

    public void ShowComments() {
        for (int i = 0; i < this.comments.size(); i++) {
            System.out.println(i + comments.get(i) + "\n");
        }
    }*/

    public void writeFilm() throws IOException {

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(new File("src/films.txt"), true))) {

            bufferedWriter.write(this.name + "," + this.year + "," + this.rating + "," +this.category);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            // JOptionPane.showMessageDialog(null, "Film added!", "Congrats!", JOptionPane.PLAIN_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static Film readFilm(File file) throws IOException, ClassNotFoundException {
        Film result = null;
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (Film) ois.readObject();
        }
        return result;
    }*/

    @Override
    public String toString() {
        return "Film{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }

    public int compareYearTo(Film compareFilm) {
        //ascending order
        int compareYear = ((Film) compareFilm).getYear();
        return this.year - compareYear;
        //descending order
        //return compareYear - this.year;

    }

    public static Comparator<Film> FilmRatingComparator
            = new Comparator<Film>() {

        @Override
        public int compare(Film o1, Film o2) {
            return Double.compare(o1.getRating(), o2.getRating());
        }
    };
    public static Comparator<Film> FilmNameComparator
            = new Comparator<Film>() {

        @Override
        public int compare(Film f1, Film f2) {
            String fName1 = f1.getName().toUpperCase();
            String fName2 = f2.getName().toUpperCase();

            //ascending order
            return fName1.compareTo(fName2);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }
    };


}

