package Main.ContentItem.Course;
import java.sql.Date;

import Main.ContentItem.ContentItem;
import Main.Database.Database;

public class Course extends ContentItem {
    private String name; 
    private String topic; 
    private String text; 
    private String level; 
    private int percentageViewed; 
    private Database db = new Database();

    public Course(Date publicationDate, String status, String name, String topic, String text, String level) {        
        super(publicationDate, status); 

        this.name = name; 
        this.topic = topic; 
        this.text = text; 
        this.level = level; 

        db.createCourse(publicationDate, status, this.name, this.topic, this.text, this.level, this.percentageViewed); 
    }

    public void updatePercentageViewed(int value) {
        this.percentageViewed = value;
    }
}
