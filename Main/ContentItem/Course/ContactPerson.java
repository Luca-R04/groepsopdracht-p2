package Main.ContentItem.Course;

import java.util.ArrayList;

import Main.Database.Database;

public class ContactPerson {
    private int ID; 
    private String email; 
    private String name; 
    private ArrayList<Module> modules;
    private Database db = new Database();

    public ContactPerson(int ID, String email, String name) {
        this.ID = ID;
        this.email = email;
        this.name = name;

        this.modules = new ArrayList<>();

        // db.createContactPerson();
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public void update(int ID, String email, String name) {
        this.ID = ID;
        this.email = email;
        this.name = name;

        // db.updateContactPerson(this, ID, email, name);
    }
}
