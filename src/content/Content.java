package content;

import java.time.LocalDate;

public abstract class Content {

    private String title;
    private String description;
    private Genre genre;
    private int duration;
    private LocalDate year;
    private double rating;
    private boolean available;

    public Content() {
    }

    public Content(String title, String description, Genre genre, int duration, LocalDate year,
        double rating, boolean available) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.year = year;
        this.rating = rating;
        this.available = available;
    }

    public abstract void play();

    public abstract String getSpecs();

    public void rate(double rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }

    public boolean isPopular() {
        return rating >=4;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
