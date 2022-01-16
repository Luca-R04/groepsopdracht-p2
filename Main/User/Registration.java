package Main.User;

import java.sql.Date;

import Main.ContentItem.Course.Course;
import Main.Database.Database;

public class Registration {
	private int id; 
  private User courseTaker; 
  private Course course; 
  private Date date; 
	private Database db = new Database();

	public Registration(User courseTaker, Course course, Date date) {
		this.courseTaker = courseTaker;
		this.course = course;
		this.date = date;
	}

	public void insert() {
		db.createRegistration(this);
	}

	public void delete() {
		db.deleteRegistration(this);
	}

	public void update(User courseTaker, Course course) {
		db.updateRegistration(this, courseTaker, course); 

		this.courseTaker = courseTaker;
		this.course = course;
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
