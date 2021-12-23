package Main;

import java.sql.Date;
// import java.util.ArrayList;
// import java.util.HashMap;

import javafx.application.Application;
import Main.ContentItem.Speaker;
import Main.ContentItem.Webcast;
import Main.ContentItem.Course.Course;
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
        // Speaker s = new Speaker("Github", "Chris"); 

        // Webcast w = new Webcast(date, "not done", "Webcast 1", "https://www.google.com", 100, "Toon", "Test webcast", s);
        Course s = new Course(date, "not done", "Webcast 1", "https://www.google.com", "Test webcast", "Toon", 100);
    }
}