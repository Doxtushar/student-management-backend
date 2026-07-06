package com.silicon.practice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.silicon.practice.model.StudentDetails;

@Repository
public interface StudentRepository extends JpaRepository<StudentDetails, Integer> {
	List<StudentDetails> findByNameContainingIgnoreCase(String name);

	long countByGenderIgnoreCase(String gender);

	@Query("select count(distinct s.course) from StudentDetails s where s.course is not null and trim(s.course) <> ''")
	long countDistinctCourses();
}
