package platform;

import content.Content;
import java.time.LocalDateTime;

public class User {

    private String name;
    private String email;
    private LocalDateTime createdAt;

    public User() {
    }

    public User(String name, String email, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }

    public void watch(Content content) {
        System.out.println(name + " is watching...");
        content.play();
    }

    public String getSpecs() {
        return "Name: " + name + "\n"
            + "Created At: " + createdAt + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
