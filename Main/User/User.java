package Main.User;

import java.sql.Date;

import Main.Database.Database;

public class User {
    private String email;
    private String FirstName;
    private String LastName;
    private Date birthDate;
    private Gender gender;
    private String address;
    private String residence;
    private String postalCode;
    private String country;
    private String isCourseTaker;
    private String isStaff;
    private Database db = new Database();

    public User(String email, String FirstName, String LastName,  Date birthDate, Gender gender, String address, String postalCode, String residence, String country, String isCourseTaker, String isStaff) {
        this.email = email;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.residence = residence;
        this.postalCode = postalCode;
        this.country = country;
        this.isCourseTaker = isCourseTaker;
        this.isStaff = isStaff;
    }

    public void insert() {
        db.createUser(this);
    }

    public void update(String email, String FirstName, String LastName,  Date birthDate, Gender gender, String address, String postalCode, String residence, String country) {
        db.updateUser(this, email, FirstName, LastName, birthDate, gender, address, postalCode ,residence, country); 

        this.FirstName = FirstName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.residence = residence;
        this.country = country;
    }

    public void delete() {
        db.deleteUser(this.email);
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public Gender getGender() {
        return this.gender;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPostalCode(){
        return this.postalCode;
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