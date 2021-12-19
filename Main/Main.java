package Main;

// import java.sql.Date;
// import java.util.ArrayList;
// import java.util.HashMap;

// import Main.User.User;
import javafx.application.Application;
// import Main.Database.Database;
import Main.GUI.GUI;

public class Main {
    public static void main(String[] args) {
        
        // Database db = new Database();
        // Date date = new Date(12 - 12 - 2021);
        // User user = new User("johndoe@gmail.com", "John", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands",
        //         "1", null);
        // User user2 = new User("johndoe2@gmail.com", "John", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands",
        //         null, "1");
        // User user3 = new User("johndoe3@gmail.com", "John", date, "male", "Lovensdijkstraat 10", "Breda", "Netherlands",
        //         null, "1");

        // db.updateUser("johndoe3@gmail.com", "John", date, "female", "Lovensdijkstraat 10", "Breda", "Netherlands", null, "1");

        

        // HashMap<String, ArrayList<String>> users = db.getAllUsers();
        // System.out.println(users.get("johndoe@gmail.com"));

        // db.deleteUser("johndoe2@gmail.com");

        
        Application.launch(GUI.class);
    }
}