package com.enemhub.backend.DTO;

import com.enemhub.backend.Model.AlternativeModel;

import java.util.List;

public record QuestionDTO(
        Long id,
        String title,
        int questionIndex,
        int year,
        String language,
        String discipline,
        String context,
        String correctAlternative,
        String alternativesIntroduction,
        List<AlternativeModel> alternatives) {

}
