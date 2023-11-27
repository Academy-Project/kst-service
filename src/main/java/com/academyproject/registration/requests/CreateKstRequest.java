package com.academyproject.registration.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreateKstRequest {
    private Long subjectClassId;

    private Long studentId;
}
