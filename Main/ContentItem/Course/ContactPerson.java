package Main.ContentItem.Course;

// This class represents a ContactPerson. It is possible to create one and retrieve it's attributes. 

public class ContactPerson {
    private String email; 
    private String firstName; 
    private String lastName;

    public ContactPerson(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
