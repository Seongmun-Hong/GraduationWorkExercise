package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Course;

@Repository
public class CourseDAO {

	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sqlStatement = "select count(*) from course";
		return jdbcTemplateObject.queryForObject(sqlStatement, Integer.class);
	}

	// querying and returning a single object
	public Course getCourse(String course_id) {
		String sqlStatement = "select * from course where course_id=?";

		return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] {course_id}, new CourseMapper());
	}

	// querying and returning multiple object
	public List<Course> getCourses() {
		String sqlStatement = "select * from course";

		return jdbcTemplateObject.query(sqlStatement, new CourseMapper());
	}
	
	public boolean insert(Course course) {
		int year = course.getYear();
		int semester = course.getSemester();
		String course_id = course.getCourse_id();
		String title = course.getTitle();
		String division = course.getDivision();
		int credit = course.getCredit();
		
		String sqlStatement = "insert into course (year, semester, course_id, title, division, credit) values (?,?,?,?,?,?)";
		return (jdbcTemplateObject.update(sqlStatement, new Object[]{year, semester, course_id, title, division, credit}) == 1);
	}
	
	public boolean update(Course course) {
		int year = course.getYear();
		int semester = course.getSemester();
		String course_id = course.getCourse_id();
		String title = course.getTitle();
		String division = course.getDivision();
		int credit = course.getCredit();
		
		String sqlStatement = "update course set year=?, semester=?, title=?, division=?, credit=? where course_id=?";
		return (jdbcTemplateObject.update(sqlStatement, new Object[]{year, semester, title, division, credit, course_id}) == 1);
	}
	
	public boolean delete(String course_id) {
		String sqlStatement = "delete from course where course_id=?";
		
		return (jdbcTemplateObject.update(sqlStatement, new Object[]{course_id}) == 1);
	}

	public List<Course> getYearAndSemesterList() {
		String sqlStatement = "select distinct year, semester from course where year!=2017";

		return jdbcTemplateObject.query(sqlStatement, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Course course = new Course();

				course.setYear(rs.getInt("year"));
				course.setSemester(rs.getInt("semester"));
				
				return course;
			}
		});
	}

	public List<Course> getCourseBySemester(int year, int semester) {
		String sqlStatement = "select * from course where year=? and semester=?";

		return (jdbcTemplateObject.query(sqlStatement, new Object[]{year, semester}, new CourseMapper()));
	}
	
	public int getCreditBySemester(int year, int semester) {
		String sqlStatement = "select sum(credit) from course where year=? and semester=?";
		
		return (jdbcTemplateObject.queryForObject(sqlStatement, new Object[]{year, semester}, Integer.class));
	}
	
	public List<Course> getDivisionList() {
		String sqlStatement = "select distinct division from course";

		return jdbcTemplateObject.query(sqlStatement, new RowMapper<Course>() {

			@Override
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Course course = new Course();

				course.setDivision(rs.getString("division"));
				
				return course;
			}
		});
	}
	
	public int getCreditByDivision(String division) {
		String sqlStatement = "select sum(credit) from course where division=? and year!=2017";
		
		return (jdbcTemplateObject.queryForObject(sqlStatement, new Object[]{division}, Integer.class));
	}
	
	public int getCompletedCredit() {
		String sqlStatement = "select sum(credit) from course where year!=2017";

		return (jdbcTemplateObject.queryForObject(sqlStatement, Integer.class));
	}

	public List<Course> getRegisteredCourses() {
		String sqlStatement = "select * from course where year=2017 and semester=1";

		return (jdbcTemplateObject.query(sqlStatement, new CourseMapper()));
	}
}
