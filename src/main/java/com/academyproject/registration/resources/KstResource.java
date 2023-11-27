package com.academyproject.registration.resources;

import com.academyproject.registration.entities.Kst;
import com.academyproject.registration.entities.Student;
import com.academyproject.registration.entities.SubjectClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KstResource {
    private Long id;
    private SubjectClass subjectClass;
    private Student student;

    public KstResource convertToResource(Kst kst) {
        return KstResource.builder()
                .id(kst.getId())
                .subjectClass(kst.getSubjectClass())
                .student(kst.getStudent())
                .build();
    }
}
