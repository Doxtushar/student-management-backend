package com.silicon.practice.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silicon.practice.model.DashboardResponse;
import com.silicon.practice.repository.StudentRepository;
import com.silicon.practice.service.DashboardService;

@Service
@Transactional(readOnly = true)
public class DashboardServiceImpl implements DashboardService {

    private static final String MALE = "male";
    private static final String FEMALE = "female";

    private final StudentRepository studentRepository;

    public DashboardServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public DashboardResponse getDashboardSummary() {
        return new DashboardResponse(
                studentRepository.count(),
                studentRepository.countDistinctCourses(),
                studentRepository.countByGenderIgnoreCase(MALE),
                studentRepository.countByGenderIgnoreCase(FEMALE));
    }
}
