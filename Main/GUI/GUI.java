package Main.GUI;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Main.Database.Database;
import Main.User.User;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GUI extends Application {
	Stage stage;
	Scene scene;
	Database db = new Database();

	@Override
	public void start(Stage stage) {
		
		this.stage = stage;
		
		// Changes the title of the window.
		this.stage.setTitle("Codecademy: Luca Rinck - 218...., Martijn Schemers - 2184875, Max Stokla - 2186459, Laurens Weterings - 2189413");

		// Changes the scene using the startScene method
		startScene();


		// Displays the stage element
		this.stage.show();
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
	ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Yes",
        "No"
    );

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
		if (email.getText().isEmpty() || name.getText().isEmpty() || gender.getText().isEmpty() || address.getText().isEmpty() || residence.getText().isEmpty()) {
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
		if ((course.getValue() == "Yes" && staff.getValue() == "Yes") || (course.getValue() == "No" && staff.getValue() == "No")) {
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

			db.createUser(email.getText(), name.getText(), sqlDate, gender.getText(), address.getText(), residence.getText(), country.getText(), isCourseTaker, isStaff);
		}
	});


	this.scene = new Scene(gridPane, 500, 700);
	this.stage.setScene(this.scene);

}


		// Method to see a overview of the users
public void sceneUserRead() {

	GridPane gridPane = new GridPane();
	
	///////////////////////////////////////////////////////////////////////////

	// TableView Columns
	TableColumn<User, String> emailColumn = new TableColumn<>();
	emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

	TableColumn<User, String> nameColumn = new TableColumn<>();
	nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

	TableColumn<User, Date> birthdateColumn = new TableColumn<>();
	birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));

	TableColumn<User, String> genderColumn = new TableColumn<>();
	genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

	TableColumn<User, String> addressColumn = new TableColumn<>();
	addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

	TableColumn<User, String> residenceColumn = new TableColumn<>();
	residenceColumn.setCellValueFactory(new PropertyValueFactory<>("residence"));

	TableColumn<User, String> countryColumn = new TableColumn<>();
	countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

	TableColumn<User, String> isCourseTakerColumn = new TableColumn<>();
	isCourseTakerColumn.setCellValueFactory(new PropertyValueFactory<>("isCourseTaker"));

	TableColumn<User, String> isStaffColumn = new TableColumn<>();
	isStaffColumn.setCellValueFactory(new PropertyValueFactory<>("isStaff"));

	///////////////////////////////////////////////////////////////////////////

	// TableView

	TableView<User> table = new TableView<>();
	table.setItems(db.getUsersGUI());
	table.getColumns().addAll(emailColumn, nameColumn, birthdateColumn, genderColumn, addressColumn, residenceColumn, countryColumn, isCourseTakerColumn, isStaffColumn);
	
	gridPane.getChildren().add(table);
	

	this.scene = new Scene(gridPane, 500, 500);
	this.stage.setScene(this.scene);

}


		// Method for altering a user
public void sceneUserUpdate() {

	GridPane gridPane = new GridPane();
	gridPane.getChildren().add(new Label("Update"));


	this.scene = new Scene(gridPane, 500, 500);
	this.stage.setScene(this.scene);

}


		// Method for deleting a user
public void sceneUserDelete() {		

	GridPane gridPane = new GridPane();
	gridPane.getChildren().add(new Label("Delete"));


	this.scene = new Scene(gridPane, 500, 500);
	this.stage.setScene(this.scene);

}


// Method that gives options for the user to navigate through the CRUD system
public void sceneUser() {

	// Creating all the buttons	
	Button bCreate = new Button("Create a user");
	Button bRead = new Button("View users");
	Button bUpdate = new Button("Update users");
	Button bDelete = new Button("Delete users");

	// New Gridpane
	GridPane gridPane = new GridPane();

	// Specifies coordinates for the buttons
	gridPane.add(bCreate, 0, 1);
	gridPane.add(bRead, 1, 1);

	gridPane.add(bUpdate, 0, 2);
	gridPane.add(bDelete, 1, 2);
	
	
	// Styling
	gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
	gridPane.setVgap(10); 
	gridPane.setHgap(10);


	// Button functions

	// bCreate
	bCreate.setOnAction((action) -> {
		sceneUserCreate();
	});

	bRead.setOnAction((action) -> {
		sceneUserRead();
	});

	bUpdate.setOnAction((action) -> {
		sceneUserUpdate();
	});

	bDelete.setOnAction((action) -> {
		sceneUserDelete();
	});

	// Change the scene
	this.scene = new Scene(gridPane, 500, 500);
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

		// Button functions

		// bUser
		bUser.setOnAction((event) -> {
			sceneUser();
		});
	}

	

}
