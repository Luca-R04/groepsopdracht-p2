package Main.ContentItem;

import java.sql.Date;

import Main.Database.Database;

public class Webcast extends ContentItem {
    private String title; 
    private String URL; 
    private int duration; 
    private String staffName; 
    private String description; 
    private Speaker speaker;
    private Database db = new Database(); 

    public Webcast(Date publicationDate, String status, String title, String URL, int duration, String staffName, String description, Speaker speaker) {
        super(publicationDate, status);

        this.title = title;
        this.URL = URL;
        this.duration = duration;
        this.staffName = staffName;
        this.description = description;
        this.speaker = speaker;

        db.createWebcast(publicationDate, status, this.title, this.URL, this.duration, this.staffName, this.description, this.speaker);
    }
}
