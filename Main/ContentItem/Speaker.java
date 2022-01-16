package Main.ContentItem;

public class Speaker {
    private String organisation;
    private String name;

    public Speaker(String name, String organisation) {
        this.name = name;
        this.organisation = organisation;
    }

    public String getOrganisation() {
        return this.organisation;
    }

    public String getName() {
        return this.name; 
    }
}