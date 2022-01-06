package Main.User;

import java.sql.Date;

import Main.Database.Database;

public class User {
    private String email;
    private String name;
    private Date birthDate;
    private String gender;
    private String address;
    private String residence;
    private String country;
    private String isCourseTaker;
    private String isStaff;
    private Database db = new Database();

    public User(String email, String name, Date birthDate, String gender, String address, String residence, String country, String isCourseTaker, String isStaff) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.residence = residence;
        this.country = country;
        this.isCourseTaker = isCourseTaker;
        this.isStaff = isStaff;

        db.createUser(this);
    }

    public void update(String email, String name, Date birthDate, String gender, String address, String residence, String country, String isCourseTaker, String isStaff) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.residence = residence;
        this.country = country;
        this.isCourseTaker = isCourseTaker;
        this.isStaff = isStaff;

        db.updateUser(this, this.email, this.name, this.birthDate, this.gender, this.address, this.residence, this.country, this.isCourseTaker, this.isStaff); 
    }

    public void delete() {
        db.deleteUser(this);
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getResidence() {
        return residence;
    }

    public String getCountry() {
        return country;
    }

    public String getIsCourseTaker() {
        return isCourseTaker;
    }

    public String getIsStaff() {
        return isStaff;
    }
}