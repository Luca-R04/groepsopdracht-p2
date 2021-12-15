package Main.User;

import java.sql.Date;

import Main.Database.Database;

public class CourseTaker extends User { 
    private Database db = new Database();

    public CourseTaker(String email, String name, Date birthDate, String gender, String address, String residence, String country, String isStaff, String isCourseTaker) {
        super(email, name, birthDate, gender, address, residence, country, isStaff, isCourseTaker);

    }
}