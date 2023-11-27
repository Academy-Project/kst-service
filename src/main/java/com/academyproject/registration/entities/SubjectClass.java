package com.academyproject.registration.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false)
    private Integer startTime;

    @Column(nullable = false)
    private Integer endTime;

    @Column(nullable = false)
    private Integer chairAmount;

    @Column(nullable = false)
    private String roomCode;

    @ManyToOne
    @JoinColumn(name = "lecture_id", referencedColumnName = "id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    public SubjectClass(Integer day, Integer startTime, Integer endTime, Integer chairAmount, String roomCode, Lecture lecture, Subject subject) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.chairAmount = chairAmount;
        this.roomCode = roomCode;
        this.lecture = lecture;
        this.subject = subject;
    }
}
