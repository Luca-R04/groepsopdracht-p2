package Main.User;

import java.sql.Date;
import Main.Database.Database;

public class User {
    private String email;
    private String name;
    private Date DateOfBirth;
    private String gender;
    private String address;
    private String residence;
    private String country;
    private int CourseTakerID ;
    private int StaffID;
    private Database db = new Database();

    public User(String email, String name, Date DateOfBirth, String gender, String address, String residence, String country, int CourseTakerID, int StaffID) {
        this.email = email;
        this.name = name;
        this.DateOfBirth = DateOfBirth;
        this.gender = gender;
        this.address = address;
        this.residence = residence;
        this.country = country;
        this.CourseTakerID = CourseTakerID;
        this.StaffID = StaffID;
        
        // db.createUser(this.email, this.name, this.birthDate, this.gender, this.address, this.residence, this.country, this.isCourseTaker, this.isStaff);
    }
}