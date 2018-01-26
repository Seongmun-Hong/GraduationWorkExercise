package kr.ac.hansung.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class Course {
	private int year;
	private int semester;
	
	@NotEmpty(message="The course ID cannot be empty")
	@Size(min=7, max=7, message="Course ID must be 7 chars")
	private String course_id;
	
	@NotEmpty(message="The title cannot be empty")
	@Size(min=3, max=20, message="Title must be between 3 and 20 chars")
	private String title;
	
	private String division;
	
	private int credit;
	
	public Course() {
		
	}
	
	public Course(String division, int credit) {
		this.division = division;
		this.credit = credit;
	}
	
	public Course(int year, int semester, String course_id, String title, String division, int credit) {
		this.year = year;
		this.semester = semester;
		this.course_id = course_id;
		this.title = title;
		this.division = division;
		this.credit = credit;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public String getCourse_id() {
		return course_id;
	}
	
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDivision() {
		return division;
	}
	
	public void setDivision(String division) {
		this.division = division;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public void setCredit(int credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "Course [year=" + year + ", semester=" + semester + ", course_id=" + course_id + ", title=" + title
				+ ", division=" + division + ", credit=" + credit + "]";
	}
}
