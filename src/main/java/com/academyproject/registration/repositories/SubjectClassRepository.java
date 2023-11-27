package com.academyproject.registration.repositories;

import com.academyproject.registration.entities.Subject;
import com.academyproject.registration.entities.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectClassRepository extends JpaRepository<SubjectClass, Long> {
    public List<SubjectClass> findAllBySubject(Subject subject);

    public Optional<SubjectClass> findById(Long id);
}
