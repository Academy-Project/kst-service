package com.academyproject.registration.repositories;

import com.academyproject.registration.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
