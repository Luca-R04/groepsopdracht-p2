package Main.GUI;

import Main.Database.Database;
import Main.ContentItem.Course.Course;
import Main.User.Registration;
import Main.User.User;

import java.sql.Date;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/*
	This class provides the GUI for the CRUD operations for the object Registration. 
	It is possible to create, read, update and delete Registration objects from the database. 
*/

public class RegistrationGUI {
  private Database db = new Database();
	public Scene scene;

	public RegistrationGUI() {
		// Creating all the buttons
		Button bCreate = new Button("Create Registration");
		Button bRead = new Button("View Registrations");

		// New Gridpane
		GridPane gridPane = new GridPane();

		// Specifies coordinates for the buttons
		gridPane.add(bCreate, 0, 1);
		gridPane.add(bRead, 1, 1);

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
			sceneRegistrationRead();
			GUI.updateScene(this.scene);
		});

		this.scene = new Scene(gridPane, 600, 500);
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
	
  	// ComboBoxes
    ArrayList<User> users = db.getUsers();
    ArrayList<String> courseTakerNames = new ArrayList<>();

    for (User user : users) {
			if (user.getIsCourseTaker() != null) {
				courseTakerNames.add(user.getEmail());
			}
    }

		ObservableList<String> courseTakers = FXCollections.observableArrayList(courseTakerNames);
		ComboBox<String> courseTaker = new ComboBox<>(courseTakers);

		ArrayList<Course> courses = db.getAllCourses();
    ArrayList<String> courseTitles = new ArrayList<>();

    for (Course course : courses) {
      courseTitles.add(course.getName());
    }

		ObservableList<String> courseTitleOptions = FXCollections.observableArrayList(courseTitles);
		ComboBox<String> course = new ComboBox<>(courseTitleOptions);

		// Coordinates for the elements
		gridPane.add(lCourseTaker, 0, 1);
		gridPane.add(courseTaker, 1, 1);

		gridPane.add(lCourse, 0, 2);
		gridPane.add(course, 1, 2);

		gridPane.add(bCreate, 1, 6);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Functions
		bCreate.setOnAction((action) -> {
			boolean error = false;

			// Checks if all the fields are filled in
			if (courseTaker.getValue() == null || course.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Make a choice in every combobox!");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if there occured an error, if not create new Registration in database
			if (!error) {
				User currentUser = null;
				for (User user : users) {
					if (user.getEmail().equals(courseTaker.getValue())) {
						currentUser = user;
					}
				}

				Course currentCourse = null;
				for(Course c : courses) {
					if (c.getName().equals(course.getValue())) {
						currentCourse = c;
					}
				}

				Registration registration = new Registration(currentUser, currentCourse, new Date(System.currentTimeMillis()));
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
	public void sceneRegistrationRead() {
		TableView<Registration> tableView = new TableView<>();

		TableColumn<Registration, String> column1 = new TableColumn<>("Course Taker Email");
    column1.setCellValueFactory(new PropertyValueFactory<>("courseTaker"));

    TableColumn<Registration, String> column2 = new TableColumn<>("Course");
    column2.setCellValueFactory(new PropertyValueFactory<>("course"));

    TableColumn<Registration, String> column3 = new TableColumn<>("Date");
    column3.setCellValueFactory(new PropertyValueFactory<>("date"));

    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);
    tableView.getColumns().add(column3);

		ArrayList<Registration> registrations = db.getRegistrations();

		for(Registration registration : registrations) {
			tableView.getItems().add(registration);
		}

		Button delete = new Button("Delete");

		delete.setOnAction((event) -> {
			Registration registration = tableView.getSelectionModel().getSelectedItem();
			registration.delete();
			sceneRegistrationRead();
			GUI.updateScene(this.scene);
		});

		Button update = new Button("Update");

		update.setOnAction((event) -> {
			Registration registration = tableView.getSelectionModel().getSelectedItem();
			sceneRegistrationUpdate(registration);
			GUI.updateScene(this.scene);
		});

    VBox vBox = new VBox(tableView);
		HBox buttons = new HBox();

		buttons.setStyle("-fx-font-size: 1.5em; -fx-padding: 1.5em;");

		buttons.getChildren().addAll(delete, update); 
		vBox.getChildren().add(buttons);

		this.scene = new Scene(vBox, 500, 500);
	}

	// Method for altering a Registration
	public void sceneRegistrationUpdate(Registration registration) {
		GridPane gridPane = new GridPane();

		// Buttons
		Button bUpdate = new Button("Update");

		// Labels;
		Label lCourseTaker = new Label("Course Taker: ");
		Label lCourse = new Label("Course: ");
	
  	// ComboBoxes
		ArrayList<User> users = db.getUsers();
  
		Label courseTaker = new Label("");

		ArrayList<Course> courses = db.getAllCourses();
		ArrayList<String> courseNames = new ArrayList<>();

    for (Course course : courses) {
      courseNames.add(course.getName());
    }

		ObservableList<String> courseNameOptions = FXCollections.observableArrayList(courseNames);
		ComboBox<String> course = new ComboBox<>(courseNameOptions);

		courseTaker.setText(registration.getCourseTaker().getEmail());
		course.setValue(registration.getCourse().getName());

		// Coordinates for the elements
		gridPane.add(lCourseTaker, 0, 1);
		gridPane.add(courseTaker, 1, 1);

		gridPane.add(lCourse, 0, 2);
		gridPane.add(course, 1, 2);

		gridPane.add(bUpdate, 1, 10);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Functions
		bUpdate.setOnAction((action) -> {
			boolean error = false;

			// Checks if all the fields are filled in
			if (courseTaker.getText() == null || course.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Make a choice in every combobox!");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if there occured an error, if not create new Registration in database
			if (!error) {
				User currentUser = null;
				for (User user : users) {
					if (user.getEmail().equals(courseTaker.getText())) {
						currentUser = user;
					}
				}

				Course currentCourse = null;
				for(Course c : courses) {
					if (c.getName().equals(course.getValue())) {
						currentCourse = c;
					}
				}

				registration.update(currentUser, currentCourse);

				sceneRegistrationRead();
				GUI.updateScene(this.scene);

				Alert errorAlert = new Alert(AlertType.CONFIRMATION);
				errorAlert.setHeaderText("Registration successfully created!");
				errorAlert.showAndWait();
			}
		});

		this.scene = new Scene(gridPane, 700, 700);
	}
}