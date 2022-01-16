package Main.GUI;

import java.sql.SQLException;
import java.util.ArrayList;

import Main.Database.Database;
import Main.User.Certificate;
import Main.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

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
        GridPane gridPane = new GridPane();

        // Labels;
        Label lUser = new Label("User: ");
        Label lCertificates = new Label("Certificate: ");
        Label lRating = new Label("Rating: ");
        Label lStaff = new Label("Staff: ");
        Label lCourse = new Label("Course: ");

        ArrayList<User> users = db.getUsers();
        ArrayList<String> userEmails = new ArrayList<>();

        for (User user : users) {
            userEmails.add(user.getEmail());
        }

        ObservableList<String> userEmailOptions = FXCollections.observableArrayList(userEmails);

        Button bUpdate = new Button("Update");

        // Text fields
        ComboBox<String> userEmail = new ComboBox<>();
        userEmail.setItems(userEmailOptions);
        ComboBox<String> userCertificates = new ComboBox<>();
        TextField rating = new TextField();
        TextField staff = new TextField();
        TextField course = new TextField();

        // When a user is selected from the comboBox the method will display it's
        // certificates
        userEmail.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem == null) {
            } else {
                userCertificates.setItems(db.getCertificates(userEmail.getValue()));
            }
        });

        userCertificates.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem == null) {
                rating.setText("");
                staff.setText("");
                course.setText("");

            } else {
                ArrayList<String> certificateDataList = db.getCertificateData(userCertificates.getValue());

                rating.setText(certificateDataList.get(1));
                staff.setText(db.getStaffMail(Integer.valueOf(certificateDataList.get(2))));
                course.setText(db.getCourse(Integer.valueOf(certificateDataList.get(3))));
            }
        });

        // Coordinates for the elements
        gridPane.add(lUser, 0, 1);
        gridPane.add(userEmail, 1, 1);

        gridPane.add(lCertificates, 0, 2);
        gridPane.add(userCertificates, 1, 2);

        gridPane.add(lRating, 0, 3);
        gridPane.add(rating, 1, 3);

        gridPane.add(lStaff, 0, 4);
        gridPane.add(staff, 1, 4);

        gridPane.add(lCourse, 0, 5);
        gridPane.add(course, 1, 5);

        gridPane.add(bUpdate, 1, 7);

        // Styling
        gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        bUpdate.setOnAction((action) -> {
            boolean error = false;

            // Checks if all the fields are filled in
            if (userEmail.getValue().isEmpty() || userCertificates.getValue().isEmpty() || rating.getText().isEmpty() || staff.getText().isEmpty()
                    || course.getText().isEmpty()) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setHeaderText("Input not valid");
                errorAlert.setContentText("Fill in all the fields");
                errorAlert.showAndWait();
                error = true;
            }

            // Checks if there occured an error, if not update the specified user
            if (!error) {
                String isCourseTaker = null;
                String isStaff = null;

                Certificate c = new Certificate(Integer.valueOf(userCertificates.getValue()), Integer.valueOf(rating.getText()), db.getStaffID(staff.getText()), db.getCourseID(course.getText()), db.getCourseTakerID(userEmail.getValue()));

                c.update();

                Alert errorAlert = new Alert(AlertType.CONFIRMATION);
                errorAlert.setHeaderText("Certificate successfully updated!");
                errorAlert.showAndWait();
            }
        });

        this.scene = new Scene(gridPane, 700, 700);
    }

    private void sceneCertificateRead() {
        GridPane gridPane = new GridPane();

        // Labels;
        Label lUser = new Label("User: ");
        Label lCertificates = new Label("Certificate: ");
        Label lRating = new Label("Rating: ");
        Label lStaff = new Label("Staff: ");
        Label lCourse = new Label("Course: ");

        ArrayList<User> users = db.getUsers();
        ArrayList<String> userEmails = new ArrayList<>();

        for (User user : users) {
            userEmails.add(user.getEmail());
        }

        ObservableList<String> userEmailOptions = FXCollections.observableArrayList(userEmails);

        // Text fields
        ComboBox<String> userEmail = new ComboBox<>();
        userEmail.setItems(userEmailOptions);
        ComboBox<String> userCertificates = new ComboBox<>();
        Label rating = new Label();
        Label staff = new Label();
        Label course = new Label();

        // When a user is selected from the comboBox the method will display it's
        // certificates
        userEmail.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem == null) {
            } else {
                userCertificates.setItems(db.getCertificates(userEmail.getValue()));
            }
        });

        userCertificates.valueProperty().addListener((obs, oldItem, newItem) -> {
            if (newItem == null) {
                rating.setText("");
                staff.setText("");
                course.setText("");

            } else {
                ArrayList<String> certificateDataList = db.getCertificateData(userCertificates.getValue());

                rating.setText(certificateDataList.get(1));
                staff.setText(db.getStaffMail(Integer.valueOf(certificateDataList.get(2))));
                course.setText(db.getCourse(Integer.valueOf(certificateDataList.get(3))));
            }
        });

        // Coordinates for the elements
        gridPane.add(lUser, 0, 1);
        gridPane.add(userEmail, 1, 1);

        gridPane.add(lCertificates, 0, 2);
        gridPane.add(userCertificates, 1, 2);

        gridPane.add(lRating, 0, 4);
        gridPane.add(rating, 1, 4);

        gridPane.add(lStaff, 0, 5);
        gridPane.add(staff, 1, 5);

        gridPane.add(lCourse, 0, 6);
        gridPane.add(course, 1, 6);

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
