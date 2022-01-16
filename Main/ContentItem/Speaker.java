package Main.ContentItem;

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

    public String getName() {
        return this.firstName + this.lastName;
    }
}