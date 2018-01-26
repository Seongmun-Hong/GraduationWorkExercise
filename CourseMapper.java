package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.ac.hansung.model.Course;

public class CourseMapper implements RowMapper<Course> {

	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

		Course course = new Course();

		course.setYear(rs.getInt("year"));
		course.setSemester(rs.getInt("semester"));
		course.setCourse_id(rs.getString("course_id"));
		course.setTitle(rs.getString("title"));
		course.setDivision(rs.getString("division"));
		course.setCredit(rs.getInt("credit"));

		return course;
	}

}
