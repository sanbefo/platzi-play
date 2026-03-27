package content;

public class Documentary extends Content implements Promotable {

    private String narrator;

    public Documentary(String title, String description, Genre genre, int duration, java.time.LocalDate year,
        double rating, boolean available, String narrator) {
        super(title, description, genre, duration, year, rating, available);
        this.narrator = narrator;
    }

    @Override
    public void play() {
        System.out.println("Playing documentary: " + getTitle() + " narrated by " + narrator);
    }

    @Override
    public String getSpecs() {
        return getTitle() + "\n"
            + "Genre: " + getGenre() + "\n"
            + "Rating: " + getRating() + "/5\n"
            + "Release date: " + getYear() + "\n"
            + "Available: " + isAvailable() + "\n"
            + "Narrator: " + getNarrator() + "\n";
    }

    @Override
    public String promote() {
        return "Don't miss " + getTitle() + ", a fascinating documentary narrated by " + narrator + "!";
    }

    public String getNarrator() {
        return narrator;
    }
}
