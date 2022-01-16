package Main.User;

import Main.Database.Database;

public class Certificate {

    private int certificateID;
    private int rating;
    private int staffID;
    private int courseID;
    private int CourseTakerID;
    private Database db = new Database();

    public Certificate(Integer certificateID, Integer rating, Integer staffID, Integer courseID,
            Integer CourseTakerID) {
        this.certificateID = certificateID;
        this.rating = rating;
        this.staffID = staffID;
        this.courseID = courseID;
        this.CourseTakerID = CourseTakerID;
    }

    public void update() {
        db.updateCertificate(certificateID, rating, staffID, courseID, CourseTakerID);
    }

    public int getCertificateID() {
        return certificateID;
    }

    public int getRating() {
        return rating;
    }

    public int getStaffID() {
        return staffID;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getCourseTakerID() {
        return CourseTakerID;
    }

}
