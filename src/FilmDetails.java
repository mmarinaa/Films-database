import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilmDetails {
    /**
     * Reads the file one line at a time. Each line will is that split up and translated into a Passenger object
     */
    public List<Film> getFilmFromFile(BufferedReader reader) throws IOException {
        List<Film> films = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] filmDetails = line.trim().split(",");
            Film film = new Film();
            for (int i = 0; i < filmDetails.length; i++) {

                SetFilmName(filmDetails, film, 0);

                SetFilmYear(filmDetails, film, 1);

                SetFilmRating(filmDetails, film, 2);
            }

            films.add(film);


        }
        return films;

    }

    private void SetFilmYear(String[] filmDetails, Film film, int i) {
        if (i < filmDetails.length && i == 1) {
            film.setYear(Integer.parseInt(String.valueOf(filmDetails[1])));
        }
    }

    private void SetFilmRating(String[] filmDetails, Film film, int i) {
        if (i < filmDetails.length && i == 2) {
            film.setRating(Integer.parseInt(String.valueOf(filmDetails[2])));
        }
    }

    private void SetFilmName(String[] filmDetails, Film film, int i) {
        if (i < filmDetails.length && i == 0) {
            film.setName(String.valueOf(filmDetails[0]));
        }
    }

}