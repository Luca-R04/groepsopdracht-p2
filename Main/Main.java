package Main;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import Main.User.User;
import Main.Database.Database;


public class Main {
    public static void main(String[] args) {
    
        Database db = new Database();
        HashMap<String, ArrayList<String>> users = db.SelectUser();

        System.out.println(users.get("johndoe@gmail.com"));
    }
}