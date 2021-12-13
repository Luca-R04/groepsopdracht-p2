package Main.User;
import java.sql.Date;
import java.sql.*;

import Main.Database.Database;

public class User {
    private String email; 
    private String name;
    private Date birthDate;
    private String gender; 
    private String address; 
    private String residence; 
    private String country; 
    private String isCourseTaker;
    private String isStaff;
    private Database db;

    public User(String email, String name, Date birthDate, String gender, String address, String residence, String country, String isStaff, String isCourseTaker) {
        // db.CreateUser(email, name, birthDate, gender, address, residence, country, isStaff, isCourseTaker);
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.residence = residence;
        this.country = country;
        this.isCourseTaker = isCourseTaker;
        this.isStaff = isStaff;
    }

    public void CreateUser() {
        String connectionUrl = "jdbc:sqlserver://aei-sql2.avans.nl\\studenten:1443;databaseName=CodeCademy12;user=adidas12;password=MondKap!;";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 'Importeer' de driver die je gedownload hebt.
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Maak de verbinding met de database.
            con = DriverManager.getConnection(connectionUrl);

            // SQL query die een waarde in de User tabel insert.
            String SQL = "INSERT INTO [User] VALUES ('" + email + "','" + name + "','" + birthDate + "','" + gender + "','"
                    + address + "','" + residence + "','" + country + "','" + isCourseTaker + "','" + isStaff + "') ";
            stmt = con.createStatement();
            // Voer de query uit op de database.
            rs = stmt.executeQuery(SQL);
        }

        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        } finally {
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
    }

}