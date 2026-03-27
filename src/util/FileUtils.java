package util;

import content.Content;
import content.Documentary;
import content.Genre;
import content.Movie;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;
import platform.Platform;

public class FileUtils {

    public static void loadMovies(Platform platform) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("movies.txt"));
        lines.forEach(line -> {
            String[] parts = line.split("\\|");
            String contentType = parts[0];
            if (("MOVIE".equalsIgnoreCase(contentType) && parts.length != 6)
                || ("DOCUMENTARY".equalsIgnoreCase(contentType) && parts.length != 7)) {
                System.out.println("Invalid line format: " + line);
                return;
            }
            int duration = Integer.parseInt(parts[2]);
            Genre genre = Genre.getByName(parts[3]);
            double rating = parts[4].isBlank() ? 0.0 : Double.parseDouble(parts[4]);
            LocalDate year = LocalDate.parse(parts[5]);
            if ("DOCUMENTARY".equalsIgnoreCase(contentType)) {
                Documentary documentary = new Documentary(parts[1], "A great adventure", genre, duration, year, rating, true, parts[6]);
                try {
                    platform.addContent(documentary);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            } else if ("MOVIE".equalsIgnoreCase(contentType)) {
                Movie movie = new Movie(parts[1], "A great adventure", genre, duration, year, rating, true);
                try {
                    platform.addContent(movie);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void saveContentToFile(Content content) throws IOException {
        String line = String.join("|",
            content.getTitle(),
            String.valueOf(content.getDuration()),
            content.getGenre().name(),
            String.valueOf(content.getRating()),
            content.getYear().toString());
        String finalLine;
        if (content instanceof Documentary documentary) {
            finalLine = "DOCUMENTARY|" + line + "|" + documentary.getNarrator();
        } else {
            finalLine = "MOVIE|" + line;
        }
        Files.writeString(Path.of("movies.txt"),
            finalLine + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
