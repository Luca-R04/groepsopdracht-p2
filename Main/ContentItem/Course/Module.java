package Main.ContentItem.Course;

public class Module {
    private int id; 
    private String title; 
    private int version; 
    private int serialNumber; 
    private String description; 
    private ContactPerson contactPerson;
    private Course course;

    public Module(String title, int version, int serialNumber, String description, ContactPerson contactPerson, Course course) {
        this.title = title;
        this.version = version;
        this.serialNumber = serialNumber;
        this.description = description;
        this.contactPerson = contactPerson;
        this.course = course;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return this.title;
    }
}
