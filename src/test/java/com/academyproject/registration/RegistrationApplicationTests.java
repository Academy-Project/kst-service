package com.academyproject.registration;

import com.academyproject.registration.entities.Lecture;
import com.academyproject.registration.entities.Student;
import com.academyproject.registration.entities.Subject;
import com.academyproject.registration.entities.SubjectClass;
import com.academyproject.registration.repositories.LectureRepository;
import com.academyproject.registration.repositories.StudentRepository;
import com.academyproject.registration.repositories.SubjectClassRepository;
import com.academyproject.registration.repositories.SubjectRepository;
import com.academyproject.registration.requests.CreateKstRequest;
import com.academyproject.registration.resources.KstResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class RegistrationApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectClassRepository subjectClassRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LectureRepository lectureRepository;

    // You may need to autowire other repositories or services used by RegistrationService

    @Test
    public void testStoreEndpoint() throws Exception {
        // Create a CreateKstRequest object with necessary data

        Student studentRaw = Student.builder()
                .nim("6720210123")
                .name("Agung Prasetyo Nugroho")
                .address("Kec. Pabelan Kab. Semarang")
                .phone("0234234324323")
                .build();
        Student student = studentRepository.save(studentRaw);

        Lecture lectureRaw = new Lecture("A-101", "Jojo Nuswantoro");
        Lecture lecture = lectureRepository.save(lectureRaw);
        Subject subjectRaw = new Subject("Test Class", "TC021", true, 4);
        Subject subject = subjectRepository.save(subjectRaw);

        SubjectClass subjectClassRaw = new SubjectClass(
                1,
                7,
                9,
                1,
                "TC-123",
                lecture,
                subject
        );
        SubjectClass subjectClass = subjectClassRepository.save(subjectClassRaw);

        CreateKstRequest createKstRequest = new CreateKstRequest();
        createKstRequest.setStudentId(student.getId());
        createKstRequest.setSubjectClassId(subjectClass.getId());

        MvcResult result = mockMvc.perform(post("/api/kst")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createKstRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        // Retrieve the created KstResource from the response
        String responseBody = result.getResponse().getContentAsString();
        KstResource createdKst = objectMapper.readValue(responseBody, KstResource.class);
        assertNotNull(createdKst);
    }

}


