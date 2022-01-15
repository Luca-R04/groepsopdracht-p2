package Main.ContentItem;

import java.util.ArrayList;

// import Main.Database.Database;

public class Speaker {
    private String organisation;
    private String name;
    private ArrayList<Webcast> webcasts; 
    // private Database db = new Database();

    public Speaker(String organisation, String name) {
        this.organisation = organisation;
        this.name = name;

        this.webcasts = new ArrayList<>();
    }

    // public void insert() {
    //     db.createSpeaker(this);
    // }

    public void addWebcast(Webcast webcast) {
        this.webcasts.add(webcast);
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getName() {
        return name; 
    }

    public void update(String organisation, String name) {
        this.organisation = organisation;
        this.name = name;
    }
}
