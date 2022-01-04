package Main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javafx.application.Application;
import Main.ContentItem.Speaker;
import Main.ContentItem.Webcast;
import Main.ContentItem.Course.ContactPerson;
import Main.ContentItem.Course.Module;
import Main.ContentItem.Course.Course;
import Main.Database.Database;
import Main.User.User;
import Main.GUI.GUI;
import Main.GUI.Test;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();

        Date date = new Date(12-12-2021);
        // User user = new User("johndoe@gmail.com", "John", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands", "1", null);
        // User user2 = new User("johndoe2@gmail.com", "John", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands", null, "1");
        // User user3 = new User("johndoe3@gmail.com", "John", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands", null, "1");

        // db.updateUser("Email@gmail.com", "Kees", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands", null, "1");
        // db.deleteUser("johndoe@gmail.com");
        // db.deleteUser("johndoe2@gmail.com");

        // HashMap<String, ArrayList<String>> users = db.getAllUsers();
        // System.out.println(users.get("johndoe@gmail.com"));
        
        // Application.launch(GUI.class);
        // Speaker s = new Speaker("Google", "Christian"); 

        // Webcast w = new Webcast(date, "not done", "Webcast 3", "https://www.google.com", 100, "Toon", "Test webcast", s);
        // w.update(date, "not done", "Webcast 1", "https://www.google.com", 150, "Toon", "Test webcast", s);

        // Module m = new Module("Programming 3", "2", 1234555, "Module about programming");
        // Course s = new Course(date, "not done", "Course 3", "https://www.google.com", "Test course", "Kees");
        // s.addModule(m);
        
        Application.launch(GUI.class);

        // ContactPerson john = new ContactPerson("uyghjgwdasjhkgupda@gmail.com", "John");

        // while(true) {
        //     Scanner s = new Scanner(System.in);
        //     String input = s.nextLine();

        //     if(input.equals("update")) {
        //         System.out.println("Email: ");
        //         String email = s.nextLine();
        //         System.out.println("Name: ");
        //         String name = s.nextLine();

        //         john.update(email, name);
        //     }

        //     if(input.equals("delete")) {
        //         john.delete();
        //     }
        // }

        // john.addModule(m);

        // System.out.println(db.getAllUsers());            
    }
}