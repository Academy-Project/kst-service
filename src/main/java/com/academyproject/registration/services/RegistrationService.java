package com.academyproject.registration.services;

import com.academyproject.registration.entities.Kst;
import com.academyproject.registration.entities.Student;
import com.academyproject.registration.entities.SubjectClass;
import com.academyproject.registration.handlers.exceptions.NotFoundException;
import com.academyproject.registration.repositories.KstRepository;
import com.academyproject.registration.repositories.StudentRepository;
import com.academyproject.registration.repositories.SubjectClassRepository;
import com.academyproject.registration.requests.CreateKstRequest;
import com.academyproject.registration.resources.KstResource;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final KstRepository kstRepository;
    private final StudentRepository studentRepository;
    private final SubjectClassRepository subjectClassRepository;
    private final ValidationService validationService;

    @Transactional(readOnly = true)
    public KstResource store(CreateKstRequest request) {
        validationService.validate(request);
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student"));

        SubjectClass subjectClass = subjectClassRepository.findById(request.getSubjectClassId())
                .orElseThrow(() -> new NotFoundException("Subject Class"));

        // validate limit amount enrolled class
        int enrolledStudents = kstRepository.countBySubjectClass(subjectClass);
        if (enrolledStudents >= subjectClass.getChairAmount()) {
            throw new ConstraintViolationException("Subject Class is full. Cannot register more students.", null);
        }

        Kst kstRaw = Kst.builder()
                .subjectClass(subjectClass)
                .student(student)
                .build();

        var kst = kstRepository.save(kstRaw);

        return new KstResource().convertToResource(kst);
    }

    public void delete(Long kstId) {
        kstRepository.deleteById(kstId);
    }
}
