package com.enemhub.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * The AlternativeModel class serves as an entity representing an alternative for a question
 * in a question-answering system. Alternatives are potential answers associated with questions,
 * and each alternative can be identified as correct or incorrect. It is mapped to the
 * "tb_alternative" table in the database.
 *
 * This class contains fields for storing the alternative's unique identifier, letter
 * designation, descriptive text, optional file reference, and correctness indicator.
 * It is designed to be used in conjunction with the QuestionModel class.
 *
 * An AlternativeModel is linked to a specific question through a one-to-many relationship
 * defined in the QuestionModel class.
 *
 * Annotations in this class are used for ORM (Object-Relational Mapping) functionality
 * as well as validation.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_alternative")
public class AlternativeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "letter")
    private String letter;

    @Column(length = 2000, name = "text")
    private String text;

    @Column(name = "file")
    private String file;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questions_id", nullable = false)
    private QuestionModel question;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AlternativeModel that = (AlternativeModel) o;
        return isCorrect == that.isCorrect && Objects.equals(id, that.id) && Objects.equals(letter, that.letter) && Objects.equals(text, that.text) && Objects.equals(file, that.file) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, letter, text, file, isCorrect, question);
    }
}
