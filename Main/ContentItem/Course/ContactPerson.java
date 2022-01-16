package Main.ContentItem.Course;

public class ContactPerson {
    private String email; 
    private String name; 

    public ContactPerson(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }
}
