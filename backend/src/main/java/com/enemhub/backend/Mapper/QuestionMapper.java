package com.enemhub.backend.Mapper;

import com.enemhub.backend.DTO.AlternativeDTO;
import com.enemhub.backend.DTO.QuestionDTO;
import com.enemhub.backend.Model.AlternativeModel;
import com.enemhub.backend.Model.QuestionModel;
import java.util.List;
import java.util.stream.Collectors;


public class QuestionMapper {
    public static QuestionDTO toDTO(QuestionModel question) {
        List<AlternativeDTO> alternativeDTOs = question.getAlternatives().stream()
                .map(QuestionMapper::toAlternativeDTO)
                .collect(Collectors.toList());

        return new QuestionDTO(
                question.getId(),
                question.getTitle(),
                question.getQuestionIndex(),
                question.getYear(),
                question.getLanguage(),
                question.getDiscipline(),
                question.getContext(),
                question.getCorrectAlternative(),
                question.getAlternativesIntroduction(),
                alternativeDTOs
        );
    }

    private static AlternativeDTO toAlternativeDTO(AlternativeModel alternative) {
        return new AlternativeDTO(
                alternative.getId(),
                alternative.getLetter(),
                alternative.getText(),
                alternative.getFile(),
                alternative.isCorrect()
        );
    }
}