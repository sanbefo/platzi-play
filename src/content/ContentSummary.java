package content;

public record ContentSummary(String title, int duration, Genre genre) {

     public ContentSummary(Content content) {
         this(content.getTitle(), content.getDuration(), content.getGenre());
     }
}
