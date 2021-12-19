package Main.User;

import java.sql.Date;
import java.time.LocalDate;

public class Staff extends User {

    public Staff(String email, String name, LocalDate birthDate, String gender, String address, String residence, String country, String isStaff, String isCourseTaker) {
        super(email, name, birthDate, gender, address, residence, country, isStaff, isCourseTaker);

    }
}