package content;

public enum Genre {

    ACTION("ACTION"),
    COMEDY("COMEDY"),
    DRAMA("DRAMA"),
    TERROR("TERROR"),
    HORROR("HORROR"),
    SCI_FI("SCI_FI"),
    ROMANCE("ROMANCE"),
    THRILLER("THRILLER"),
    FANTASY("FANTASY"),
    ANIMATION("ANIMATION"),
    DOCUMENTARY("DOCUMENTARY");

    private final String name;

    Genre(String name) {
        this.name = name;
    }

    public static Genre getByName(String name) {
        for (Genre genre : Genre.values()) {
            if (genre.name.equalsIgnoreCase(name)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("No genre found with name: " + name);
    }
}
