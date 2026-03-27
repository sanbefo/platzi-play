package content;

import java.time.LocalDate;

public class Movie extends Content {

    public Movie(String title, String description, Genre genre, int duration, LocalDate year,
        double rating, boolean available) {
        super(title, description, genre, duration, year, rating, available);
    }

    @Override
    public void play() {
        System.out.println("Playing movie: " + getTitle());
    }

    @Override
    public String getSpecs() {
        return getTitle() + "\n"
            + "Genre: " + getGenre() + "\n"
            + "Rating: " + getRating() + "/5\n"
            + "Release date: " + getYear() + "\n"
            + "Available: " + isAvailable();
    }
}
