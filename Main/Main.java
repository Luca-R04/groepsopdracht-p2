package Main;
import java.sql.Date;

import Main.User.User;
import Main.Database.Database;


public class Main {
    public static void main(String[] args) {
    
        Database db = new Database();
        System.out.println(db.SelectUser());
    }
}