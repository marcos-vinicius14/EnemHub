package com.enemhub.backend.Service;

import com.enemhub.backend.DTO.QuestionDTO;
import com.enemhub.backend.Mapper.QuestionMapper;
import com.enemhub.backend.Model.AlternativeModel;
import com.enemhub.backend.Model.QuestionModel;
import com.enemhub.backend.Repository.AlternativeRepository;
import com.enemhub.backend.Repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AlternativeRepository alternativeRepository;
    private final ObjectMapper objectMapper;
    private final PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    public void loadAllQuestionsForYear(String year) throws Exception {
        // Define o padrão de busca para os arquivos
        String pattern = String.format("public/%s/questions/*/details.json", year);

        // Busca os arquivos
        Resource[] resources = resourceResolver.getResources(pattern);

        // Verifica se algo foi encontrado
        if (resources.length == 0) {
            throw new RuntimeException("Nenhuma questão encontrada para o ano " + year);
        }

        for (Resource resource : resources) {
            try (InputStream inputStream = resource.getInputStream()) {
                QuestionModel question = objectMapper.readValue(inputStream, QuestionModel.class);

                List<AlternativeModel> alternativesTemp = question.getAlternatives();
                question.setAlternatives(null);

                QuestionModel savedQuestion = questionRepository.save(question);

                for (AlternativeModel alternative : alternativesTemp) {
                    alternative.setQuestion(savedQuestion);
                    System.out.println("Associando alternativa " + alternative.getLetter() + " à questão ID " + question.getId());
                    System.out.println("Questao_id na alternativa: " + (alternative.getQuestion() != null ? alternative.getQuestion().getId() : "null"));
                }

                alternativeRepository.saveAll(alternativesTemp);
                questionRepository.save(savedQuestion);
            } catch (Exception e) {
                System.err.println("Erro ao processar " + resource.getFilename() + ": " + e.getMessage() + pattern);
            }
        }
    }

    @Transactional
    public Page<QuestionDTO> findQuestionsByYear(int year, Pageable pageable) {
        Page<QuestionModel> questions = questionRepository.findQuestionModelByYear(year, pageable);

        if (questions.isEmpty()) {
            return new PageImpl<>(Collections.emptyList());
        }

        return questions.map(QuestionMapper::toDTO);

    }

}