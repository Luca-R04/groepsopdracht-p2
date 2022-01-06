package Main.User;

import java.sql.Date;

import Main.ContentItem.Course.Course;
import Main.Database.Database;

public class Registration {
    private User courseTaker; 
    private Course course; 
    private Date date; 
	private Database db = new Database();

	public Registration(User courseTaker, Course course, Date date) {
		this.courseTaker = courseTaker;
		this.course = course;
		this.date = date;

		db.createRegistration(this);
	}

	public User getCourseTaker() {
		return this.courseTaker;
	}

	public Course getCourse() {
		return this.course;
	}

	public Date getDate() {
		return this.date;
	}
}
