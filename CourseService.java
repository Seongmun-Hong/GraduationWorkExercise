package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.CourseDAO;
import kr.ac.hansung.model.Course;

@Service
public class CourseService {
	
	private CourseDAO courseDAO;

	@Autowired
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	
	public List<Course> getYearAndSemesterList() {
		return courseDAO.getYearAndSemesterList();
	}
	
	public List<Course> getCourseBySemester(int year, int semester) {
		return courseDAO.getCourseBySemester(year, semester);
	}
	
	public int getCreditBySemester(int year, int semester) {
		return courseDAO.getCreditBySemester(year, semester);
	}
	
	public List<Course> getDivisionList() {
		return courseDAO.getDivisionList();
	}
	
	public int getCreditByDivision(String division) {
		return  courseDAO.getCreditByDivision(division);
	}
	
	public int getCompletedCredit() {
		return courseDAO.getCompletedCredit();
	}
	
	public void insert(Course course) {
		courseDAO.insert(course);
	}
	
	public List<Course> getRegisteredCourses() {
		return courseDAO.getRegisteredCourses();
	}

}
