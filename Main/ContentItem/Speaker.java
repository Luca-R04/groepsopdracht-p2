package Main.ContentItem;

import java.util.ArrayList;

public class Speaker {
    private String organisation;
    private String name;
    private ArrayList<Webcast> webcasts; 

    public Speaker(String organisation, String name) {
        this.organisation = organisation;
        this.name = name;

        this.webcasts = new ArrayList<>();
    }

    public void addWebcast(Webcast webcast) {
        this.webcasts.add(webcast);
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getName() {
        return name; 
    }
}
