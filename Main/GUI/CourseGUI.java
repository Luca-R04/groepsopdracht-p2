package Main.GUI;

import Main.ContentItem.Course.Course;
import Main.ContentItem.Course.Level;
import Main.ContentItem.Course.Status;
import Main.ContentItem.Course.Module;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

	// Method to show a scene where it is possible to create a course.
	public void sceneCourseCreate() {
		GridPane gridPane = new GridPane();

		// Buttons
		Button bSubmit = new Button("Create");

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

		ArrayList<Module> modules = db.getAllModules();
		ComboBox<Module> module = new ComboBox<>();
		module.getItems().addAll(modules);

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

			// Checks if there occured an error, if not create new course in database
			if (!error) {
				Date sqlDate = Date.valueOf(publicationDate.getValue());

				Course course = new Course(sqlDate, status.getValue(), name.getText(), topic.getText(), text.getText(), level.getValue());
				course.insert();

				Module m = new Module(module.getValue().getTitle(), module.getValue().getVersion(), module.getValue().getSerialNumber(), module.getValue().getDescription(), module.getValue().getContactPerson(), module.getValue().getCourse());
				course.addModule(m);
				
				sceneCourseCreate();
				GUI.updateScene(this.scene);

				Alert errorAlert = new Alert(AlertType.CONFIRMATION);
				errorAlert.setHeaderText("Course successfully created!");
				errorAlert.showAndWait();
			}
		});

		this.scene = new Scene(gridPane, 700, 700);
	}

	// Method to see an overview of all the courses
	public void sceneCourseRead() {
		TableView<Course> tableView = new TableView<>();

		TableColumn<Course, String> column1 = new TableColumn<>("Name");
    column1.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<Course, String> column2 = new TableColumn<>("Topic");
    column2.setCellValueFactory(new PropertyValueFactory<>("topic"));

    TableColumn<Course, String> column3 = new TableColumn<>("Text");
    column3.setCellValueFactory(new PropertyValueFactory<>("text"));

		TableColumn<Course, Date> column4 = new TableColumn<>("Publication Date");
    column4.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));

		TableColumn<Course, Status> column5 = new TableColumn<>("Status");
    column5.setCellValueFactory(new PropertyValueFactory<>("status"));
	
		TableColumn<Course, String> column6 = new TableColumn<>("Modules");
    column6.setCellValueFactory(new PropertyValueFactory<>("modules"));

		// TableColumn<Course, String> column7 = new TableColumn<>("Residence");
    // column7.setCellValueFactory(new PropertyValueFactory<>("residence"));

		// TableColumn<Course, String> column8 = new TableColumn<>("Postal Code");
    // column8.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

		// TableColumn<Course, String> column9 = new TableColumn<>("Country");
    // column9.setCellValueFactory(new PropertyValueFactory<>("country"));

		// TableColumn<Course, String> column10 = new TableColumn<>("Course Taker");
    // column10.setCellValueFactory(new PropertyValueFactory<>("isCourseTaker"));

		// TableColumn<Course, String> column11 = new TableColumn<>("Staff");
    // column11.setCellValueFactory(new PropertyValueFactory<>("isStaff"));

		// TableColumn<Button, String> column12 = new TableColumn<>("Delete");
    // column12.setCellValueFactory(new PropertyValueFactory<>("Delete"));

    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);
    tableView.getColumns().add(column3);
    tableView.getColumns().add(column4);
    tableView.getColumns().add(column5);
    tableView.getColumns().add(column6);
    // tableView.getColumns().add(column7);
    // tableView.getColumns().add(column8);
    // tableView.getColumns().add(column9);
    // tableView.getColumns().add(column10);
    // tableView.getColumns().add(column11);

		ArrayList<Course> courses = db.getAllCourses();

		for(Course course : courses) {
			tableView.getItems().add(course);
		}


		Button delete = new Button("Delete");

		delete.setOnAction((event) -> {
			Course course = tableView.getSelectionModel().getSelectedItem();
			System.out.println(course.getName());
			course.delete();
			sceneCourseRead();
			GUI.updateScene(this.scene);
		});

		// tableView.getItems().add(delete);

    VBox vBox = new VBox(tableView);
		vBox.getChildren().add(delete);

		this.scene = new Scene(vBox, 500, 500);
	}

	// Method for altering a course
	public void sceneCourseUpdate() {
		GridPane gridPane = new GridPane();

		Button bSubmit = new Button("Update");

		ArrayList<Course> courses = db.getAllCourses();
		ArrayList<String> courseNames = new ArrayList<>();

    for (Course course : courses) {
      courseNames.add(course.getName());
    }

		ObservableList<String> courseNameOptions = FXCollections.observableArrayList(courseNames);

		ComboBox<String> courseName = new ComboBox<>();
		courseName.setItems(courseNameOptions);
		Label lName = new Label("Name: ");
		Label lTopic = new Label("Topic: ");
		Label lText = new Label("Text: ");
		Label lPublicationDate = new Label("Publication Date: ");
		Label lLevel = new Label("Level: ");
		Label lModule = new Label("Modules: "); 
		Label lStatus = new Label("Status: "); 

		TextField name = new TextField();
		TextField topic = new TextField();
		TextField text = new TextField();
		DatePicker publicationDate = new DatePicker();

		ObservableList<Status> statuses = FXCollections.observableArrayList(Status.class.getEnumConstants());
		ComboBox<Status> status = new ComboBox<>(statuses);

		ObservableList<Level> levels = FXCollections.observableArrayList(Level.class.getEnumConstants());
		ComboBox<Level> level = new ComboBox<>(levels);

		ObservableList<Module> modules = FXCollections.observableArrayList(db.getAllModules());
		ComboBox<Module> module = new ComboBox<>(modules);

		// gridPane.add(lName, 0, 1);
		gridPane.add(courseName, 0, 1);

		gridPane.add(lName, 0, 2);
		gridPane.add(name, 1, 2);

		gridPane.add(lTopic, 0, 3);
		gridPane.add(topic, 1, 3);

		gridPane.add(lText, 0, 4);
		gridPane.add(text, 1, 4);

		gridPane.add(lPublicationDate, 0, 5);
		gridPane.add(publicationDate, 1, 5);

		gridPane.add(lLevel, 0, 6);
		gridPane.add(level, 1, 6);

		gridPane.add(lModule, 0, 7);
		gridPane.add(module, 1, 7);

		gridPane.add(lStatus, 0, 8);
		gridPane.add(status, 1, 8);

		gridPane.add(bSubmit, 1, 10);

		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		bSubmit.setOnAction((action) -> {
			boolean error = false;

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

			// Checks if there occured an error, if not update the specified course 
			if (!error) {
				Date sqlDate = Date.valueOf(publicationDate.getValue());
				Course course = new Course(sqlDate, status.getValue(), courseName.getValue(), topic.getText(), text.getText(), level.getValue());
				course.update(sqlDate, status.getValue(), name.getText(), topic.getText(), text.getText(), level.getValue());

				sceneCourseUpdate();
				GUI.updateScene(this.scene);

				Alert errorAlert = new Alert(AlertType.CONFIRMATION);
				errorAlert.setHeaderText("Course successfully updated!");
				errorAlert.showAndWait();
			}
		});

		gridPane.setAlignment(Pos.CENTER);

		this.scene = new Scene(gridPane, 700, 700);
	}
}