package Main.GUI;

// GUI class for the Course object. It is possible to create, read, delete and update a course . 

import Main.ContentItem.Course.Course;
import Main.ContentItem.Course.Level;
import Main.ContentItem.Course.Status;
import Main.ContentItem.Course.Module;
import Main.Database.Database;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
	private GUI gui = new GUI();
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
		Menu navbar = new Menu("NavBar");
		MenuItem home = new MenuItem("Home");
		home.setOnAction(e -> {
			gui.startScene();
		});

		navbar.getItems().add(home);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(navbar);

		VBox menu = new VBox(menuBar);

		GridPane gridPane = new GridPane();

		// Buttons
		Button bSubmit = new Button("Create");

		// Labels;
		Label lName = new Label("Name: ");
		Label lTopic = new Label("Topic: ");
		Label lText = new Label("Text: ");
		Label lPublicationDate = new Label("Publication Date: ");
		Label lLevel = new Label("Level: ");
		Label lModule = new Label("Modules: ");
		Label lStatus = new Label("Status: ");

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
		ObservableList<Module> moduleOptions = FXCollections.observableArrayList(modules);
		ComboBox<Module> moduleChoice = new ComboBox<>(moduleOptions);

		// Coordinates for the elements
		gridPane.add(menu, 0, 0);

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
		gridPane.add(moduleChoice, 1, 6);

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
			if (name.getText().isEmpty() || topic.getText().isEmpty() || topic.getText().isEmpty()
					|| level.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Fill in all the fields");
				errorAlert.showAndWait();
				error = true;
			}

			if (publicationDate.getValue() == null || moduleChoice.getValue() == null || status.getValue() == null) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setHeaderText("Input not valid");
				errorAlert.setContentText("Make a choice in every combobox!");
				errorAlert.showAndWait();
				error = true;
			}

			// Checks if there occured an error, if not create new course in database
			if (!error) {
				Date sqlDate = Date.valueOf(publicationDate.getValue());

				Course course = new Course(sqlDate, status.getValue(), name.getText(), topic.getText(), text.getText(),
						level.getValue());
				course.insert();

				Module module = null;
				for (Module m : modules) {
					if (moduleChoice.getValue().getId() == m.getId()) {
						module = m;
					}
				}

				course.addModule(module);

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
		Menu navbar = new Menu("NavBar");
		MenuItem home = new MenuItem("Home");
		home.setOnAction(e -> {
			gui.startScene();
		});

		navbar.getItems().add(home);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(navbar);

		VBox menu = new VBox(menuBar);

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

		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.getColumns().add(column3);
		tableView.getColumns().add(column4);
		tableView.getColumns().add(column5);
		tableView.getColumns().add(column6);

		ArrayList<Course> courses = db.getAllCourses();

		for (Course course : courses) {
			tableView.getItems().add(course);
		}

		Button delete = new Button("Delete");
		Button view = new Button("View");

		delete.setOnAction((event) -> {
			Course course = tableView.getSelectionModel().getSelectedItem();
			course.delete();
			sceneCourseRead();
			GUI.updateScene(this.scene);
		});

		view.setOnAction((event) -> {
			Course course = tableView.getSelectionModel().getSelectedItem();
			sceneCourseView(course);
			GUI.updateScene(this.scene);
		});

		VBox vBox = new VBox();
		HBox buttons = new HBox();

		buttons.setStyle("-fx-font-size: 1.5em; -fx-padding: 1.5em;");

		buttons.getChildren().addAll(delete, view);
		vBox.getChildren().add(menu);
		vBox.getChildren().add(tableView);
		vBox.getChildren().add(buttons);

		this.scene = new Scene(vBox, 700, 400);
	}

	// Method for altering a course
	public void sceneCourseUpdate() {
		Menu navbar = new Menu("NavBar");
		MenuItem home = new MenuItem("Home");
		home.setOnAction(e -> {
			gui.startScene();
		});

		navbar.getItems().add(home);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(navbar);

		VBox menu = new VBox(menuBar);

		GridPane gridPane = new GridPane();

		Button bSubmit = new Button("Update");

		ArrayList<Course> courses = db.getAllCourses();
		ArrayList<String> courseNames = new ArrayList<>();

		for (Course course : courses) {
			courseNames.add(course.getName());
		}

		ObservableList<String> courseNameOptions = FXCollections.observableArrayList(courseNames);
		ComboBox<String> courseName = new ComboBox<>(courseNameOptions);

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

		courseName.valueProperty().addListener((obs, oldItem, newItem) -> {
			lName.textProperty().unbind();
			if (newItem == null) {
				lName.setText("");
			} else {
				Course currentCourse = null;
				for (Course course : courses) {
					if (course.getName().equals(courseName.getValue())) {
						currentCourse = course;
					}
				}

				name.setText(courseName.getValue());
				topic.setText(currentCourse.getTopic());
				text.setText(currentCourse.getText());

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(currentCourse.getPublicationDate().toString(), formatter);
				publicationDate.setValue(date);

				status.setValue(currentCourse.getStatus());
				level.setValue(currentCourse.getLevel());
				for (Module m : modules) {
					module.setValue(m);
				}
			}
		});

		gridPane.add(menu, 0, 0);

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

			if (name.getText().isEmpty() || topic.getText().isEmpty() || topic.getText().isEmpty()
					|| level.getValue() == null) {
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
				Course course = null;
				for (Course c : courses) {
					if (c.getName().equals(courseName.getValue())) {
						course = c;
					}
				}

				Date sqlDate = Date.valueOf(publicationDate.getValue());
				course.update(sqlDate, status.getValue(), name.getText(), topic.getText(), text.getText(), level.getValue());

				sceneCourseUpdate();
				GUI.updateScene(this.scene);

				Alert errorAlert = new Alert(AlertType.CONFIRMATION);
				errorAlert.setHeaderText("Course successfully updated!");
				errorAlert.showAndWait();
			}
		});

		this.scene = new Scene(gridPane, 700, 700);
	}

	public void sceneCourseView(Course course) {

		Menu navbar = new Menu("NavBar");
		MenuItem home = new MenuItem("Home");
		home.setOnAction(e -> {
			gui.startScene();
		});

		navbar.getItems().add(home);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(navbar);

		VBox menu = new VBox(menuBar);

		GridPane gridPane = new GridPane();

		Label lName = new Label("Name: ");
		Label lTopic = new Label("Topic: ");
		Label lText = new Label("Text: ");
		Label lPublicationDate = new Label("Publication Date: ");
		Label lLevel = new Label("Level: ");
		Label lModule = new Label("Modules: ");
		Label lStatus = new Label("Status: ");
		Label lTimesFinished = new Label("Total Certifcates: ");
		Label lRecommendedCourses = new Label("Recommended Courses: ");

		Label name = new Label();
		Label topic = new Label();
		Label text = new Label();
		Label publicationDate = new Label();
		Label level = new Label();
		Label module = new Label();
		Label status = new Label();
		Label timesFinished = new Label();

		name.setText(course.getName());
		topic.setText(course.getTopic());
		text.setText(course.getText());
		publicationDate.setText(course.getPublicationDate().toString());
		level.setText(course.getLevel().toString());
		module.setText(course.getModules().toString());
		status.setText(course.getStatus().toString());
		timesFinished.setText(db.getCourseCertificates(course).toString());

		gridPane.add(menu, 0, 0);

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

		gridPane.add(lTimesFinished, 0, 8);
		gridPane.add(timesFinished, 1, 8);

		gridPane.add(lRecommendedCourses, 0, 9);
		int count = 9;
		ArrayList<String> recommendedCourses = db.getRecommendedCourses();
		for (String rc : recommendedCourses) {
			Label label = new Label(rc);
			gridPane.add(label, 1, count);
			count++;
		}

		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		this.scene = new Scene(gridPane, 700, 700);
	}
}