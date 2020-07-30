package net.guides.springboot2.springboot2jpacrudexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Education {

	private long id;
	private String education;
	private String teacher;
	private String link_lesson;
	
	public Education() {
		
	}
	
	public Education(String firstName, String lastName, String emailId) {
		this.education = firstName;
		this.teacher = lastName;
		this.link_lesson = emailId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return education;
	}
	public void setFirstName(String firstName) {
		this.education = firstName;
	}
	
	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return teacher;
	}
	public void setLastName(String lastName) {
		this.teacher = lastName;
	}
	
	@Column(name = "email_address", nullable = false)
	public String getEmailId() {
		return link_lesson;
	}
	public void setEmailId(String emailId) {
		this.link_lesson = emailId;
	}

	@Override
	public String toString() {
		return "Education [id=" + id + ", education=" + education + ", teacher=" + teacher + ", link_lesson=" + link_lesson
				+ "]";
	}
	
}
