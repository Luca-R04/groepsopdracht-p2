package Main.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {
	Stage stage;
	Scene scene;

	@Override
	public void start(Stage stage) {
		// Changes the title of the window.
		this.stage = stage;
		this.stage.setTitle("Codecademy: Luca Rinck - 218...., Martijn Schemers - 218...., Max Stokla - 2186459, Laurens Weterings - 218....");

		// Changes the scene using the startScene method
		startScene();

		// Displays the stage element
		this.stage.show();
	}
    
	// Method for the user to fill in data
	public void sceneUserForm() {
		
		GridPane test = new GridPane();
		test.getChildren().add(new Label("This is a test"));
		this.scene = new Scene(test);
		this.stage.setScene(this.scene);

	}

	// Method sets up the start screen with buttons to navigate
	public void startScene() {
		// Creates all the buttons
		Button bCourse = new Button("Courses");
        Button bEnroledCourse = new Button("Your enroled courses");
        Button bTop3course = new Button("Top 3 courses");
        Button bWebcast = new Button("Webcasts");
        Button bEnroledWebcast = new Button("Your enroled Webcasts");
        Button bTop3webcast = new Button("Top 3 Webcasts");
        Button bCertificates = new Button("Your certificates");

		// New gridpane
		GridPane gridPane = new GridPane();
        
		// Specifies coordinates for the buttons
        gridPane.add(bCourse, 0, 1, 1, 1);
        gridPane.add(bEnroledCourse, 1, 1, 1, 1);
        gridPane.add(bTop3course, 0, 3, 1, 1);
        gridPane.add(bWebcast, 0, 2, 1, 1);
        gridPane.add(bEnroledWebcast, 1, 2, 1, 1);
        gridPane.add(bTop3webcast, 1, 3, 1, 1);
        gridPane.add(bCertificates, 0, 4, 1, 1);

		this.scene = new Scene(gridPane);
		this.stage.setScene(this.scene);
	}


}
