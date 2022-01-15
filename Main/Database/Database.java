package Main.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Main.ContentItem.Speaker;
import Main.ContentItem.Webcast;
import Main.ContentItem.Course.ContactPerson;
import Main.ContentItem.Course.Course;
import Main.ContentItem.Course.Level;
import Main.ContentItem.Course.Module;
import Main.ContentItem.Course.Status;
import Main.User.Gender;
import Main.User.Registration;
import Main.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
    private String connectionUrl = "jdbc:sqlserver://aei-sql2.avans.nl\\studenten:1443;databaseName=CodeCademy12;user=adidas12;password=MondKap!;";
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public Database() {
        try {

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            excecuteFinally();
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

            String SQL = "INSERT INTO [User] VALUES ('" + u.getEmail() + "','" + u.getName() + "','" + u.getBirthDate()
                    + "','" + u.getGender() + "','"
                    + u.getAddress() + "','" + u.getPostal() + "','" + u.getResidence() + "','" + u.getCountry() + "',"
                    + courseTakerId + ","
                    + staffId + ")";
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

    public ObservableList<String> getUserEmails() {
        ObservableList<String> userEmails = FXCollections.observableArrayList();

        try {

            String SQL = "SELECT Email FROM [User]";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                userEmails.add(rs.getString("Email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return userEmails;
    }

    public User getSpecificUser(String email) {
        User user = null;

        try {
            String SQL = "SELECT * FROM [User] WHERE email = '" + email + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String name = rs.getString("Name");
                Date birthDate = rs.getDate("DateOfBirth");
                Gender gender = Gender.valueOf(rs.getString("Gender"));
                String address = rs.getString("Address");
                String postalCode = rs.getString("PostalCode");
                String residence = rs.getString("Residence");
                String country = rs.getString("Country");
                String isCourseTaker = rs.getString("CourseTakerID");
                String isStaff = rs.getString("StaffID");

                user = new User(email, name, birthDate, gender, address, postalCode, residence, country, isCourseTaker,
                        isStaff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return user;
    }

    public void deleteUser(String email) {
        try {
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

    public void updateUser(User user, String email, String name, Date birthDate, Gender gender, String address,
            String postalCode, String residence, String country) {
        try {
            String SQL = "UPDATE [User] SET Name = '" + name + "', DateOfBirth = '" + birthDate
                    + "', Gender = '" + gender + "', Address = '" + address + "', PostalCode = '" + postalCode
                    + "', Residence = '" + residence + "', Country = '" + country + "' WHERE Email = '"
                    + user.getEmail() + "'";
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
            String SQL = "INSERT INTO Staff DEFAULT VALUES";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int id = 0;

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

        return null;
    }

    public Integer createCourseTaker() {
        try {

            String SQL = "INSERT INTO CourseTaker DEFAULT VALUES";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
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
            String SQL = "INSERT INTO ContentItem VALUES ('" + publicationDate + "','" + status + "'," + webcastID + ","
                    + courseID + ")";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createCourse(Date publicationDate, Status status, Course c) {
        try {
            String SQL = "INSERT INTO Course VALUES ('" + c.getName() + "','" + c.getTopic() + "','" + c.getText()
                    + "','" + c.getLevel() + "'," + null + ")";
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
            String SQL = "SELECT TOP 1 * FROM Course ORDER BY CourseID DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("CourseID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        this.createContentItem(publicationDate, status, null, id);
    }

    public void deleteCourse(String name) {
        try {
            String SQL = "DELETE FROM Course WHERE Name = '" + name + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public Map<String, ArrayList<String>> getAllCourses() {
        Map<String, ArrayList<String>> courses = new HashMap<>();

        try {
            String SQL = "SELECT * FROM Course";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                ArrayList<String> courseData = new ArrayList<>();
                for (int i = 3; i < columnCount + 1; i++) {
                    courseData.add(rs.getString(i));
                }

                courses.put(rs.getString(2), courseData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return courses;
    }

    public Course getSpecificCourse(String name) {
        Course course = null;
        Integer courseId = null;

        String topic = null;
        String text = null;
        Level level = null;

        try {
            String SQL = "SELECT * FROM Course WHERE Name = '" + name + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                courseId = rs.getInt("CourseID");
                topic = rs.getString("Topic");
                text = rs.getString("Text");
                level = Level.valueOf(rs.getString("Lvl"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        try {
            String SQL = "SELECT * FROM ContentItem WHERE CourseID = '" + courseId + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                Date publicationDate = rs.getDate("PublicationDate");
                Status status = Status.valueOf(rs.getString("Status"));

                course = new Course(publicationDate, status, name, topic, text, level);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        return course;
    }

    public void updateCourse(Course c, Date publicationDate, Status status, String name, String topic, String text,
            Level level) {
        try {
            String SQL = "UPDATE Course SET Name = '" + name + "', Topic = '" + topic + "', Text = '" + text
                    + "', Lvl = '" + level + "'WHERE Name = '"
                    + c.getName() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createWebcast(Date publicationDate, Status status, Webcast w) {
        try {
            String SQL = "INSERT INTO Webcast VALUES ('" + w.getTitle() + "','" + w.getURL() + "','"
                    + w.getDescription() + "','" + w.getDuration() + "','"
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
            String SQL = "SELECT TOP 1 * FROM Webcast ORDER BY WebcastID DESC";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                id = rs.getInt("WebcastID");
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

    public void updateWebcast(Webcast w, Date publicationDate, Status status, String title, String URL, int duration,
            String description, Speaker speaker) {
        try {
            String SQL = "UPDATE Webcast SET Title = '" + title + "', URL = '" + URL + "', Description = '"
                    + description + "', Duration = '" + duration + "', SpeakerName = '" + speaker.getName()
                    + "', SpeakerOrganisation = '" + speaker.getOrganisation() + "'WHERE Title = '" + w.getTitle()
                    + "' AND URL = '" + w.getURL() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public void createContactPerson(ContactPerson contactPerson) {
        try {
            String SQL = "INSERT INTO ContactPerson VALUES ('" + contactPerson.getEmail() + "','"
                    + contactPerson.getName() + "')";
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
            String SQL = "UPDATE ContactPerson SET Email = '" + email + "', Name = '" + name + "'WHERE Email = '"
                    + c.getEmail() + "'";
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

    public void createModule(Module module) {
        int courseId = 0;

        try {
            String SQL = "SELECT CourseID FROM Course WHERE Name = '" + module.getCourse().getName() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                courseId = rs.getInt("CourseID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        int contactPersonId = 0;

        try {
            String SQL = "SELECT ContactPersonID FROM ContactPerson WHERE Email = '"
                    + module.getContactPerson().getEmail() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                contactPersonId = rs.getInt("ContactPersonID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String SQL = "INSERT INTO Module VALUES ('" + module.getTitle() + "','" + module.getVersion() + "','"
                    + module.getSerialNumber() + "','" + module.getDescription() + "'," + contactPersonId + ","
                    + courseId + ")";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }
    }

    public ArrayList<Module> getAllModules() {
        ArrayList<Module> modules = new ArrayList<>();

        try {
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

    public void updateModule(Module m, String title, int version, int serialNumber, String description) {
        try {
            String SQL = "UPDATE Module SET Title = '" + title + "', Version = " + version + ", SerialNumber = '"
                    + serialNumber + "', Description = '" + description + "'WHERE Title = '" + m.getTitle()
                    + "' AND Version = '" + m.getVersion() + "' AND SerialNumber = '" + m.getSerialNumber() + "'";
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
            String SQL = "SELECT ContactPersonID FROM ContactPerson WHERE Email = '" + c.getEmail() + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                contactPersonId = rs.getInt("ContactPersonID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        try {
            String SQL = "UPDATE Module SET ContactPersonID = " + contactPersonId + "WHERE Title = '" + m.getTitle()
                    + "' AND Version = '" + m.getVersion() + "' AND SerialNumber = '" + m.getSerialNumber() + "'";
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
            String SQL = "UPDATE Module SET CourseID = " + courseId + "WHERE Title = '" + m.getTitle()
                    + "' AND Version = '" + m.getVersion() + "' AND SerialNumber = '" + m.getSerialNumber() + "'";
            stmt = con.createStatement();
            boolean result = stmt.execute(SQL);
            System.out.println(result);
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
            String SQL = "SELECT CourseID FROM Course WHERE Name = '" + registration.getCourse().getName() + "'";
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
            String SQL = "SELECT CourseTakerID FROM [User] WHERE Name = '" + registration.getCourseTaker().getName()
                    + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                courseTakerId = rs.getInt("CourseTakerID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            excecuteFinally();
        }

        try {
            String SQL = "INSERT INTO Registration VALUES (" + courseTakerId + "," + courseId + ",'"
                    + registration.getDate() + "')";
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