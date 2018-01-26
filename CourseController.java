package kr.ac.hansung.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.model.Course;
import kr.ac.hansung.service.CourseService;

@Controller
public class CourseController {
	
	private CourseService courseService;
	
	@Autowired
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@RequestMapping("/showcreditbysemester")
	public String showCreditBySemester(Model model) {
		
		List<Course> creditBySemesterList = courseService.getYearAndSemesterList();
		
		for(int i=0; i<creditBySemesterList.size(); i++) {
			creditBySemesterList.get(i).setCredit(courseService.getCreditBySemester(creditBySemesterList.get(i).getYear(), creditBySemesterList.get(i).getSemester()));
		}
		
		model.addAttribute("courses", creditBySemesterList);
		
		return "creditbysemester"; 
	}

	@RequestMapping("/showcoursebysemester")
	public String showCourseBySemester(Model model, String year, String semester) {
		
		List<Course> courseBySemesterList = courseService.getCourseBySemester(Integer.parseInt(year), Integer.parseInt(semester));

		model.addAttribute("courses", courseBySemesterList);

		return "coursebysemester";
	}

	@RequestMapping("/showcreditbydivision")
	public String showCreditByDivision(Model model) {

		List<Course> creditByDivisionList = courseService.getDivisionList();
		
		for(int i=0; i<creditByDivisionList.size(); i++) {
			creditByDivisionList.get(i).setCredit(courseService.getCreditByDivision(creditByDivisionList.get(i).getDivision()));
		}

		creditByDivisionList.add(new Course("ÃÑ ÇÐÁ¡", courseService.getCompletedCredit()));

		model.addAttribute("courses", creditByDivisionList);

		return "creditbydivision";
	}
	
	@RequestMapping("/registerforcourses")
	public String registerForCourses(Model model) {
		
		model.addAttribute(new Course());
		
		return "registerforcourses";
	}
	
	@RequestMapping("/doregister")
	public String doRegister(Model model, @Valid Course course, BindingResult result) {
		
		if(result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for(ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "registerforcourses";
		}
		
		courseService.insert(course);
		
		return "registersuccess";
	}
	
	@RequestMapping("/showregisteredcourses")
	public String showCourseRegistered(Model model) {

		List<Course> registeredCoursesrList = courseService.getRegisteredCourses();

		model.addAttribute("courses", registeredCoursesrList);
		
		return "registeredcourse";
	}
}
