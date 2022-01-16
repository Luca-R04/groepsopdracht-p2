package Main.User;

// This class represents a Registration. It is possible to create one and retrieve it's attributes. 
// There is also a possibilty to insert a Registration object into the database. Updating and deleting it is also possible. 

import java.sql.Date;

import Main.ContentItem.Course.Course;
import Main.Database.Database;

public class Registration {
	private int id; 
  private User courseTaker; 
  private Course course; 
  private Date registrationDate; 
	private Database db = new Database();

	public Registration(User courseTaker, Course course, Date registrationDate) {
		this.courseTaker = courseTaker;
		this.course = course;
		this.registrationDate = registrationDate;
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

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
