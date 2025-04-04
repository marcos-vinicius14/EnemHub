package com.enemhub.backend.Controller;

import com.enemhub.backend.DTO.QuestionDTO;
import com.enemhub.backend.Service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("v1/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/load-questions")
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

    @GetMapping("/{year}")
    public ResponseEntity<Page<QuestionDTO>> getQuestionsByYear(
            @PathVariable int year,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<QuestionDTO> questionsByYear = questionService.findQuestionsByYear(year, pageable);

        if (questionsByYear.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(questionsByYear);

    }
}
