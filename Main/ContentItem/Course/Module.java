package Main.ContentItem.Course;

import Main.Database.Database;

public class Module {
    private String title; 
    private int version; 
    private int serialNumber; 
    private String description; 
    private ContactPerson contactPerson;
    private Course course;
    private Database db = new Database();

    public Module(String title, int version, int serialNumber, String description, ContactPerson contactPerson, Course course) {
        this.title = title;
        this.version = version;
        this.serialNumber = serialNumber;
        this.description = description;
        this.contactPerson = contactPerson;
        this.course = course;
    }

    public void insert() {
        db.createModule(this);
    }

    public void update(String title, int version, int serialNumber, String description) {
        db.updateModule(this, title, version, serialNumber, description);

        this.title = title;
        this.version = version;
        this.serialNumber = serialNumber;
        this.description = description;
    }

    public String getTitle() {
        return this.title; 
    }

    public int getVersion() {
        return this.version;
    }

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public ContactPerson getContactPerson() {
        return this.contactPerson;
    }

    public Course getCourse() {
        return this.course;
    }

    public String toString() {
        return this.title;
    }
}
