package Exception;

public class NonexistentMovieException extends RuntimeException {

    public NonexistentMovieException(String title) {
        super("Movie with title '" + title + "' does not exist.");
    }
}
