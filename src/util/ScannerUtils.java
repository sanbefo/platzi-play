package util;

import content.Genre;
import java.util.Scanner;

public class ScannerUtils {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static String catchText(String text) {
        System.out.println(text + ": ");
        return SCANNER.nextLine();
    }

    public static int catchInt(String text) {
        System.out.println(text + ": ");
        while (!SCANNER.hasNextInt()) {
            System.out.println("Please enter a valid number: ");
            SCANNER.nextLine();
        }
        int number = SCANNER.nextInt();
        SCANNER.nextLine();
        return number;
    }

    public static double catchDecimal(String text) {
        System.out.println(text + ": ");
        double number = SCANNER.nextDouble();
        SCANNER.nextLine();
        return number;
    }

    public static Genre catchGenre(String text) {
        while (true) {
            System.out.println("Available genres: ");
            for (Genre genre : Genre.values()) {
                System.out.println(genre);
            }
            String genre = catchText(text);
            try {
                return Genre.valueOf(genre.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter a valid genre: ");
                genre = SCANNER.nextLine();
            }
        }
    }
}
