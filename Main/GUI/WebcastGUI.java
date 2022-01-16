package Main.GUI;

import Main.Database.Database;
import Main.User.Gender;
import Main.User.User;
import Main.ContentItem.Webcast;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class WebcastGUI {
    private Database db = new Database();
    public Scene scene;

    public WebcastGUI() {
        TableView<Webcast> tableView = new TableView<>();

        TableColumn<Webcast, String> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Webcast, String> column2 = new TableColumn<>("URL");
        column2.setCellValueFactory(new PropertyValueFactory<>("URL"));

        TableColumn<Webcast, Integer> column3 = new TableColumn<>("Duration");
        column3.setCellValueFactory(new PropertyValueFactory<>("Duration"));

        TableColumn<Webcast, Integer> column4 = new TableColumn<>("Publication date");
        column3.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));

        TableColumn<Webcast, Integer> column5 = new TableColumn<>("Status");
        column3.setCellValueFactory(new PropertyValueFactory<>("Status"));

        TableColumn<Webcast, Integer> column6 = new TableColumn<>("Speaker");
        column4.setCellValueFactory(new PropertyValueFactory<>("SpeakerID"));

        TableColumn<Webcast, Gender> column7 = new TableColumn<>("Description");
        column5.setCellValueFactory(new PropertyValueFactory<>("Description"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);
        tableView.getColumns().add(column7);
        
        ArrayList<Webcast> webcasts = db.getWebcasts(); 

        for (Webcast webcast : webcasts) {
            tableView.getItems().add(webcast);
        }

        Button view = new Button("View");

        view.setOnAction((event) -> {
            Webcast webcast = tableView.getSelectionModel().getSelectedItem();
            webcast.view();
            GUI.updateScene(this.scene);
        });

        VBox vbox = new VBox(tableView);
        vbox.getChildren().add(view);

        this.scene = new Scene(vbox, 1050, 400);
    }

    public Scene getScene() {
        return null;
    }
}
