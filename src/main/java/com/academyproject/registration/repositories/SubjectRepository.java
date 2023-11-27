package com.academyproject.registration.repositories;

import com.academyproject.registration.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
