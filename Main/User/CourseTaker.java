package Main.User;

import java.sql.Date;
import java.time.LocalDate;

public class CourseTaker extends User { 

    public CourseTaker(String email, String name, LocalDate birthDate, String gender, String address, String residence, String country, String isStaff, String isCourseTaker) {
        super(email, name, birthDate, gender, address, residence, country, isStaff, isCourseTaker);

    }
}