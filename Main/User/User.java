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
        db.updateUser(this, email, name, birthDate, gender, address, residence, country, isCourseTaker, isStaff); 

        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.residence = residence;
        this.country = country;
        this.isCourseTaker = isCourseTaker;
        this.isStaff = isStaff;
    }

    public void delete() {
        db.deleteUser(this);
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public String getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public String getResidence() {
        return this.residence;
    }

    public String getCountry() {
        return this.country;
    }

    public String getIsCourseTaker() {
        return this.isCourseTaker;
    }

    public String getIsStaff() {
        return this.isStaff;
    }
}