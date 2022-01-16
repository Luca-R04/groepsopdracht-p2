package Main.ContentItem;

// This class represents a Speaker. It is possible to create one and retrieve it's attributes. 

public class Speaker {
    private String organisation;
    private String firstName;
    private String lastName;

    public Speaker(String firstName, String lastName, String organisation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.organisation = organisation;
    }

    public String getOrganisation() {
        return this.organisation;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}