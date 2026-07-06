package com.silicon.practice.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicon.practice.exception.ResourceNotFoundException;
import com.silicon.practice.model.StudentDetails;
import com.silicon.practice.repository.StudentRepository;
import com.silicon.practice.service.StudentService;

@Service
@Transactional(readOnly = true)
public class StudentImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public StudentDetails save(StudentDetails studentDetails) {
        studentDetails.setId(null);
        return studentRepository.save(studentDetails);
    }

    @Override
    public StudentDetails getStudentById(Integer id) {

        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id : " + id));
    }

    @Override
    public List<StudentDetails> searchStudent(String name) {

        return studentRepository.findByNameContainingIgnoreCase(name);

    }

    @Override
    @Transactional
    public StudentDetails update(StudentDetails updatedDataFromFrontend) {
        return update(updatedDataFromFrontend.getId(), updatedDataFromFrontend);
    }

    @Override
    @Transactional
    public StudentDetails update(Integer id, StudentDetails updatedDataFromFrontend) {
        if (id == null) {
            throw new IllegalArgumentException("Student id is required for update");
        }

        StudentDetails existingStudent = studentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));

        copyStudentDetails(updatedDataFromFrontend, existingStudent);

        return studentRepository.save(existingStudent);
    }

    @Override
    @Transactional
    public String deleteStudent(Integer id) {

        StudentDetails student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id : " + id));

        studentRepository.delete(student);

        return "Student Deleted Successfully : " + student;
    }

    @Override
    public List<StudentDetails> getStudentDetails() {
        return studentRepository.findAll();
    }

    private void copyStudentDetails(StudentDetails source, StudentDetails target) {
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        target.setMobileNumber(source.getMobileNumber());
        target.setCourse(source.getCourse());
        target.setAddress(source.getAddress());
        target.setGender(source.getGender());
        target.setDateOfBirth(source.getDateOfBirth());
    }
}
