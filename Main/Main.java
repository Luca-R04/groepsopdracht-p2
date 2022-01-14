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
import Main.ContentItem.Course.Status;
import Main.ContentItem.Course.Course;
import Main.ContentItem.Course.Level;
import Main.Database.Database;
import Main.User.User;
import Main.GUI.GUI;

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

        Course s = new Course(date, Status.ACTIVE, "Course 46", "https://www.google.com", "Test course", Level.BEGINNER);
        ContactPerson john = new ContactPerson("testjohn@gmail.com", "John");

        Module m1 = new Module("Programming 4", "1", 1234555, "Module about programming", john, s);
        // Module m2 = new Module("Programming 2", "1", 1234555, "Module about programming", john, s);
        // Module m3 = new Module("Programming 3", "1", 1234555, "Module about programming", john, s);
        // Module m4 = new Module("Programming 4", "1", 1234555, "Module about programming", john, s);

        // System.out.println(s.getLevel());
        // s.addModule(m);
        
        // Application.launch(GUI.class);


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