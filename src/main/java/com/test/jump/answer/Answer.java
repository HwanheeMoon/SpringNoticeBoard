package com.test.jump.answer;

import java.time.LocalDateTime;

import com.test.jump.question.Question;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import com.test.jump.user.SiteUser;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;


    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;

}
