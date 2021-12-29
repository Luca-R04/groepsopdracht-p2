package Main.ContentItem.Course;

import Main.Database.Database;

public class Module {
    private String title; 
    private String version; 
    private int serialNumber; 
    private String description; 
    private Database db = new Database();

    public Module(String title, String version, int serialNumber, String description) {
        this.title = title;
        this.version = version;
        this.serialNumber = serialNumber;
        this.description = description;

        db.createModule(this);
    }

    public void update(String title, String version, int serialNumber, String description) {
        db.updateModule(this, title, version, serialNumber, description);

        this.title = title;
        this.version = version;
        this.serialNumber = serialNumber;
        this.description = description;
    }

    public String getTitle() {
        return this.title; 
    }

    public String getVersion() {
        return this.version;
    }

    public int getSerialNumber() {
        return this.serialNumber;
    }

    public String getDescription() {
        return this.description;
    }
}
