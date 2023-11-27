package com.academyproject.registration.repositories;

import com.academyproject.registration.entities.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
