package platform;

import static util.FileUtils.saveContentToFile;

import content.Content;
import content.ContentSummary;
import content.Documentary;
import content.Genre;
import content.Movie;
import content.Promotable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Exception.NonexistentMovieException;
import java.util.Map;

public class Platform {

    private String name;
    private List<Content> contentList;
    private Map<Content, Integer> watchCounts;

    public Platform(String name) {
        this.name = name;
        this.contentList = new ArrayList();
        this.watchCounts = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public List<Promotable> getPromotableContent() {
        return contentList.stream()
            .filter(content -> content instanceof Promotable)
            .map(content -> (Promotable) content).toList();
    }

    public void play(Content content) {
        if (contentList.contains(content)) {
            watchCounts.put(content, watchCounts.getOrDefault(content, 0) + 1);
            content.play();
        } else {
            System.out.println("Movie not found: " + content.getTitle());
        }
    }

    public void addContent(Content content) throws IOException {
        contentList.add(content);
    }

    public void saveMovie(Content content) throws IOException {
        contentList.add(content);
        saveContentToFile(content);
    }

    public void removeMovie(String title) {
        Content content = searchByTitle(title);
        try {
            System.out.println("Removing " + content.getTitle());
            contentList.remove(content);
        } catch (NonexistentMovieException e) {
            System.out.println(e.getMessage());
            throw new NonexistentMovieException(e.getMessage());
        }
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public List<String> showTitles() {
        return contentList.stream().map(Content::getTitle).toList();
    }

    public Content searchByTitle(String title) {
        return contentList.stream().filter(movie ->
            movie.getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
    }

    public List<Content> searchByGenre(String name) {
        Genre genre = Genre.getByName(name);
        System.out.println("***********************");
        System.out.println(genre);
        System.out.println("***********************");
        return contentList.stream().filter(movie ->
            movie.getGenre().equals(genre)).toList();
    }

    public int getTotalDuration() {
        return contentList.stream().mapToInt(Content::getDuration).sum();
    }

    public List<Content> getPopular() {
        return contentList.stream().filter(Content::isPopular).toList();
    }

    public List<Movie> getPopularMovies() {
        return contentList.stream()
            .filter(content -> content instanceof Movie)
            .map(content -> (Movie) content)
            .filter(Movie::isPopular).toList();
    }

    public List<Documentary> getPopularDocumentaries() {
        return contentList.stream()
            .filter(content -> content instanceof Documentary)
            .map(content -> (Documentary) content)
            .filter(Documentary::isPopular).toList();
    }

    public List<Content> sortByRating() {
        return contentList.stream().sorted((m1, m2)
            -> Double.compare(m2.getRating(), m1.getRating())).toList();
    }

    public List<ContentSummary> getContentSummaries() {
        return contentList.stream().map(ContentSummary::new).toList();
    }
}
