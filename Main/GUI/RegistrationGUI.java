package Main.GUI;

import Main.Database.Database;
import Main.User.Registration;
import Main.User.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import Main.ContentItem.Course.Course;
import Main.ContentItem.Course.Level;
import Main.ContentItem.Course.Status;
import Main.Database.Database;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
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

/*
This class provides the GUI for the CRUD operations for the object Registration. 
It is possible to create, read, update and delete Registration objects from the database. 
*/

public class RegistrationGUI {
  private Database db = new Database();
	public Scene scene;

	public RegistrationGUI() {
		// Creating all the buttons
		Button bCreate = new Button("Create a Registration");
		Button bRead = new Button("View Registrations");
		Button bUpdate = new Button("Update Registration");

		// New Gridpane
		GridPane gridPane = new GridPane();

		// Specifies coordinates for the buttons
		gridPane.add(bCreate, 0, 1);
		gridPane.add(bRead, 1, 1);
		gridPane.add(bUpdate, 0, 2);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Button functions
		bCreate.setOnAction((action) -> {
			sceneRegistrationCreate();
			GUI.updateScene(this.scene);
		});

		bRead.setOnAction((action) -> {
			// sceneRegistrationRead();
			GUI.updateScene(this.scene);
		});

		bUpdate.setOnAction((action) -> {
			// sceneRegistrationUpdate();
			GUI.updateScene(this.scene);
		});

		this.scene = new Scene(gridPane, 500, 700);
	}

	// Method to return current scene
	public Scene getScene() {
		return this.scene;
	}

	// Method to show a scene where it is possible to create a Registration.
	public void sceneRegistrationCreate() {
		GridPane gridPane = new GridPane();

		// Buttons
		Button bCreate = new Button("Create");

		// Labels;
		Label lCourseTaker = new Label("Course Taker: ");
		Label lCourse = new Label("Course: ");
		Label lDate = new Label("Date: ");
	
  	// ComboBoxes
    Map<String, ArrayList<String>> users = db.getAllUsers();
    ArrayList<String> courseTakerNames = new ArrayList<String>();

    for (String key : users.keySet()) {
      courseTakerNames.add(key);
    }

		ObservableList<String> courseTakers = FXCollections.observableArrayList(courseTakerNames);
		ComboBox<String> courseTaker = new ComboBox<>(courseTakers);

		ObservableList<String> courses = FXCollections.observableArrayList(db.getCourseNames());
		ComboBox<String> course = new ComboBox<>(courses);

    DatePicker publicationDate = new DatePicker();

		// Coordinates for the elements
		gridPane.add(lCourseTaker, 0, 1);
		gridPane.add(courseTaker, 1, 1);

		gridPane.add(lCourse, 0, 2);
		gridPane.add(course, 1, 2);

		gridPane.add(lDate, 0, 3);
		gridPane.add(publicationDate, 1, 3);

		gridPane.add(bCreate, 1, 10);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Functions
		bCreate.setOnAction((action) -> {
			boolean error = false;

			// Checks if all the fields are filled in
			if (courseTaker.getValue() == null || course.getValue() == null || publicationDate.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Make a choice in every combobox!");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if there occured an error, if not create new Registration in database
			if (!error) {
				Date sqlDate = Date.valueOf(publicationDate.getValue());

				Registration registration = new Registration(db.getSpecificUser(courseTaker.getValue()), db.getSpecificCourse(course.getValue()), sqlDate);
				registration.insert();

				sceneRegistrationCreate();
				GUI.updateScene(this.scene);

				Alert errorAlert = new Alert(AlertType.CONFIRMATION);
				errorAlert.setHeaderText("Registration successfully created!");
				errorAlert.showAndWait();
			}
		});

		this.scene = new Scene(gridPane, 700, 700);
	}

	// Method to see an overview of all the Registrations
	// public void sceneRegistrationRead() {
	// 	GridPane gridPane = new GridPane();
	// 	Map<String, ArrayList<String>> Registrations = db.getAllRegistrations();
	// 	int count = 0;

	// 	for (String key : Registrations.keySet()) {
	// 		HBox RegistrationLayer = new HBox();

	// 		Label lEmail = new Label(key);
	// 		RegistrationLayer.getChildren().add(lEmail);

	// 		for (int i = 0; i < Registrations.get(key).size(); i++) {
	// 			Label data = new Label(Registrations.get(key).get(i));
	// 			RegistrationLayer.getChildren().add(data);
	// 			RegistrationLayer.setSpacing(10);
	// 		}

	// 		Button delete = new Button("Delete");
	// 		RegistrationLayer.getChildren().add(delete);

	// 		delete.setOnAction((event) -> {
	// 			db.deleteRegistration(key);
	// 			sceneRegistrationRead();
	// 			GUI.updateScene(this.scene);
	// 		});

	// 		gridPane.add(RegistrationLayer, 0, count);
	// 		count++;
	// 	}

	// 	this.scene = new Scene(gridPane, 500, 500);
	// }

	// Method for altering a Registration
	public void sceneRegistrationUpdate() {
		GridPane gridPane = new GridPane();

		// Buttons
		Button bUpdate = new Button("Update");

		// Labels;
		Label lCourseTaker = new Label("Course Taker: ");
		Label lCourse = new Label("Course: ");
		Label lDate = new Label("Date: ");
	
  	// ComboBoxes
    Map<String, ArrayList<String>> users = db.getAllUsers();
    ArrayList<String> courseTakerNames = new ArrayList<String>();

    for (String key : users.keySet()) {
      courseTakerNames.add(key);
    }

		ObservableList<String> courseTakers = FXCollections.observableArrayList(courseTakerNames);
		ComboBox<String> courseTaker = new ComboBox<>(courseTakers);

		Map<String, ArrayList<String>> courses = db.getAllCourses();
		ArrayList<String> courseNames = new ArrayList<String>();

    for (String key : courses.keySet()) {
      courseNames.add(key);
    }

		ObservableList<String> courseNameOptions = FXCollections.observableArrayList(courseNames);
		ComboBox<String> course = new ComboBox<>(courseNameOptions);

    DatePicker publicationDate = new DatePicker();

		// Coordinates for the elements
		gridPane.add(lCourseTaker, 0, 1);
		gridPane.add(courseTaker, 1, 1);

		gridPane.add(lCourse, 0, 2);
		gridPane.add(course, 1, 2);

		gridPane.add(lDate, 0, 3);
		gridPane.add(publicationDate, 1, 3);

		gridPane.add(bUpdate, 1, 10);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Functions
		bUpdate.setOnAction((action) -> {
			boolean error = false;

			// Checks if all the fields are filled in
			if (courseTaker.getValue() == null || course.getValue() == null || publicationDate.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Make a choice in every combobox!");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if there occured an error, if not create new Registration in database
			if (!error) {
				Date sqlDate = Date.valueOf(publicationDate.getValue());

				Registration registration = new Registration(db.getSpecificUser(courseTaker.getValue()), db.getSpecificCourse(course.getValue()), sqlDate);
				registration.insert();

				sceneRegistrationCreate();
				GUI.updateScene(this.scene);

				Alert errorAlert = new Alert(AlertType.CONFIRMATION);
				errorAlert.setHeaderText("Registration successfully created!");
				errorAlert.showAndWait();
			}
		});

		this.scene = new Scene(gridPane, 700, 700);
	}
}