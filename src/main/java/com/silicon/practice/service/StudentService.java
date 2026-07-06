package com.silicon.practice.service;

import java.util.List;

import com.silicon.practice.model.StudentDetails;

public interface StudentService {

    // Fetch all students
    List<StudentDetails> getStudentDetails();

    // Fetch student by ID
    StudentDetails getStudentById(Integer id);

    // Save a new student
    StudentDetails save(StudentDetails studentDetails);

    // Update an existing student
    StudentDetails update(StudentDetails updatedDataFromFrontend);

    StudentDetails update(Integer id, StudentDetails updatedDataFromFrontend);

    // Delete a student by ID (recommended for complete CRUD)
    String deleteStudent(Integer id);

    List<StudentDetails> searchStudent(String name);
}
