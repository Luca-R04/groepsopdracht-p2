package Main.GUI;

import java.util.ArrayList;
import Main.Database.Database;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class TopCoursesGUI {
    Database db = new Database();
    GUI gui = new GUI(); 
    public Scene scene;

    public TopCoursesGUI() {
	    Menu navbar = new Menu("NavBar");
		MenuItem home = new MenuItem("Home");
		home.setOnAction(e -> {
			gui.startScene();
		});

		navbar.getItems().add(home);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(navbar);

		VBox menu = new VBox(menuBar);

        GridPane gridPane = new GridPane();

        // Labels;
        Label l1 = new Label("1: ");
        Label l2 = new Label("2: ");
        Label l3 = new Label("3: ");

        // Text fields
        Label num1 = new Label();
        Label num2 = new Label();
        Label num3 = new Label();

        ArrayList<String> top3 = db.getTopCourses();

        int i = 0;
        for (String string : top3) {

            if (i == 0) {
                String[] parts = string.split(" ");

                num1.setText(db.getCourseName(Integer.valueOf(parts[0])) + ", Certificates: " + parts[1]);
            } else if (i == 1) {
                String[] parts = string.split(" ");

                num2.setText(db.getCourseName(Integer.valueOf(parts[0])) + ", Certificates: " + parts[1]);
            } else if (i == 2) {
                String[] parts = string.split(" ");

                num3.setText(db.getCourseName(Integer.valueOf(parts[0])) + ", Certificates: " + parts[1]);
            }
            i++;
        }

        // Coordinates for the elements
        gridPane.add(menu, 0, 0);

        gridPane.add(l1, 0, 1);
        gridPane.add(num1, 1, 1);

        gridPane.add(l2, 0, 2);
        gridPane.add(num2, 1, 2);

        gridPane.add(l3, 0, 3);
        gridPane.add(num3, 1, 4);

        // Styling
        gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        this.scene = new Scene(gridPane, 700, 700);

    }

    public Scene getScene() {
        return this.scene;
    }
}