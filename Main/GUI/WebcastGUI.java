package Main.GUI;

// This GUI adds the possibilty to view the available webcasts. 

import Main.Database.Database;
import Main.User.Gender;
import Main.User.User;
import Main.ContentItem.Webcast;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class WebcastGUI {
    private Database db = new Database();
    public Scene scene;
    private String selectedUser;
    private GUI gui = new GUI();

    public WebcastGUI() {
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

        ArrayList<User> users = db.getUsers();
        ArrayList<String> userEmails = new ArrayList<>();
        Button bUpdate = new Button("Proceed to overview");

        for (User user : users) {
            userEmails.add(user.getEmail());
        }

        ObservableList<String> userEmailOptions = FXCollections.observableArrayList(userEmails);

        ComboBox<String> userEmail = new ComboBox<>();
        userEmail.setItems(userEmailOptions);

        gridPane.add(menu, 0, 0);
        gridPane.add(userEmail, 0, 1);
        gridPane.add(bUpdate, 0, 2);

        gridPane.setStyle("-fx-font-size: 2em; -fx-padding: 2em;");

        bUpdate.setOnAction((event) -> {
            selectedUser = userEmail.getValue();
            webcastRead();
            GUI.updateScene(this.scene);
        });

        this.scene = new Scene(gridPane, 700, 700);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void webcastRead() {
        Menu navbar = new Menu("NavBar");
        MenuItem home = new MenuItem("Home");

        home.setOnAction(e -> {
            gui.startScene();
        });

        navbar.getItems().add(home);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(navbar);

        VBox menu = new VBox(menuBar);

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

            int contentItemID = db
                    .getWebcastContentID(column1.getCellData(tableView.getSelectionModel().getSelectedItem()));
            int userID = db.getCourseTakerID(selectedUser);

            db.viewsWebcast(userID, contentItemID);
        });

        VBox vbox = new VBox();
        vbox.getChildren().add(menu);
        vbox.getChildren().add(tableView);
        vbox.getChildren().add(view);

        vbox.setStyle("-fx-font-size: 1.5em; -fx-padding: 1.5em;");

        this.scene = new Scene(vbox, 800, 400);
    }
}
