package Main.ContentItem;

import java.sql.Date;

import Main.ContentItem.Course.Status;
import Main.Database.Database;

public class Webcast extends ContentItem {
    private String title; 
    private String URL; 
    private int duration; 
    private String description; 
    private Speaker speaker;
    private Date publicationDate;
    private Status status;
    private Database db = new Database(); 

    public Webcast(Date publicationDate, Status status, String title, String URL, int duration, String description, Speaker speaker) {
        super(publicationDate, status);

        this.title = title;
        this.URL = URL;
        this.duration = duration;
        this.description = description;
        this.speaker = speaker;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public void insert() {
        // db.createWebcast(publicationDate, status, this);
    }

    public void update(Date publicationDate, Status status, String title, String URL, int duration, String description, Speaker speaker) {
        // db.updateWebcast(this, publicationDate, status, title, URL, duration, description, speaker);

        this.title = title;
        this.URL = URL;
        this.duration = duration;
        this.description = description;
        this.speaker = speaker;
    }

    public void delete() {
        // db.deleteWebcast(this);
    }

    public String getTitle() {
        return this.title; 
    }

    public String getURL() {
        return this.URL;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDuration() {
        return this.duration;
    }

    public Speaker getSpeaker() {
        return this.speaker;
    }
}
