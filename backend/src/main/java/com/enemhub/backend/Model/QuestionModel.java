package com.enemhub.backend.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@Table(name = "tb_questions")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "title")
    private String title;

    @Column(name = "index")
    private int index;

    @Column(name = "year")
    private int year;

    @Column(name = "language")
    private String language;

    @Column(name = "discipline")
    private String discipline;

    @Column(length = 5000, name = "context")
    private String context;

    @Column(name = "correctAlternative")
    private String correctAlternative;

    @Column(name = "alternativesIntroduction")
    private String alternativesIntroduction;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private List<AlternativeModel> alternatives;
}
