package Main.ContentItem;

import java.sql.Date;
import java.util.ArrayList;

import Main.Database.Database;

public class Webcast extends ContentItem {
    private String title; 
    private String url; 
    private int duration; 
    private String staffName; 
    private String description; 
    private ArrayList<Speaker> speakers;
    private Database db = new Database(); 

    public Webcast(Date publicationDate, String status, String title, String url, int duration, String staffName, String description) {
        super(publicationDate, status);

        this.title = title;
        this.url = url;
        this.duration = duration;
        this.staffName = staffName;
        this.description = description;

        this.speakers = new ArrayList<>();

        // db.createWebcast();
    }

    public void addSpeaker(Speaker speaker) {
        this.speakers.add(speaker);
    }
}
