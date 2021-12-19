package Main.User;

import java.sql.Date;
import java.time.LocalDate;

import Main.Database.Database;

public class User {
    private String email;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String residence;
    private String country;
    private String isCourseTaker;
    private String isStaff;
    private Database db = new Database();

    public User(String email, String name, LocalDate birthDate, String gender, String address, String residence, String country, String isCourseTaker, String isStaff) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.residence = residence;
        this.country = country;
        this.isCourseTaker = isCourseTaker;
        this.isStaff = isStaff;
        
        db.createUser(this.email, this.name, this.birthDate, this.gender, this.address, this.residence, this.country, this.isCourseTaker, this.isStaff);
    }
}