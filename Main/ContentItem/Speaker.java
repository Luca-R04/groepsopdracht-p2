package Main.ContentItem;

import java.util.ArrayList;

public class Speaker {
    private String organization;
    private String name;
    private ArrayList<Webcast> webcasts; 

    public Speaker(String organization, String name) {
        this.organization = organization;
        this.name = name;

        this.webcasts = new ArrayList<>();
    }

    public void addWebcast(Webcast webcast) {
        this.webcasts.add(webcast);
    }
}
