package Main.GUI;

import Main.Database.Database;
import Main.User.Gender;
import Main.User.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserGUI {
	private Database db = new Database();
	public Scene scene;

	public UserGUI() {
		// Creating all the buttons
		Button bCreate = new Button("Create a user");
		Button bRead = new Button("View users");
		Button bUpdate = new Button("Update users");

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
			sceneUserCreate();
			GUI.updateScene(this.scene);
		});

		bRead.setOnAction((action) -> {
			sceneUserRead();
			GUI.updateScene(this.scene);
		});

		bUpdate.setOnAction((action) -> {
			sceneUserUpdate();
			GUI.updateScene(this.scene);
		});

		this.scene = new Scene(gridPane, 600, 500);
	}

	// Method to return current scene
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
		Label lPostal = new Label("Postal code:");
		Label lCountry = new Label("Country:");
		Label lResidence = new Label("Residence: ");
		Label lCourse = new Label("Course taker: ");
		Label lStaff = new Label("Staff: ");

		// Text fields
		TextField email = new TextField();
		TextField FirstName = new TextField();
		TextField LastName = new TextField();
		DatePicker birthdate = new DatePicker();
		TextField address = new TextField();
		TextField postal = new TextField();
		TextField country = new TextField();
		TextField residence = new TextField();

		// comboBox
		ObservableList<String> options = FXCollections.observableArrayList("Yes", "No");

		ComboBox<String> course = new ComboBox<>(options);
		ComboBox<String> staff = new ComboBox<>(options);

		ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.class.getEnumConstants());
		ComboBox<Gender> gender = new ComboBox<>(genders);

		// Coordinates for the elements
		gridPane.add(lEmail, 0, 1);
		gridPane.add(email, 1, 1);

		gridPane.add(lName, 0, 2);
		gridPane.add(FirstName, 1, 2);

		gridPane.add(lBirthdate, 0, 3);
		gridPane.add(birthdate, 1, 3);

		gridPane.add(lGender, 0, 4);
		gridPane.add(gender, 1, 4);

		gridPane.add(lAddress, 0, 5);
		gridPane.add(address, 1, 5);

		gridPane.add(lPostal, 0, 6);
		gridPane.add(postal, 1, 6);

		gridPane.add(lResidence, 0, 7);
		gridPane.add(residence, 1, 7);

		gridPane.add(lCountry, 0, 8);
		gridPane.add(country, 1, 8);

		gridPane.add(lCourse, 0, 9);
		gridPane.add(course, 1, 9);

		gridPane.add(lStaff, 0, 10);
		gridPane.add(staff, 1, 10);

		gridPane.add(bSubmit, 1, 11);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Functions
		bSubmit.setOnAction((action) -> {
			boolean error = false;

			// Checks if all the fields are filled in
			if (email.getText().isEmpty() || FirstName.getText().isEmpty() || address.getText().isEmpty()
					|| residence.getText().isEmpty()) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Fill in all the fields");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if a date is picked
			if (birthdate.getValue() == null || gender.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Make a choice in every combobox!");
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

			// Checks if there occured an error, if not create new user in database
			if (!error) {
				String isCourseTaker = null;
				String isStaff = null;

				if (staff.getValue().equals("Yes")) {
					isStaff = "1";
				} else {
					isCourseTaker = "1";
				}

				Date sqlDate = Date.valueOf(birthdate.getValue());
				User u = new User(email.getText(), FirstName.getText(), LastName.getText(), sqlDate, gender.getValue(),
						address.getText(), postal.getText(), residence.getText(), country.getText(), isCourseTaker,
						isStaff);
				u.insert();
			}
		});

		this.scene = new Scene(gridPane, 600, 750);
	}

	// Method to see a overview of the users
	public void sceneUserRead() {
		TableView<User> tableView = new TableView<>();

		TableColumn<User, String> column1 = new TableColumn<>("Email");
    column1.setCellValueFactory(new PropertyValueFactory<>("email"));

    TableColumn<User, String> column2 = new TableColumn<>("First Name");
    column2.setCellValueFactory(new PropertyValueFactory<>("FirstName"));

    TableColumn<User, String> column3 = new TableColumn<>("Last Name");
    column3.setCellValueFactory(new PropertyValueFactory<>("LastName"));

		TableColumn<User, Date> column4 = new TableColumn<>("Birthdate");
    column4.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

		TableColumn<User, Gender> column5 = new TableColumn<>("Gender");
    column5.setCellValueFactory(new PropertyValueFactory<>("gender"));

		TableColumn<User, String> column6 = new TableColumn<>("Address");
    column6.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<User, String> column7 = new TableColumn<>("Residence");
    column7.setCellValueFactory(new PropertyValueFactory<>("residence"));

		TableColumn<User, String> column8 = new TableColumn<>("Postal Code");
    column8.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

		TableColumn<User, String> column9 = new TableColumn<>("Country");
    column9.setCellValueFactory(new PropertyValueFactory<>("country"));

		TableColumn<User, String> column10 = new TableColumn<>("Course Taker");
    column10.setCellValueFactory(new PropertyValueFactory<>("isCourseTaker"));

		TableColumn<User, String> column11 = new TableColumn<>("Staff");
    column11.setCellValueFactory(new PropertyValueFactory<>("isStaff"));

		TableColumn<Button, String> column12 = new TableColumn<>("Delete");
    column12.setCellValueFactory(new PropertyValueFactory<>("Delete"));

    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);
    tableView.getColumns().add(column3);
    tableView.getColumns().add(column4);
    tableView.getColumns().add(column5);
    tableView.getColumns().add(column6);
    tableView.getColumns().add(column7);
    tableView.getColumns().add(column8);
    tableView.getColumns().add(column9);
    tableView.getColumns().add(column10);
    tableView.getColumns().add(column11);

		ArrayList<User> users = db.getUsers();

		for(User user : users) {
			tableView.getItems().add(user);
		}

		Button delete = new Button("Delete");

		delete.setOnAction((event) -> {
			User user = tableView.getSelectionModel().getSelectedItem();
			user.delete();
			sceneUserRead();
			GUI.updateScene(this.scene);
		});

    VBox vbox = new VBox(tableView);
		vbox.getChildren().add(delete);

		this.scene = new Scene(vbox, 800, 800);
	}

	// Method for altering a user
	public void sceneUserUpdate() {
		GridPane gridPane = new GridPane();

		Button bUpdate = new Button("Update");

		// Labels;
		Label lUser = new Label("User: ");
		Label lEmail = new Label("Email: ");
		Label lName = new Label("First name: ");
		Label lLastName = new Label("Last name: ");
		Label lBirthdate = new Label("Birthdate: ");
		Label lGender = new Label("Gender: ");
		Label lAddress = new Label("Address:");
		Label lPostal = new Label("Postal code");
		Label lCountry = new Label("Country:");
		Label lResidence = new Label("Residence: ");

		ArrayList<User> users = db.getUsers();
		ArrayList<String> userEmails = new ArrayList<>();

		for (User user : users) {
			userEmails.add(user.getEmail());
		}

		ObservableList<String> userEmailOptions = FXCollections.observableArrayList(userEmails);

		// Text fields
		ComboBox<String> userEmail = new ComboBox<>();
		userEmail.setItems(userEmailOptions);
		TextField email = new TextField();
		TextField FirstName = new TextField();
		TextField LastName = new TextField();
		DatePicker birthdate = new DatePicker();
		TextField address = new TextField();
		TextField postal = new TextField();
		TextField country = new TextField();
		TextField residence = new TextField();

		ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.class.getEnumConstants());
		ComboBox<Gender> gender = new ComboBox<>(genders);

		// When a user is selected from the comboBox the method will display it's data
		userEmail.valueProperty().addListener((obs, oldItem, newItem) -> {
			FirstName.textProperty().unbind();
			if (newItem == null) {
				FirstName.setText("");
			} else {
				User currentUser = null;
				for (User user : users) {
					if (user.getEmail().equals(userEmail.getValue())) {
						currentUser = user;
					}
				}
			
				email.setText(userEmail.getValue());
				FirstName.setText(currentUser.getFirstName());
				LastName.setText(currentUser.getLastName());

				// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				// LocalDate date = LocalDate.parse(currentUser.getBirthDate(), formatter);
				// birthdate.setValue(date);

				address.setText(currentUser.getAddress());
				postal.setText(currentUser.getPostalCode());
				country.setText(currentUser.getCountry());
				residence.setText(currentUser.getResidence());
			}
		});

		// Coordinates for the elements
		gridPane.add(lUser, 0, 1);
		gridPane.add(userEmail, 1, 1);

		gridPane.add(lEmail, 0, 2);
		gridPane.add(email, 1, 2);

		gridPane.add(lName, 0, 3);
		gridPane.add(FirstName, 1, 3);

		gridPane.add(lLastName, 0, 4);
		gridPane.add(LastName, 1, 4);

		gridPane.add(lBirthdate, 0, 5);
		gridPane.add(birthdate, 1, 5);

		gridPane.add(lGender, 0, 6);
		gridPane.add(gender, 1, 6);

		gridPane.add(lAddress, 0, 7);
		gridPane.add(address, 1, 7);

		gridPane.add(lPostal, 0, 8);
		gridPane.add(postal, 1, 8);

		gridPane.add(lResidence, 0, 9);
		gridPane.add(residence, 1, 9);

		gridPane.add(lCountry, 0, 10);
		gridPane.add(country, 1, 10);

		gridPane.add(bUpdate, 1, 11);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		// Functions
		bUpdate.setOnAction((action) -> {
			boolean error = false;

			// Checks if all the fields are filled in
			if (email.getText().isEmpty() || FirstName.getText().isEmpty() || LastName.getText().isEmpty()
					|| address.getText().isEmpty() || residence.getText().isEmpty()) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Fill in all the fields");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if a date is picked
			if (birthdate.getValue() == null || gender.getValue() == null || userEmail.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Make a choice in every combobox!");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if there occured an error, if not update the specified user
			if (!error) {
				String isCourseTaker = null;
				String isStaff = null;

				Date sqlDate = Date.valueOf(birthdate.getValue());
				User u = new User(userEmail.getValue(), FirstName.getText(), LastName.getText(), sqlDate,
						gender.getValue(), address.getText(), postal.getText(), residence.getText(), country.getText(),
						isCourseTaker, isStaff);
				u.update(email.getText(), FirstName.getText(), LastName.getText(), sqlDate, gender.getValue(),
						address.getText(), postal.getText(), residence.getText(), country.getText());

				sceneUserRead();
				GUI.updateScene(this.scene);

				Alert errorAlert = new Alert(AlertType.CONFIRMATION);
				errorAlert.setHeaderText("User successfully updated!");
				errorAlert.showAndWait();
			}
		});

		this.scene = new Scene(gridPane, 700, 700);
	}

	// Method that gives options for the user to navigate through the CRUD system
	public void sceneUser() {
		// Creating all the buttons
		Button bCreate = new Button("Create a user");
		Button bRead = new Button("View users");
		Button bUpdate = new Button("Update users");

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
			sceneUserCreate();
		});

		bRead.setOnAction((action) -> {
			sceneUserRead();
		});

		bUpdate.setOnAction((action) -> {
			sceneUserUpdate();
		});

		// Change the scene
		this.scene = new Scene(gridPane, 500, 500);
	}
}