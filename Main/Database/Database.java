package Main.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Main.ContentItem.Speaker;
import Main.ContentItem.Webcast;
import Main.ContentItem.Course.ContactPerson;
import Main.ContentItem.Course.Course;
import Main.ContentItem.Course.Module;
import Main.User.Registration;
import Main.User.User;

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
    public void createUser(User u) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            System.out.println(u.getIsCourseTaker());
            System.out.println(u.getIsStaff());

            Integer courseTakerId = null; 
            Integer staffId = null; 

            if (u.getIsCourseTaker() != null) {
                courseTakerId = Integer.valueOf(u.getIsCourseTaker());
            }

            if (u.getIsStaff() != null) {
                staffId = Integer.valueOf(u.getIsStaff()); 
            }

            if (staffId != null) {
                staffId = this.createStaff();
            } else {
                courseTakerId = this.createCourseTaker();
            }

            String SQL = "INSERT INTO [User] VALUES ('" + u.getEmail() + "','" + u.getName() + "','" + u.getBirthDate() + "','" + u.getGender() + "','" 
            + u.getAddress() + "','" + u.getResidence() + "','" + u.getCountry() + "'," + courseTakerId + "," + staffId + ")";
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

    public void deleteUser(User u) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM [User] WHERE (Email = '" + u.getEmail() + "')";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void updateUser(User u, String email, String name, Date birthDate, String gender, String address, String residence,
            String country, String isCourseTaker, String isStaff) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "UPDATE [User] SET Email = '" + email + "', Name = '" + name + "', DateOfBirth = '" + birthDate + "', Gender = '" + gender + "', Address = '"
                    + address + "', Residence = '" + residence + "', Country = '" + country + "', CourseTakerID = "
                    + isCourseTaker + ", StaffID = " + isStaff + "WHERE Email = '" + u.getEmail() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public Integer createStaff() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Staff DEFAULT VALUES";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = 0; 

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT TOP 1 * FROM Staff ORDER BY Id DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("Id"); 
            }
            return id; 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Integer createCourseTaker() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO CourseTaker DEFAULT VALUES";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = 0; 

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT TOP 1 * FROM CourseTaker ORDER BY Id DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("Id"); 
            }
            return id; 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; 
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

    public void createCourse(Date publicationDate, String status, Course c) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Course VALUES ('" + c.getName() + "','" + c.getTopic() + "','" + c.getText() + "','" + c.getLevel() + "'," + c.getPercentageViewed() + "," + null + ")";
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

    public void deleteCourse(Course c) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM Course WHERE Name = '" + c.getName() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void updateCourse(Course c, Date publicationDate, String status, String name, String topic, String text, String level, int percentageViewed) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            // Stel een SQL query samen.
            String SQL = "UPDATE Course SET Name = '" + name + "', Topic = '" + topic + "', Text = '" + text + "', Lvl = '" + level + "', PercentageViewed = " + percentageViewed + "WHERE Name = '" + c.getName() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createWebcast(Date publicationDate, String status, Webcast w) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Webcast VALUES ('" + w.getTitle() + "','" + w.getURL() + "','" + w.getDescription() + "','" + w.getDuration() + "','" 
            + w.getSpeaker().getName() + "','" + w.getSpeaker().getOrganisation() + "')";
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

    public void deleteWebcast(Webcast w) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM Webcast WHERE Title = '" + w.getTitle() + "' AND URL = '" + w.getURL() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void updateWebcast(Webcast w, Date publicationDate, String status, String title, String URL, int duration, String description, Speaker speaker) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "UPDATE Webcast SET Title = '" + title + "', URL = '" + URL + "', Description = '" + description + "', Duration = '" + duration + "', SpeakerName = '" + speaker.getName() + "', SpeakerOrganisation = '" + speaker.getOrganisation() + "'WHERE Title = '" + w.getTitle() + "' AND URL = '" + w.getURL() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createContactPerson(ContactPerson c) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO ContactPerson VALUES ('" + c.getEmail() + "','" + c.getName() + "')";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void updateContactPerson(ContactPerson c, String email, String name) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            // Stel een SQL query samen.
            String SQL = "UPDATE ContactPerson SET Email = '" + email + "', Name = '" + name + "'WHERE Email = '" + c.getEmail() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void deleteContactPerson(ContactPerson c) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM ContactPerson WHERE Email = '" + c.getEmail() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createModule(Module m) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Module VALUES ('" + m.getTitle() + "','" + m.getVersion() + "','" + m.getSerialNumber() + "','" + m.getDescription() + "'," + 1 + "," + 3 + ")";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void updateModule(Module m, String title, String version, int serialNumber, String description) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            // Stel een SQL query samen.
            String SQL = "UPDATE Module SET Title = '" + title + "', Version = '" + version + "', SerialNumber = '" + serialNumber + "', Description = '" + description + "'WHERE Title = '" + m.getTitle() + "' AND Version = '" + m.getVersion() + "' AND SerialNumber = '" + m.getSerialNumber() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void addModuleToContactPerson(ContactPerson c, Module m) {
        int contactPersonId = 0; 

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT Id FROM ContactPerson WHERE Email = '" + c.getEmail() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                contactPersonId = rs.getInt("Id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "UPDATE Module SET ContactPersonID = " + contactPersonId + "WHERE Title = '" + m.getTitle() + "' AND Version = '" + m.getVersion() + "' AND SerialNumber = '" + m.getSerialNumber() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void addModuleToCourse(Course c, Module m) {
        int courseId = 0; 

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT Id FROM Course WHERE Name = '" + c.getName() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                courseId = rs.getInt("Id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "UPDATE Module SET CourseID = " + courseId + "WHERE Title = '" + m.getTitle() + "' AND Version = '" + m.getVersion() + "' AND SerialNumber = '" + m.getSerialNumber() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createRegistration(Registration r) {
        int courseId = 0; 
        int courseTakerId = 0;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT Id FROM Course WHERE Name = '" + r.getCourse().getName() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                courseId = rs.getInt("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT CourseTakerID FROM User WHERE Name = '" + r.getCourseTaker().getName() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while(rs.next()) {
                courseId = rs.getInt("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Registration VALUES ('" + courseTakerId + "','" + courseId + "','" + r.getDate() + "')";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }
}