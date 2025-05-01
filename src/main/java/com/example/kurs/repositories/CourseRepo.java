package com.example.kurs.repositories;

import com.example.kurs.models.MedicalCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<MedicalCourse, Long> {
}
