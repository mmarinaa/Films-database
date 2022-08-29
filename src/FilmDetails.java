import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmDetails {
    /**
     * Reads the file one line at a time. Each line will is that split up and translated into a Film object
     */
    public List<Film> getFilmFromFile(BufferedReader reader) throws IOException {
        List<Film> films = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] filmDetails = line.trim().split(",");
            Film film = new Film();


                SetFilmName(filmDetails, film);

                SetFilmYear(filmDetails, film);

                SetFilmRating(filmDetails, film);

            films.add(film);


        }
        return films;

    }

    private void SetFilmYear(String[] filmDetails, Film film) {

            film.setYear(Integer.parseInt(String.valueOf(filmDetails[1])));

    }

    private void SetFilmRating(String[] filmDetails, Film film) {
            film.setRating(Double.parseDouble(String.valueOf(filmDetails[2])));
    }

    private void SetFilmName(String[] filmDetails, Film film) {
            film.setName(String.valueOf(filmDetails[0]));
    }

}