package Main.GUI;

import Main.Database.Database;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {
	public static Stage stage;
	public Scene scene;
	public Database db = new Database();
	public static UserGUI user = new UserGUI();
	public static CourseGUI course = new CourseGUI();
	public static CertificateGUI certificate = new CertificateGUI();
	public static RegistrationGUI registration = new RegistrationGUI();

	@Override
	public void start(Stage stage) {
		GUI.stage = stage;

		// Changes the title of the window.
		GUI.stage.setTitle("Codecademy: Luca Rinck - 2186751, Martijn Schemers - 2184875, Max Stokla - 2186459, Laurens Weterings - 2189413");

		GUI.stage.setScene(scene);

		// Changes the scene using the startScene method
		startScene();

		// Displays the stage element
		GUI.stage.show();
	}

	// Method sets up the start screen with buttons to navigate
	public void startScene() {
		// Creates all the buttons
		Button bCourse = new Button("Courses");
		Button bRegistrations = new Button("Registrations");
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
		gridPane.add(bRegistrations, 1, 1);

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
		bRegistrations.setMaxWidth(300);
		bTop3course.setMaxWidth(300);
		bWebcast.setMaxWidth(300);
		bEnroledWebcast.setMaxWidth(300);
		bTop3webcast.setMaxWidth(300);
		bCertificates.setMaxWidth(300);
		bUser.setMaxWidth(300);

		this.scene = new Scene(gridPane, 600, 500);
		GUI.stage.setScene(this.scene);
		GUI.stage.show();

		// Button functions
		// bUser
		bUser.setOnAction((event) -> {
			GUI.stage.setScene(user.getScene());
		});

		bCourse.setOnAction((event) -> {
			GUI.stage.setScene(course.getScene());
		});

		bRegistrations.setOnAction((event) -> {
			GUI.stage.setScene(registration.getScene());
		});

		bCertificates.setOnAction((event) -> {
			GUI.stage.setScene(certificate.getScene());
		});
	}

	public static void updateScene(Scene userScene) {
		stage.setScene(userScene);
	}

}