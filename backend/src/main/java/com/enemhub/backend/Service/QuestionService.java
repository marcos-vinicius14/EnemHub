package com.enemhub.backend.Service;


import com.enemhub.backend.DTO.LoadQuestionDTO;
import com.enemhub.backend.DTO.QuestionDTO;
import com.enemhub.backend.Mapper.QuestionMapper;
import com.enemhub.backend.Model.QuestionModel;
import com.enemhub.backend.Repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final ObjectMapper objectMapper;

// /home/marcos/Documentos/public
    // /home/marcos/Documentos/public/2009/questions //
    public void loadQuestionDetails(LoadQuestionDTO loadQuestionDTO) throws Exception {
        String filePathWithoutLanguage = String.format("public/%s/questions/%s/details.json", loadQuestionDTO.year(), loadQuestionDTO.index());
        String filePathWithLanguage = String.format("public/%s/questions/%s-%s/details.json", loadQuestionDTO.year(), loadQuestionDTO.index(), loadQuestionDTO.language());

        System.out.println(" ################## " + filePathWithoutLanguage + " or " + filePathWithLanguage + " #########");
        ClassPathResource resource = new ClassPathResource(filePathWithoutLanguage);

        if (!resource.exists()) {
            resource = new ClassPathResource(filePathWithLanguage);
            if (!resource.exists()) {
                throw new IOException("File not found: " + filePathWithoutLanguage + " or " + filePathWithLanguage);
            }
        }

        try (InputStream inputStream = resource.getInputStream()) {
            //QuestionDTO questionDTO = objectMapper.readValue(inputStream, QuestionDTO.class);
            QuestionModel question = objectMapper.readValue(inputStream, QuestionModel.class);
            questionRepository.save(question);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + resource.getPath(), e);
        }
    }
}
