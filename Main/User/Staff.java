package Main.User;

import java.sql.Date;

public class Staff extends User{

    public Staff(String email, String name, Date birthDate, String gender, String address, String residence, String country, String isStaff, String isCourseTaker) {
        super(email, name, birthDate, gender, address, residence, country, isStaff, isCourseTaker);

    }
    
}
