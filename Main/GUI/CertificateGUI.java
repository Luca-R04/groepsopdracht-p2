package Main.GUI;

import java.util.ArrayList;
import java.util.Map;

import Main.Database.Database;

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

public class CertificateGUI {
    private Database db = new Database();
    public Scene scene;

    public CertificateGUI() {
        // Creating all the buttons
        Button bRead = new Button("View certifcates");
        Button bUpdate = new Button("Update certificates");

        // New Gridpane
        GridPane gridPane = new GridPane();

        // Specifies coordinates for the buttons
        gridPane.add(bRead, 1, 1);
        gridPane.add(bUpdate, 2, 1);

        // Styling
        gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Button functions
        bRead.setOnAction((action) -> {
            sceneCertificateRead();
            GUI.updateScene(this.scene);
        });

        bUpdate.setOnAction((action) -> {
            sceneCertificateUpdate();
            GUI.updateScene(this.scene);
        });

        this.scene = new Scene(gridPane, 600, 500);
    }

    private void sceneCertificateUpdate() {

    }

    private void sceneCertificateRead() {
        GridPane gridPane = new GridPane();

		// Labels;
		Label lUser = new Label("User: ");
        Label lCertificates = new Label("Certificate: ");
		Label lCertificateID = new Label("ID: ");
        Label lRating = new Label("Rating: ");
		Label lStaff = new Label("StaffName: ");
		Label lCourse = new Label("Course: ");

		// Text fields
		ComboBox<String> userEmails = new ComboBox<>();
		userEmails.setItems(db.getUserEmails());
        ComboBox<String> userCertificates = new ComboBox<>();
		userEmails.setItems(db.getUserEmails());
		Label certificateID = new Label();
		Label rating = new Label();
        Label staff = new Label();
		Label course = new Label();

		//When a user is selected from the comboBox the method will display it's data
		userEmails.valueProperty().addListener((obs, oldItem, newItem) -> {
			if (newItem == null) {
				certificateID.setText("");
			} else {
				Map<String, ArrayList<String>> certificates = db.getCertificates();
				ArrayList<String> data = certificates.get(userEmails.getValue());

				//Puts the data into the correct textField
				for (int i = 0; i < data.size(); i++) {
					String labelValue = data.get(i);

					if (i == 0) {
						certificateID.setText(labelValue);
					} else if (i == 1) {
						rating.setText(labelValue);
					} else if (i == 2) {
						rating.setText(labelValue);
					} else if (i == 3) {
						course.setText(labelValue);
					}
				}
			}
		});

		// Coordinates for the elements
		gridPane.add(lUser, 0, 1);
		gridPane.add(userEmails, 1, 1);

		gridPane.add(lCertificates, 0, 2);
		gridPane.add(userCertificates, 1, 2);

		gridPane.add(lCertificateID, 0, 3);
		gridPane.add(certificateID, 1, 3);

		gridPane.add(lRating, 0, 4);
		gridPane.add(rating, 1, 4);

        gridPane.add(lCourse, 0, 5);
		gridPane.add(course, 1, 5);

		// Styling
		gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
		gridPane.setVgap(10);
		gridPane.setHgap(10);

		this.scene = new Scene(gridPane, 700, 700);
    }

    public Scene getScene() {
        return this.scene;
    }

}
