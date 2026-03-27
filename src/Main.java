import static util.FileUtils.loadMovies;
import static util.ScannerUtils.catchGenre;

import content.Documentary;
import content.Genre;
import content.Content;
import content.Movie;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import platform.Platform;
import util.ScannerUtils;

public class Main {

    public static final String VERSION = "1.0.0";
    public static final int MOVIE = 1;
    public static final int DOCUMENTARY = 2;
    public static final int ADD = 1;
    public static final int SHOW = 2;
    public static final int SEARCH_BY_TITLE = 3;
    public static final int SEARCH_BY_GENRE = 4;
    public static final int SHOW_POPULAR_CONTENT = 5;
    public static final int PLAY = 6;
    public static final int REMOVE = 7;
    public static final int EXIT = 9;

    public static void main(String[] args) throws IOException {
        System.out.println("Platzi Play !" + VERSION);
        Platform platform = new Platform("Platzi Play");
        loadMovies(platform);
        System.out.println("Total duration of contents: " + platform.getTotalDuration() + " minutes");
        platform.getPromotableContent().forEach(content -> System.out.println(content.promote()));
        while (true) {
            int option = ScannerUtils.catchInt("""
                What do you want to do? 
                1. Add content
                2. Show everything
                3. Search by title
                4. Search by genre
                5. Show popular contents
                6. Play content
                7. Remove title
                9. Exit
            """);
            System.out.println("You selected option " + option);
            switch (option) {
                case ADD -> {
                    int contentType = ScannerUtils.catchInt("""
                        What type of content do you want to add?
                        1. Movie
                        2. Documentary
                    """);
                    String title = ScannerUtils.catchText("What is the title of the content you want to watch?");
                    Genre genre = catchGenre("What is the genre of the content you want to watch?");
                    int duration = ScannerUtils.catchInt("What is the duration of the content you want to watch?");
                    double rating = ScannerUtils.catchDecimal("What is the rating of the content you want to watch?");
                    try {
                        if (contentType == MOVIE) {
                            platform.saveMovie(new Movie(title, "A great adventure", genre, duration, LocalDate.ofYearDay(2001, 1), rating, true));
                        } else if (contentType == DOCUMENTARY) {
                            String narrator = ScannerUtils.catchText("What is the narrator of the documentary you want to watch?");
                            platform.saveMovie(new Documentary(title, "A great documentary", genre, duration, LocalDate.ofYearDay(2001, 1), rating, true, narrator));
                        } else {
                            throw new IllegalArgumentException("Invalid content type: " + contentType);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                }
                case SHOW -> {
                    System.out.println("-------------------------------------------");
                    platform.getContentSummaries().forEach(summary -> System.out.println(summary));
                    System.out.println("-------------------------------------------");
                }
                case SEARCH_BY_TITLE -> {
                    String title = ScannerUtils.catchText("What is the title of the content you want to search?");
                    Content content = platform.searchByTitle(title);
                    System.out.println(content != null ? content.getSpecs() : "Movie not found");
                }
                case SEARCH_BY_GENRE -> {
                    String genre = ScannerUtils.catchText("What is the genre of the content you want to search?");
                    System.out.println("the genre is " + genre);
                    List<Content> contents = platform.searchByGenre(genre);
                    if (contents.isEmpty()) {
                        System.out.println("No contents found with genre: " + genre);
                        break;
                    }
                    System.out.println("Movies found: " + contents.size());
                    contents.forEach(content ->
                        System.out.println(content.getSpecs() + "\n-----------------------------"));
                }
                case SHOW_POPULAR_CONTENT -> {
                    int contentType = ScannerUtils.catchInt("""
                        What type of content do you want to show?
                        1. Movie
                        2. Documentary
                    """);
                    if (contentType == MOVIE) {
                        List<Movie> movies = platform.getPopularMovies();
                        if (movies.isEmpty()) {
                            System.out.println("No popular movies found");
                            break;
                        } else {
                            movies.forEach(content ->
                                System.out.println(content.getSpecs() + "\n-----------------------------"));
                        }
                        System.out.println("Popular contents found: " + movies.size());
                    } else if (contentType == DOCUMENTARY) {
                        List<Documentary> documentaries = platform.getPopularDocumentaries();
                        if (documentaries.isEmpty()) {
                            System.out.println("No popular documentaries found");
                            break;
                        } else {
                            documentaries.forEach(content ->
                                System.out.println(content.getSpecs() + "\n-----------------------------"));
                        }
                        System.out.println("Popular contents found: " + documentaries.size());
                    } else {
                        throw new IllegalArgumentException("Invalid content type: " + contentType);
                    }
                }
                case PLAY -> {
                    String title = ScannerUtils.catchText("What is the title of the content you want to watch?");
                    Content content = platform.searchByTitle(title);
                    platform.play(content);
                }
                case REMOVE -> {
                    String title = ScannerUtils.catchText("What is the title of the content you want to remove?");
                    platform.removeMovie(title);
                }
                case EXIT -> System.exit(0);
            }
        }


//        platform.showTitles();
//        User user = new User();
//        user.setName("John");
//        user.setCreatedAt(LocalDateTime.of(2025, 12, 24, 17, 15, 14));
//        System.out.println(user.getSpecs());
    }
}
