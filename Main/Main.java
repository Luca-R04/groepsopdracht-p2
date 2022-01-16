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
import Main.User.Gender;
import Main.User.User;
import Main.GUI.GUI;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();

        Date date = new Date(2020-12-12);
        // String email, String FirstName, String LastName, Date birthDate, Gender gender, String address, String postalCode, String residence, String country, String isCourseTaker, String isStaff
        // User user = new User("johndoe@gmail.com", "John", "Doe", date, Gender.MALE, "Lovensdijkstraat 10", "4264 CK", "Breda", "Netherlands", null, "1");
        // user.insert();
        // User user2 = new User("johndoe2@gmail.com", "John", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands", null, "1");
        // User user3 = new User("johndoe3@gmail.com", "John", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands", null, "1");

        // Application.launch(GUI.class);

        // Speaker s = new Speaker("Kees", "AH");
        // s.insert();
        // Webcast w = new Webcast(date, Status.ACTIVE, "Webcast 3", "https://www.google.com", 100, "Test webcast", s);
        // w.insert();

        // Course s1 = new Course(date, Status.ARCHIVED, "Course 5", "https://www.google.com", "Test course 1", Level.BEGINNER);
        // Course s2 = new Course(date, Status.ACTIVE, "Course 2", "https://www.google.com", "Test course 2", Level.ADVANCED);
        // Course s3 = new Course(date, Status.CONCEPT, "Course 3", "https://www.google.com", "Test course 3", Level.EXPERT);

        // s1.insert();
        // s2.insert();
        // s3.insert();

        // ContactPerson john = new ContactPerson("testjohn@gmail.com", "John");

        // Module m1 = new Module("Programming 1", "1", 1234555, "Module about programming", john, s1);
        // Module m2 = new Module("Programming 2", "1", 1234555, "Module about programming", john, s1);
        // Module m3 = new Module("Programming 3", "1", 1234555, "Module about programming", john, s1);
        // Module m4 = new Module("Programming 4", "1", 1234555, "Module about programming", john, s1);

        // m1.insert();
        // m2.insert();
        // m3.insert();
        // m4.insert();

        Application.launch(GUI.class);
    }
}