package Main.ContentItem;

import Main.Database.Database;

public class Speaker {
    private String organisation;
    private String name;
    private Database db = new Database();

    public Speaker(String name, String organisation) {
        this.name = name;
        this.organisation = organisation;
    }

    public void insert() {
        db.createSpeaker(this);
    }

    public String getOrganisation() {
        return this.organisation;
    }

    public String getName() {
        return this.name; 
    }
}