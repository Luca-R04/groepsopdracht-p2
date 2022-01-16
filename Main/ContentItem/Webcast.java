package Main.ContentItem;

// This class represents a Webcast. It is possible to create one and retrieve it's attributes. 
// There is also a possibilty to insert a Webcast object into the database. Updating and deleting it is also possible. 

import java.sql.Date;
import Main.ContentItem.Course.Status;

public class Webcast {
    private String title; 
    private String URL; 
    private int duration; 
    private String description; 
    private Speaker speaker;
    private Date publicationDate;
    private Status status;

    public Webcast(Date publicationDate, Status status, String title, String URL, int duration, String description, Speaker speaker) {
        this.title = title;
        this.URL = URL;
        this.duration = duration;
        this.description = description;
        this.speaker = speaker;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public void update(Date publicationDate, Status status, String title, String URL, int duration, String description, Speaker speaker) {
        this.title = title;
        this.URL = URL;
        this.duration = duration;
        this.description = description;
        this.speaker = speaker;
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

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public Status getStatus() {
        return this.status;
    }
}
