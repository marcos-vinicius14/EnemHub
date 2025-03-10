package com.enemhub.backend.Service;

import com.enemhub.backend.DTO.LoadQuestionDTO;
import com.enemhub.backend.Model.AlternativeModel;
import com.enemhub.backend.Model.QuestionModel;
import com.enemhub.backend.Repository.AlternativeRepository;
import com.enemhub.backend.Repository.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AlternativeRepository alternativeRepository;
    private final ObjectMapper objectMapper;
    private final PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    public QuestionService(QuestionRepository questionRepository, AlternativeRepository alternativeRepository, ObjectMapper objectMapper) {
        this.questionRepository = questionRepository;
        this.alternativeRepository = alternativeRepository;
        this.objectMapper = objectMapper;
    }

    // Método original mantido para compatibilidade, se necessário
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
            QuestionModel question = objectMapper.readValue(inputStream, QuestionModel.class);
            questionRepository.save(question);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + resource.getPath(), e);
        }
    }


    public void loadAllQuestionsForYear(String year) throws Exception {
        // Define o padrão de busca para os arquivos
        String pattern = String.format("public/%s/questions/*/details.json", year);

        // Busca os arquivos
        Resource[] resources = resourceResolver.getResources(pattern);

        // Verifica se algo foi encontrado
        if (resources.length == 0) {
            throw new RuntimeException("Nenhuma questão encontrada para o ano " + year);
        }

        // Processa os arquivos encontrados
        for (Resource resource : resources) {
            try (InputStream inputStream = resource.getInputStream()) {
                QuestionModel question = objectMapper.readValue(inputStream, QuestionModel.class);

                //TODO: Salva as questões, sem alternativas, para gerar o ID.
                List<AlternativeModel> alternativesTemp = question.getAlternatives();
                question.setAlternatives(null); //TODO: Remove as alternativas temporariamente

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

}