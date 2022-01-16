package Main.GUI;

import Main.Database.Database;
import Main.User.Gender;
import Main.ContentItem.Webcast;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class WebcastGUI {
    private Database db = new Database();
    public Scene scene;

    public WebcastGUI() {
        TableView<Webcast> tableView = new TableView<>();

        TableColumn<Webcast, String> column1 = new TableColumn<>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<>("Title"));

        TableColumn<Webcast, String> column2 = new TableColumn<>("URL");
        column2.setCellValueFactory(new PropertyValueFactory<>("URL"));

        TableColumn<Webcast, Integer> column3 = new TableColumn<>("Duration");
        column3.setCellValueFactory(new PropertyValueFactory<>("Duration"));

        TableColumn<Webcast, Integer> column4 = new TableColumn<>("Publication date");
        column4.setCellValueFactory(new PropertyValueFactory<>("PublicationDate"));

        TableColumn<Webcast, Integer> column5 = new TableColumn<>("Status");
        column5.setCellValueFactory(new PropertyValueFactory<>("Status"));

        TableColumn<Webcast, Integer> column6 = new TableColumn<>("Speaker");
        column6.setCellValueFactory(new PropertyValueFactory<>("Speaker"));

        TableColumn<Webcast, Gender> column7 = new TableColumn<>("Description");
        column7.setCellValueFactory(new PropertyValueFactory<>("Description"));

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

            Runtime rt = Runtime.getRuntime();
            String url = column2.getCellData(tableView.getSelectionModel().getSelectedItem());

            if (url.contains("http") || url.contains("www.")) {

            } else {
                url = "http://" + column2.getCellData(tableView.getSelectionModel().getSelectedItem());
            }

            try {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        VBox vbox = new VBox(tableView);
        vbox.getChildren().add(view);

        this.scene = new Scene(vbox, 1050, 400);
    }

    public Scene getScene() {
        return this.scene;
    }
}
