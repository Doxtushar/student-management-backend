package com.silicon.practice.controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silicon.practice.model.Address;
import com.silicon.practice.model.Course;
import com.silicon.practice.model.ParentResponse;
import com.silicon.practice.model.SchoolDetails;
import com.silicon.practice.model.SectionDetails;
import com.silicon.practice.model.StudentDetails;
import com.silicon.practice.model.TransportDetails;
import com.silicon.practice.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping({"/home", ""})
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Welcome API", description = "Operations related to Welcome management")
public class welcomeController {
	

	
	private final StudentService studentService;
	
	public welcomeController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	
	@GetMapping("/welcome")	

	public String welcome() {

		System.out.println("Welcomne called");
		return "Welcome to SIlicon";
	}

	@GetMapping("/courses/{id}")
	public List<Course> getCourses(@PathVariable int id)

	{
		List<Course> courses = new ArrayList<>();

		List<Course> returnCources = new ArrayList<>();

		Course courseOne = new Course();// 1st object initialization
		courseOne.setCourseId(101);
		courseOne.setCoursename("Math");

		Course courseTwo = new Course();// 2nd object initialization
		courseTwo.setCourseId(102);
		courseTwo.setCoursename("Science");

		courses.add(courseOne);
		courses.add(courseTwo);

		for (Course course : courses) {

			if (course.getCourseId() == id) {
				returnCources.add(course);
			}
		}

		return returnCources;

	}

	@PostMapping("/store")
	@Operation(summary = "Save Address", description = "Stores the user's name and address details in the system.")
	public String save(@RequestBody Address add) {
		String name = add.getName();
		String address = add.getAddress();
		System.out.println("Name :" + name);
		System.out.println("Address :" + address);
		return "Address save Successfully " + name + " : " + address;
	}

	@GetMapping("/getAllSchoolData")
	@Operation(summary = "Get School Info ", description = "This API Throw info")
	public ParentResponse getAllSchoolData() {

		ParentResponse parent = new ParentResponse();

		// School List
		List<SchoolDetails> schoolList = new ArrayList<>();

		SchoolDetails school1 = new SchoolDetails();
		school1.setSchoolId(101);
		school1.setSchoolName("Silicon Public School");
		school1.setPrincipalName("Rajesh Kumar");
		school1.setEmail("info@siliconschool.com");
		school1.setTotalStudents(1200);
		school1.setTotalTeachers(60);
		school1.setBoardType("CBSE");

		SchoolDetails school2 = new SchoolDetails();
		school2.setSchoolId(102);
		school2.setSchoolName("DAV Public School");
		school2.setPrincipalName("Suresh Kumar");
		school2.setEmail("info@davschool.com");
		school2.setTotalStudents(1500);
		school2.setTotalTeachers(75);
		school2.setBoardType("ICSE");

		schoolList.add(school1);
		schoolList.add(school2);

		// Section List
		List<SectionDetails> sectionList = new ArrayList<>();

		SectionDetails section1 = new SectionDetails();
		section1.setRegNum(33334);
		section1.setStudentName("Rajesh");

		SectionDetails section2 = new SectionDetails();
		section2.setRegNum(33335);
		section2.setStudentName("Rakesh");

		sectionList.add(section1);
		sectionList.add(section2);

		// Transport List
		List<TransportDetails> transportList = new ArrayList<>();

		TransportDetails transport1 = new TransportDetails();
		transport1.setDriverName("Kabir");
		transport1.setDVehicleNumber("MH04GH0987");

		TransportDetails transport2 = new TransportDetails();
		transport2.setDriverName("Amit");
		transport2.setDVehicleNumber("OD02AB1234");

		transportList.add(transport1);
		transportList.add(transport2);

		// Set lists in parent object
		parent.setDetails(schoolList);
		parent.setSectionDetails(sectionList);
		parent.setTransportDetails(transportList);

		return parent;
	}

	/**
	 * This method helps to retrieve all student data
	 * 
	 * @return list of all students
	 */
	@Operation(summary = "Get all student details ", description = "This API retrieve all stu")
	@GetMapping("/getAllStudent")
	public List<StudentDetails> getAllStudentDetails() {

	    List<StudentDetails> listOfStudents = studentService.getStudentDetails();
	    return listOfStudents;
	}

	@PostMapping("/students")
	public ResponseEntity<StudentDetails> createStudent(@Valid @RequestBody StudentDetails studentDetails) {
	    StudentDetails savedStudent = studentService.save(studentDetails);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
	}
	
	@GetMapping("/students")
	public List<StudentDetails> getStudents() {
	    return studentService.getStudentDetails();
	}
	
	@GetMapping("/students/{id}")
	public StudentDetails getStudent(@PathVariable Integer id) {
	    return studentService.getStudentById(id);
	}
	
	@PutMapping("/students/{id}")
	public StudentDetails updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentDetails studentDetails) {
	    return studentService.update(id, studentDetails);
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable Integer id) {
	    studentService.deleteStudent(id);
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/students/search/{name}")
	public List<StudentDetails> searchStudents(@PathVariable String name) {
	    return studentService.searchStudent(name);
	}
	
	@GetMapping("/getStudentById/{id}")
	public StudentDetails getStudentById(@PathVariable Integer id) {
	    return studentService.getStudentById(id);
	}
	
	@PostMapping("/save")
	public StudentDetails save(@Valid @RequestBody StudentDetails studentDetails) 
	{
	    return studentService.save(studentDetails);
	}
	@GetMapping("/search/{name}")
	public List<StudentDetails> searchStudent(@PathVariable String name) {

	    return studentService.searchStudent(name);

	}		
	@PutMapping("/update")
	public StudentDetails update(@Valid @RequestBody StudentDetails studentDetails) {
	    return studentService.update(studentDetails);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Integer id) {

	    StudentDetails student = studentService.getStudentById(id);

	    studentService.deleteStudent(id);

	    return "Student Deleted Successfully.\nDeleted Student Details : " + student;
	}

}
