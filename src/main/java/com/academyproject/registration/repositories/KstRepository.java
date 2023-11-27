package com.academyproject.registration.repositories;

import com.academyproject.registration.entities.Kst;
import com.academyproject.registration.entities.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KstRepository extends JpaRepository<Kst, Long> {

    public int countBySubjectClass(SubjectClass subjectClass);

}
