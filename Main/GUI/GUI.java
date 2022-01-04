package Main.GUI;

import Main.Database.Database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import Main.GUI.User;

public class GUI extends Application {
	public Stage stage;
	public Scene scene;
	public Database db = new Database();
	private User user = new User();

	@Override
	public void start(Stage stage) {
		this.stage = stage;

		// Changes the title of the window.
		this.stage.setTitle("Codecademy: Luca Rinck - 2186751, Martijn Schemers - 2184875, Max Stokla - 2186459, Laurens Weterings - 2189413");

		this.stage.setScene(scene);

		// Changes the scene using the startScene method
		startScene();

		// Displays the stage element
		this.stage.show();
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
		Button bUser = new Button("User overview");

		// New gridpane
		GridPane gridPane = new GridPane();

		// Specifies coordinates for the buttons
		gridPane.add(bCourse, 0, 1);
		gridPane.add(bEnroledCourse, 1, 1);

		gridPane.add(bWebcast, 0, 2);
		gridPane.add(bEnroledWebcast, 1, 2);

		gridPane.add(bTop3course, 0, 3);
		gridPane.add(bTop3webcast, 1, 3);

		gridPane.add(bCertificates, 0, 4);
		gridPane.add(bUser, 1, 4);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		bCourse.setMaxWidth(300);
		bEnroledCourse.setMaxWidth(300);
		bTop3course.setMaxWidth(300);
		bWebcast.setMaxWidth(300);
		bEnroledWebcast.setMaxWidth(300);
		bTop3webcast.setMaxWidth(300);
		bCertificates.setMaxWidth(300);
		bUser.setMaxWidth(300);

		this.scene = new Scene(gridPane, 500, 500);
		this.stage.setScene(this.scene);
		this.stage.show();

		// Button functions
		// bUser
		bUser.setOnAction((event) -> {
			user.sceneUser();
			System.out.println("Test");
		});
	}
}