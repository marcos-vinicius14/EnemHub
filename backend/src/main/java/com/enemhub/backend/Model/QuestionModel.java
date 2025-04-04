package com.enemhub.backend.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The QuestionModel class serves as an entity representing a question in a question-answering
 * system. It is mapped to the "tb_questions" table in the database.
 *
 * This class contains fields for storing various attributes of a question, including its title,
 * index, year, language, discipline, contextual information, correct answer, and introduction
 * to the alternatives.
 *
 * A one-to-many relationship is established with the AlternativeModel class, allowing each
 * question to be associated with multiple alternatives.
 *
 * Annotations in this class are used for ORM (Object-Relational Mapping) functionality, and
 * Lombok is used to generate boilerplate code such as getters, setters, and constructors.
 */
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

    @Column(name = "question_index")
    private int questionIndex;

    @Column(name = "year")
    private int year;

    @Column(name = "language")
    private String language;

    @Column(name = "discipline")
    private String discipline;

    @Column(length = 5000, name = "context")
    private String context;

    @Column(name = "correct_alternative")
    private String correctAlternative;

    @Column(name = "alternatives_introduction")
    private String alternativesIntroduction;

    //@JoinColumn(name = "question_id")
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AlternativeModel> alternatives;
}
