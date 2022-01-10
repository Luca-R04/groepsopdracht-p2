package Main.GUI;

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

public class UserGUI {
	private Database db = new Database();
	// private final Group root;
	public Scene scene;

	public UserGUI() {
		// Creating all the buttons
		Button bCreate = new Button("Create a user");
		Button bRead = new Button("View users");
		Button bUpdate = new Button("Update users");
		// Button bDelete = new Button("Delete users");

		// New Gridpane
		GridPane gridPane = new GridPane();

		// Specifies coordinates for the buttons
		gridPane.add(bCreate, 0, 1);
		gridPane.add(bRead, 1, 1);

		gridPane.add(bUpdate, 0, 2);
		// gridPane.add(bDelete, 1, 2);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Button functions
		bCreate.setOnAction((action) -> {
			sceneUserCreate();
			GUI.updateScene(this.scene);
		});

		bRead.setOnAction((action) -> {
			sceneUserRead();
		});

		bUpdate.setOnAction((action) -> {
			sceneUserUpdate();
		});

		this.scene = new Scene(gridPane, 500, 700);
	}

	public Scene getScene() {
		return this.scene;
	}

	// Method for the user to fill in data.
	public void sceneUserCreate() {
		GridPane gridPane = new GridPane();

		// Buttons
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
		ObservableList<String> options = FXCollections.observableArrayList(
				"Yes",
				"No");

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

			if (!error) {
				String isCourseTaker = null;
				String isStaff = null;

				if (staff.getValue().equals("Yes")) {
					isStaff = "1";
				} else {
					isCourseTaker = "1";
				}

				Date sqlDate = Date.valueOf(birthdate.getValue());
				User u = new User(email.getText(), name.getText(), sqlDate, gender.getText(), address.getText(), residence.getText(), country.getText(), isCourseTaker, isStaff);
			}
		});

		
		this.scene = new Scene(gridPane, 500, 700);
		// Scene scene = new Scene(gridPane, 500, 700);
	}

	// Method to see a overview of the users
	public void sceneUserRead() {
		GridPane gridPane = new GridPane();
		Map<String, ArrayList<String>> users = db.getAllUsers();
		int count = 0;

		for (String key : users.keySet()) {
			HBox userLayer = new HBox();

			Label lEmail = new Label(key);
			userLayer.getChildren().add(lEmail);

			for (int i = 0; i < users.get(key).size(); i++) {
				Label data = new Label(users.get(key).get(i));
				userLayer.getChildren().add(data);
				userLayer.setSpacing(10);
			}

			Button update = new Button("Update");
			Button delete = new Button("Delete");
			userLayer.getChildren().add(update);
			userLayer.getChildren().add(delete);

			delete.setOnAction((event) -> {
				// db.deleteUser(key);
				sceneUserRead();
			});

			update.setOnAction((event) -> {
				sceneUserUpdate();
			});

			gridPane.add(userLayer, 0, count);
			count++;
		}

		// this.scene = new Scene(gridPane, 500, 500);
		// this.stage.setScene(this.scene);
	}

	// Method for altering a user
	public void sceneUserUpdate() {
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
		ObservableList<String> options = FXCollections.observableArrayList(
				"Yes",
				"No");

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

			if (!error) {
				String isCourseTaker = null;
				String isStaff = null;

				if (staff.getValue().equals("Yes")) {
					isStaff = "1";
				} else {
					isCourseTaker = "1";
				}

				Date sqlDate = Date.valueOf(birthdate.getValue());

				// db.updateUser(email.getText(), name.getText(), sqlDate, gender.getText(), address.getText(), residence.getText(), country.getText(), isCourseTaker, isStaff);
			}
		});

		// this.scene = new Scene(gridPane, 600, 700);
		// this.stage.setScene(this.scene);
	}

	// Method that gives options for the user to navigate through the CRUD system
	public void sceneUser() {
		// Creating all the buttons
		Button bCreate = new Button("Create a user");
		Button bRead = new Button("View users");
		Button bUpdate = new Button("Update users");
		// Button bDelete = new Button("Delete users");

		// New Gridpane
		GridPane gridPane = new GridPane();

		// Specifies coordinates for the buttons
		gridPane.add(bCreate, 0, 1);
		gridPane.add(bRead, 1, 1);

		gridPane.add(bUpdate, 0, 2);
		// gridPane.add(bDelete, 1, 2);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Button functions
		bCreate.setOnAction((action) -> {
			sceneUserCreate();
		});

		bRead.setOnAction((action) -> {
			sceneUserRead();
		});

		bUpdate.setOnAction((action) -> {
			sceneUserUpdate();
		});

		// bDelete.setOnAction((action) -> {
		// sceneUserDelete();
		// });

		// Change the scene
		// GUI.scene = new Scene(gridPane, 500, 500);
		// this.stage.setScene(this.scene);
	}
}