package Main.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Main.ContentItem.Speaker;

public class Database {
    private String connectionUrl = "jdbc:sqlserver://aei-sql2.avans.nl\\studenten:1443;databaseName=CodeCademy12;user=adidas12;password=MondKap!;";
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;


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

    // Creates a user inside the database 
    public void createUser(String email, String name, Date birthDate, String gender, String address, String residence,
            String country, String isCourseTaker, String isStaff) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO [User] VALUES ('" + email + "','" + name + "','" + birthDate + "','" + gender + "','" 
            + address + "','" + residence + "','" + country + "'," + isCourseTaker + "," + isStaff + ")";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    // Retrieve all users and put them in a HashMap with the Email as key  
    public Map<String, ArrayList<String>> getAllUsers() {
        Map<String, ArrayList<String>> users = new HashMap<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                ArrayList<String> userData = new ArrayList<>();
                for (int i = 2; i < columnCount + 1; i++) {
                    userData.add(rs.getString(i));
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

    public void deleteUser(String email) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM [User] WHERE (Email = '" + email + "')";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
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
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createContentItem(Date publicationDate, String status, Integer webcastID, Integer courseID) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO ContentItem VALUES ('" + publicationDate + "','" + status + "'," + webcastID  + "," + courseID + ")";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createWebcast(Date publicationDate, String status, String title, String URL, int duration, String staffName, String description, Speaker speaker) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Webcast VALUES ('" + title + "','" + URL + "','" + description + "','" + duration + "','" 
            + speaker.getName() + "','" + speaker.getOrganization() + "')";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        int id = 0; 

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT TOP 1 * FROM Webcast ORDER BY Id DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("Id"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        this.createContentItem(publicationDate, status, id, null);
    }

    public void createCourse(Date publicationDate, String status, String name, String topic, String text, String level, int percentageViewed) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Course VALUES ('" + name + "','" + topic + "','" + text + "','" + level + "'," + percentageViewed + "," + null + ")";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        int id = 0; 

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT TOP 1 * FROM Course ORDER BY Id DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("Id"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        this.createContentItem(publicationDate, status, null, id);
    }
}