package com.enemhub.backend.Controller;

import com.enemhub.backend.DTO.LoadQuestionDTO;
import com.enemhub.backend.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/load-question")
    public ResponseEntity<String> loadQuestionDetails(@RequestBody LoadQuestionDTO loadQuestionDTO)  {
        try {
            questionService.loadQuestionDetails(loadQuestionDTO);
            return ResponseEntity.ok("Question loaded successfully " + loadQuestionDTO);
        } catch (Exception e) {
            throw new RuntimeException("Error loading question");
        }
    }


}
