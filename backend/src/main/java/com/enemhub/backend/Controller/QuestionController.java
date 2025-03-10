package com.enemhub.backend.Controller;

import com.enemhub.backend.DTO.LoadQuestionDTO;
import com.enemhub.backend.Model.QuestionModel;
import com.enemhub.backend.Service.QuestionService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/load-question")
    public ResponseEntity<String> loadQuestionDetails(@RequestBody String request)  {
        try {
            JSONObject jsonObject = new JSONObject(request);
            String year = jsonObject.getString("request");
             questionService.loadAllQuestionsForYear(year);
            return ResponseEntity.ok("Question loaded successfully!");
        } catch (Exception e) {
            throw new RuntimeException("Error loading question: " + e.getMessage());
        }
    }


}
