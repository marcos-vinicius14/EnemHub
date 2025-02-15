package com.enemhub.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "isCorrect")
    private boolean isCorrect;
}
