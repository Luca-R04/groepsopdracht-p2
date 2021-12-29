package Main.ContentItem.Course;

import java.util.ArrayList;

import Main.Database.Database;

public class ContactPerson {
    private String email; 
    private String name; 
    private ArrayList<Module> modules;
    private Database db = new Database();

    public ContactPerson(String email, String name) {
        this.email = email;
        this.name = name;

        this.modules = new ArrayList<>();

        db.createContactPerson(this);
    }

    public void addModule(Module module) {
        this.modules.add(module);

        db.addModuleToContactPerson(this, module);
    }

    public void update(String email, String name) {
        db.updateContactPerson(this, email, name);

        this.email = email;
        this.name = name;
    }

    public void delete() {
        db.deleteContactPerson(this);
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }
}
