package Main.GUI;

import Main.ContentItem.Course.Course;
import Main.ContentItem.Course.Level;
import Main.ContentItem.Course.Status;
import Main.Database.Database;
import Main.User.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

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

public class CourseGUI {
	private Database db = new Database();
	public Scene scene;

	public CourseGUI() {
		// Creating all the buttons
		Button bCreate = new Button("Create a course");
		Button bRead = new Button("View courses");
		Button bUpdate = new Button("Update course");

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
			sceneCourseCreate();
			GUI.updateScene(this.scene);
		});

		bRead.setOnAction((action) -> {
			sceneCourseRead();
			GUI.updateScene(this.scene);
		});

		bUpdate.setOnAction((action) -> {
			sceneCourseUpdate();
			GUI.updateScene(this.scene);
		});

		this.scene = new Scene(gridPane, 500, 700);
	}

	// Method to return current scene
	public Scene getScene() {
		return this.scene;
	}

	// Method for the user to fill in data.
	public void sceneCourseCreate() {
		GridPane gridPane = new GridPane();

		// Buttons
		Button bSubmit = new Button("Submit");

		// Labels;
		Label lName = new Label("Name: ");
		Label lTopic = new Label("Topic: ");
		Label lText = new Label("Text: ");
		Label lPublicationDate = new Label("Publication Date: ");
		Label lLevel = new Label("Level:");
		Label lModule = new Label("Modules:"); 
		Label lStatus = new Label("Status:"); 

		// Text fields
		TextField name = new TextField();
		TextField topic = new TextField();
		TextField text = new TextField();
		DatePicker publicationDate = new DatePicker();

		// ComboBoxes
		ObservableList<Status> statuses = FXCollections.observableArrayList(Status.class.getEnumConstants());
		ComboBox<Status> status = new ComboBox<>(statuses);

		ObservableList<Level> levels = FXCollections.observableArrayList(Level.class.getEnumConstants());
		ComboBox<Level> level = new ComboBox<>(levels);

		ObservableList<String> modules = FXCollections.observableArrayList(db.getAllModules());
		ComboBox<String> module = new ComboBox<>(modules);

		// Coordinates for the elements
		gridPane.add(lName, 0, 1);
		gridPane.add(name, 1, 1);

		gridPane.add(lTopic, 0, 2);
		gridPane.add(topic, 1, 2);

		gridPane.add(lText, 0, 3);
		gridPane.add(text, 1, 3);

		gridPane.add(lPublicationDate, 0, 4);
		gridPane.add(publicationDate, 1, 4);

		gridPane.add(lLevel, 0, 5);
		gridPane.add(level, 1, 5);

		gridPane.add(lModule, 0, 6);
		gridPane.add(module, 1, 6);

		gridPane.add(lStatus, 0, 7);
		gridPane.add(status, 1, 7);

		gridPane.add(bSubmit, 1, 10);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Functions
		bSubmit.setOnAction((action) -> {
			boolean error = false;

			// Checks if all the fields are filled in
			if (name.getText().isEmpty() || topic.getText().isEmpty() || topic.getText().isEmpty() || level.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Fill in all the fields");
				errorAlert.showAndWait();
				error = true;
			}

			if (publicationDate.getValue() == null || module.getValue() == null || status.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Make a choice in every combobox!");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if there occured an error, if not create new user in database
			if (!error) {
				Date sqlDate = Date.valueOf(publicationDate.getValue());

				Course c = new Course(sqlDate, status.getValue(), name.getText(), topic.getText(), text.getText(), level.getValue());

				sceneCourseCreate();
				GUI.updateScene(this.scene);
			}
		});

		this.scene = new Scene(gridPane, 700, 700);
	}

	// Method to see a overview of the users
	public void sceneCourseRead() {
		GridPane gridPane = new GridPane();
		Map<String, ArrayList<String>> courses = db.getAllCourses();
		int count = 0;

		for (String key : courses.keySet()) {
			HBox courseLayer = new HBox();

			Label lEmail = new Label(key);
			courseLayer.getChildren().add(lEmail);

			for (int i = 0; i < courses.get(key).size(); i++) {
				Label data = new Label(courses.get(key).get(i));
				courseLayer.getChildren().add(data);
				courseLayer.setSpacing(10);
			}

			Button delete = new Button("Delete");
			courseLayer.getChildren().add(delete);

			delete.setOnAction((event) -> {
				db.deleteCourse(key);
				sceneCourseRead();
				GUI.updateScene(this.scene);
			});

			gridPane.add(courseLayer, 0, count);
			count++;
		}

		this.scene = new Scene(gridPane, 500, 500);
	}

	// Method for altering a user
	public void sceneCourseUpdate() {
		GridPane gridPane = new GridPane();

		Button bSubmit = new Button("Submit");

		// Labels;
		Label lEmail = new Label("Email: ");
		Label lName = new Label("Name: ");
		Label lBirthdate = new Label("Birthdate: ");
		Label lGender = new Label("Gender: ");
		Label lAddress = new Label("Address:");
		Label lCountry = new Label("Country:");
		Label lResidence = new Label("Residence: ");
		Label lCourse = new Label("Course taker: ");
		Label lStaff = new Label("Staff: ");

		// Text fields
		TextField email = new TextField();
		TextField name = new TextField();
		DatePicker birthdate = new DatePicker();
		TextField gender = new TextField();
		TextField address = new TextField();
		TextField country = new TextField();
		TextField residence = new TextField();

		// comboBox
		ObservableList<String> options = FXCollections.observableArrayList("Yes", "No");

		ComboBox<String> course = new ComboBox<>(options);
		ComboBox<String> staff = new ComboBox<>(options);

		// Coordinates for the elements
		gridPane.add(lEmail, 0, 1);
		gridPane.add(email, 1, 1);

		gridPane.add(lName, 0, 2);
		gridPane.add(name, 1, 2);

		gridPane.add(lBirthdate, 0, 3);
		gridPane.add(birthdate, 1, 3);

		gridPane.add(lGender, 0, 4);
		gridPane.add(gender, 1, 4);

		gridPane.add(lAddress, 0, 5);
		gridPane.add(address, 1, 5);

		gridPane.add(lCountry, 0, 6);
		gridPane.add(country, 1, 6);

		gridPane.add(lResidence, 0, 7);
		gridPane.add(residence, 1, 7);

		gridPane.add(lCourse, 0, 8);
		gridPane.add(course, 1, 8);

		gridPane.add(lStaff, 0, 9);
		gridPane.add(staff, 1, 9);

		gridPane.add(bSubmit, 1, 10);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Functions
		bSubmit.setOnAction((action) -> {
			boolean error = false;

			// Checks if all the fields are filled in
			if (email.getText().isEmpty() || name.getText().isEmpty() || gender.getText().isEmpty()
					|| address.getText().isEmpty() || residence.getText().isEmpty()) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Fill in all the fields");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if a date is picked
			if (birthdate.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Pick a date");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if they picked either staff or user, cant be staff and user
			if ((course.getValue() == "Yes" && staff.getValue() == "Yes")
					|| (course.getValue() == "No" && staff.getValue() == "No")) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("You have to pick Staff or User");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if there occured an error, if not update the specified user 
			if (!error) {
				String isCourseTaker = null;
				String isStaff = null;

				if (staff.getValue().equals("Yes")) {
					isStaff = "1";
				} else {
					isCourseTaker = "1";
				}

				// Werkt nog niet 
				Date sqlDate = Date.valueOf(birthdate.getValue());
				User u = new User(email.getText(), name.getText(), sqlDate, gender.getText(), address.getText(), residence.getText(), country.getText(), isCourseTaker, isStaff);
				// u.update(email.getText(), name.getText(), sqlDate, gender.getText(), address.getText(), residence.getText(), country.getText(), isCourseTaker, isStaff);
			}
		});

		this.scene = new Scene(gridPane, 600, 700);
	}
}