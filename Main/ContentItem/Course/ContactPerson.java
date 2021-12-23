package Main.ContentItem.Course;

import java.util.ArrayList;

public class ContactPerson {
    private int ID; 
    private String email; 
    private String name; 
    private ArrayList<Module> modules;

    public ContactPerson(int ID, String email, String name) {
        this.ID = ID;
        this.email = email;
        this.name = name;

        this.modules = new ArrayList<>();
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }
}
