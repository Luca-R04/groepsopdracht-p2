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
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
			sceneRegistrationRead();
			GUI.updateScene(this.scene);
		});

		bUpdate.setOnAction((action) -> {
			// sceneRegistrationUpdate();
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
		Label lDate = new Label("Date: ");
	
  	// ComboBoxes
    ArrayList<User> users = db.getUsers();
    ArrayList<String> courseTakerNames = new ArrayList<>();

    for (User user : users) {
      courseTakerNames.add(user.getEmail());
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

				Registration registration = new Registration(currentUser, currentCourse, sqlDate);
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

		TableColumn<Registration, String> column1 = new TableColumn<>("Course Taker Name");
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

    VBox vBox = new VBox(tableView);
		vBox.getChildren().add(delete);

		this.scene = new Scene(vBox, 500, 500);
	}

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
    ArrayList<User> users = db.getUsers();
    ArrayList<String> courseTakerNames = new ArrayList<>();

    for (User user : users) {
      courseTakerNames.add(user.getEmail());
    }

		ObservableList<String> courseTakers = FXCollections.observableArrayList(courseTakerNames);
		ComboBox<String> courseTaker = new ComboBox<>(courseTakers);

		ArrayList<Course> courses = db.getAllCourses();
		ArrayList<String> courseNames = new ArrayList<>();

    for (Course course : courses) {
      courseNames.add(course.getName());
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

				Registration registration = new Registration(currentUser, currentCourse, sqlDate);
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