package com.enemhub.backend.Mapper;

import com.enemhub.backend.DTO.AlternativeDTO;
import com.enemhub.backend.DTO.QuestionDTO;
import com.enemhub.backend.Model.AlternativeModel;
import com.enemhub.backend.Model.QuestionModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    // Converte QuestionDTO para QuestionModel
    public QuestionModel map(QuestionDTO questionDTO) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setId(questionDTO.id());
        questionModel.setTitle(questionDTO.title());
        questionModel.setQuestionIndex(questionDTO.questionIndex());
        questionModel.setYear(questionDTO.year());
        questionModel.setLanguage(questionDTO.language());
        questionModel.setDiscipline(questionDTO.discipline());
        questionModel.setContext(questionDTO.context());
        questionModel.setCorrectAlternative(questionDTO.correctAlternative());
        questionModel.setAlternativesIntroduction(questionDTO.alternativesIntroduction());

        List<AlternativeModel> alternatives = questionDTO.alternatives().stream()
                .filter(Objects::nonNull)
                .map(this::mapAlternative)
                .collect(Collectors.toList());
        questionModel.setAlternatives(alternatives);

        return questionModel;
    }

    // Converte QuestionModel para QuestionDTO
    public QuestionDTO map(QuestionModel questionModel) {
        List<AlternativeDTO> alternatives = questionModel.getAlternatives().stream()
                .map(this::mapAlternativeToDTO)
                .collect(Collectors.toList());
        return new QuestionDTO(
                questionModel.getId(),
                questionModel.getTitle(),
                questionModel.getQuestionIndex(),
                questionModel.getYear(),
                questionModel.getLanguage(),
                questionModel.getDiscipline(),
                questionModel.getContext(),
                questionModel.getCorrectAlternative(),
                questionModel.getAlternativesIntroduction(),
                alternatives
        );
    }

    private AlternativeModel mapAlternative(AlternativeDTO dto) {
        if (dto == null || dto.letter() == null || dto.id() == null) {
            throw new IllegalArgumentException("Campos obrigatórios em AlternativeDTO estão ausentes.");
        }
        AlternativeModel model = new AlternativeModel();
        model.setId(dto.id());
        model.setLetter(dto.letter());
        model.setText(dto.text());
        model.setFile(dto.file());
        model.setCorrect(dto.isCorrect());
        return model;
    }

    private AlternativeDTO mapAlternativeToDTO(AlternativeModel model) {
        if (model == null) {
            return null;
        }
        return new AlternativeDTO(
                model.getId(),
                model.getLetter(),
                model.getText(),
                model.getFile(),
                model.isCorrect()
        );
    }
}