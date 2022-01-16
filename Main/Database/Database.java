package Main.Database;

import Main.ContentItem.Course.ContactPerson;
import Main.ContentItem.Course.Course;
import Main.ContentItem.Course.Level;
import Main.ContentItem.Course.Module;
import Main.ContentItem.Course.Status;
import Main.User.Gender;
import Main.User.Registration;
import Main.User.User;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        }
    }

    public void excecuteFinally() {
        if (rs != null)
            try {
                rs.close();
            } catch (Exception e) {
            }
        if (stmt != null)
            try {
                stmt.close();
            } catch (Exception e) {
            }
        if (con != null)
            try {
                con.close();
            } catch (Exception e) {
            }
    }

    // Creates a user inside the database
    public void createUser(User u) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

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

            String SQL = "INSERT INTO [User] VALUES ('" + u.getEmail() + "','" + u.getFirstName() + "','"
                    + u.getLastName() + "','" + u.getBirthDate() + "','" + u.getGender() + "','"
                    + u.getAddress() + "','" + u.getPostalCode() + "','" + u.getResidence() + "','" + u.getCountry()
                    + "'," + courseTakerId + "," + staffId + ")";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    // Retrieve all users and put them in an ArrayList
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String email = rs.getString("Email");
                String FirstName = rs.getString("FirstName");
                String LastName = rs.getString("LastName");
                Date birthDate = rs.getDate("DateOfBirth");
                Gender gender = Gender.valueOf(rs.getString("Gender"));
                String address = rs.getString("Address");
                String postalCode = rs.getString("PostalCode");
                String residence = rs.getString("Residence");
                String country = rs.getString("Country");
                String isCourseTaker = rs.getString("CourseTakerID");
                String isStaff = rs.getString("StaffID");

                User user = new User(email, FirstName, LastName, birthDate, gender, address, postalCode, residence,
                        country, isCourseTaker, isStaff);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public void deleteUser(String email) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM [User] WHERE (Email = '" + email + "')";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void updateUser(User user, String email, String FirstName, String LastName, Date birthDate, Gender gender,
            String address,
            String postalCode, String residence, String country) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "UPDATE [User] SET FirstName = '" + FirstName + "', LastName = '" + LastName
                    + "', DateOfBirth = '" + birthDate
                    + "', Gender = '" + gender + "', Address = '" + address + "', PostalCode = '" + postalCode
                    + "', Residence = '" + residence + "', Country = '" + country + "' WHERE Email = '"
                    + user.getEmail() + "'";
            stmt = con.createStatement();
            stmt.execute(SQL);
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
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer id = null;

        try {
            String SQL = "SELECT TOP 1 * FROM Staff ORDER BY StaffID DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("StaffID");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public Integer createCourseTaker() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO CourseTaker DEFAULT VALUES";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = 0;

        try {

            String SQL = "SELECT TOP 1 * FROM CourseTaker ORDER BY CourseTakerID DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("CourseTakerID");
            }
            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void createContentItem(Date publicationDate, Status status, Integer webcastID, Integer courseID) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO ContentItem VALUES ('" + publicationDate + "','" + status + "'," + webcastID + "," + courseID + ")";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createCourse(Date publicationDate, Status status, Course c) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Course VALUES ('" + c.getName() + "','" + c.getTopic() + "','" + c.getText()
                    + "','" + c.getLevel() + "'," + null + ")";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = 0;

        try {
            String SQL = "SELECT TOP 1 * FROM Course ORDER BY CourseID DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("CourseID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.createContentItem(publicationDate, status, null, id);
    }

    public void deleteCourse(String name) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM Course WHERE Name = '" + name + "'";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM Course JOIN ContentItem ON ContentItem.CourseID = Course.CourseID";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Date publicationDate = rs.getDate("PublicationDate");
                Status status = Status.valueOf(rs.getString("Status"));
                String name = rs.getString("Name");
                String topic = rs.getString("Topic");
                String text = rs.getString("Text");
                Level level = Level.valueOf(rs.getString("Lvl"));

                Course course = new Course(publicationDate, status, name, topic, text, level);
                courses.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return courses;
    }

    public void updateCourse(Course c, Date publicationDate, Status status, String name, String topic, String text,
            Level level) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "UPDATE Course SET Name = '" + name + "', Topic = '" + topic + "', Text = '" + text
                    + "', Lvl = '" + level + "'WHERE Name = '"
                    + c.getName() + "'";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public ArrayList<Module> getAllModules() {
        ArrayList<Module> modules = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM Module JOIN ContactPerson ON Module.ContactPersonID = ContactPerson.ContactPersonID JOIN Course ON Module.CourseID = Course.CourseID JOIN ContentItem ON Course.CourseID = ContentItem.CourseID";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String moduleTitle = rs.getString("Title");
                int moduleVersion = rs.getInt("Version");
                int moduleSerialNumber = rs.getInt("SerialNumber");
                String moduleDescription = rs.getString("Description");

                String contactPersonName = rs.getString("Name");
                String contactPersonEmail = rs.getString("Email");
                ContactPerson contactPerson = new ContactPerson(contactPersonName, contactPersonEmail);

                Date coursePublicationDate = rs.getDate("PublicationDate");
                Status courseStatus = Status.valueOf(rs.getString("Status"));
                String courseName = rs.getString("Name");
                String courseTopic = rs.getString("Topic");
                String courseText = rs.getString("Text");
                Level courseLevel = Level.valueOf(rs.getString("Lvl"));
                Course course = new Course(coursePublicationDate, courseStatus, courseName, courseTopic, courseText,
                        courseLevel);

                Module module = new Module(moduleTitle, moduleVersion, moduleSerialNumber, moduleDescription,
                        contactPerson, course);
                modules.add(module);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modules;
    }

    public void addModuleToCourse(Course c, Module m) {
        int courseId = 0;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT CourseID FROM Course WHERE Name = '" + c.getName() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                courseId = rs.getInt("CourseID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "UPDATE Module SET CourseID = " + courseId + "WHERE Title = '" + m.getTitle()
                    + "' AND Version = '" + m.getVersion() + "' AND SerialNumber = '" + m.getSerialNumber() + "'";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createRegistration(Registration registration) {
        int courseId = 0;
        int courseTakerId = 0;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM Course WHERE Name = '" + registration.getCourse().getName() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                courseId = rs.getInt("CourseID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT CourseTakerID FROM [User] WHERE FirstName = '" + registration.getCourseTaker().getFirstName() + "' AND LastName = '" + registration.getCourseTaker().getLastName() +"'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                courseTakerId = rs.getInt("CourseTakerID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "INSERT INTO Registration VALUES (" + courseTakerId + "," + courseId + ",'" + registration.getDate() + "')";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Registration> getRegistrations() {
        ArrayList<Registration> registrations = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM Registration JOIN [User] ON [User].CourseTakerID = Registration.CourseTakerID JOIN Course ON Course.CourseID = Registration.CourseID";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String userEmail = rs.getString("Email");
                ArrayList<User> users = this.getUsers();
                User user = null;
                for(User u : users) {
                    if (u.getEmail().equals(userEmail)) {
                        user = u;
                    }
                }

                String courseName = rs.getString("Name");
                ArrayList<Course> courses = this.getAllCourses();
                Course course = null;
                for(Course c : courses) {
                    if (c.getName().equals(courseName)) {
                        course = c;
                    }
                }

                int id = rs.getInt("RegistrationID"); 
                Date date = rs.getDate("RegistrationDate");

                Registration registration = new Registration(user, course, date);
                registration.setId(id);
                registrations.add(registration);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return registrations;
    }

    public void deleteRegistration(Registration registration) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "DELETE FROM Registration WHERE RegistrationID = " + registration.getId() + "'";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public ObservableList<String> getCertificates(String userEmail) {
        ObservableList<String> certificateData = FXCollections.observableArrayList();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT CertificateID FROM Certificate WHERE CoursetakerID IN( SELECT CourseTakerID FROM [User] WHERE Email = '"
                    + userEmail + "')";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {

                for (int i = 1; i < columnCount + 1; i++) {
                    certificateData.add(rs.getString(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return certificateData;
    }

    public ArrayList<String> getCertificateData(String certificateID) {
        ArrayList<String> certificateData = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "SELECT * FROM Certificate WHERE CertificateID = " + Integer.valueOf(certificateID) + "";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {

                for (int i = 1; i < columnCount + 1; i++) {
                    certificateData.add(rs.getString(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return certificateData;
    }

    public void updateCertificate(Integer certificateID, Integer rating, Integer staffID, Integer courseID,
            Integer CourseTakerID) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            String SQL = "UPDATE Certificate SET Rating = " + rating + ", StaffID = " + staffID + ", CourseID = "
                    + courseID + ", CourseTakerID = " + CourseTakerID + " WHERE CertificateID = " + certificateID + "";
            stmt = con.createStatement();
            stmt.execute(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }
}