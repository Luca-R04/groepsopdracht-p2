package Main.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Main.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
    private String connectionUrl = "jdbc:sqlserver://aei-sql2.avans.nl\\studenten:1443;databaseName=CodeCademy12;user=adidas12;password=MondKap!;";
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private ObservableList<User> userList = FXCollections.observableArrayList(); 


    public Database() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            excecuteFinally();
        }
    }

    public void excecuteFinally() {
        if (rs != null) try { rs.close(); } catch (Exception e) {}
        if (stmt != null) try { stmt.close(); } catch (Exception e) {}
        if (con != null) try { con.close(); } catch (Exception e) {}
    }

    public void createUser(String email, String name, Date birthDate, String gender, String address, String residence,
            String country, String isCourseTaker, String isStaff) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO [User] VALUES ('" + email + "','" + name + "','" + birthDate + "','" + gender + "','" 
            + address + "','" + residence + "','" + country + "'," + isCourseTaker + "," + isStaff + ")";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public Map<String, ArrayList<String>> getAllUsers() {
        Map<String, ArrayList<String>> users = new HashMap<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                ArrayList<String> userData = new ArrayList<>();
                for (int i = 2; i < 9; i++) {
                    userData.add(rs.getString(i));
                    userList.add(new User(rs.getString("Email"), 
                    rs.getString("Name"), 
                    rs.getDate("DateOfBirth"), 
                    rs.getString("Gender"),
                    rs.getString("Address"),
                    rs.getString("Residence"),
                    rs.getString("Country"),
                    rs.getString("isCourseTaker"),
                    rs.getString("isStaff")
                    ));
                }

                users.put(rs.getString(1), userData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return users;
    }

    public ObservableList<User> getUsersGUI() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                    userList.add(new User(rs.getString("Email"), 
                    rs.getString("Name"), 
                    rs.getDate("DateOfBirth"), 
                    rs.getString("Gender"),
                    rs.getString("Address"),
                    rs.getString("Residence"),
                    rs.getString("Country"),
                    rs.getString("CourseTakerID"),
                    rs.getString("StaffID")
                    ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return userList;
    }

    public void deleteUser(String email) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Maak de verbinding met de database.
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM [User] WHERE (Email = '" + email + "')";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void updateUser(String email, String name, Date birthDate, String gender, String address, String residence,
            String country, String isCourseTaker, String isStaff) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            // Stel een SQL query samen.
            String SQL = "UPDATE [User] SET Email = '" + email + "', Name = '" + name + "', DateOfBirth = '" + birthDate + "', Gender = '" + gender + "', Address = '"
                    + address + "', Residence = '" + residence + "', Country = '" + country + "', CourseTakerID = "
                    + isCourseTaker + ", StaffID = " + isStaff + "WHERE Email = '" + email + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }
}