package Main.ContentItem.Course;

// This class represents a Course. It is possible to create one and retrieve it's attributes. 
// There is also a possibilty to insert a Course object into the database. Updating and deleting it is also possible. 

import java.sql.Date;
import java.util.ArrayList;

import Main.Database.Database;

public class Course {
    private int id; 
    private String name; 
    private String topic; 
    private String text; 
    private Level level; 
    private Date publicationDate;
    private Status status; 
    private ArrayList<Module> modules; 
    private Database db = new Database();

    public Course(Date publicationDate, Status status, String name, String topic, String text, Level level) {        
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

    public void update(Date publicationDate, Status status, String name, String topic, String text, Level level) {
        db.updateCourse(this, publicationDate, status, name, topic, text, level); 

        this.name = name;
        this.topic = topic;
        this.text = text;
        this.level = level;
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

    public Date getPublicationDate() {
        return this.publicationDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return this.name; 
    }
}