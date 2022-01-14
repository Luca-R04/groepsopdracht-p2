package Main.ContentItem.Course;
import java.sql.Date;
import java.util.ArrayList;

import Main.ContentItem.ContentItem;
import Main.Database.Database;

public class Course extends ContentItem {
    private String name; 
    private String topic; 
    private String text; 
    private Level level; 
    private int percentageViewed; 
    private Date publicationDate;
    private Status status; 
    private ArrayList<Module> modules; 
    private Database db = new Database();

    public Course(Date publicationDate, Status status, String name, String topic, String text, Level level) {        
        super(publicationDate, status); 

        this.name = name; 
        this.topic = topic; 
        this.text = text; 
        this.level = level; 
        this.publicationDate = publicationDate;
        this.status = status; 
        this.modules = new ArrayList<>();
    }

    public void insert() {
        db.createCourse(publicationDate, status, this); 
    }

    public void addModule(Module module) {
        this.modules.add(module);

        db.addModuleToCourse(this, module);
    }

    public void update(Date publicationDate, Status status, String name, String topic, String text, Level level, int percentageViewed) {
        db.updateCourse(this, publicationDate, status, name, topic, text, level, percentageViewed); 

        this.name = name;
        this.topic = topic;
        this.text = text;
        this.level = level;
        this.percentageViewed = percentageViewed;
    }

    public void delete() {
        db.deleteCourse(this.name);
    }

    public String getName() {
        return this.name;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getText() {
        return this.text;
    }

    public Level getLevel() {
        return this.level;
    }

    public int getPercentageViewed() {
        return this.percentageViewed;
    }
}
