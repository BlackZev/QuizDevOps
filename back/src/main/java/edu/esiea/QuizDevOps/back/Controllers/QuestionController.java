package edu.esiea.QuizDevOps.back.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.QuizDevOps.back.Entities.QuestionEntity;
import edu.esiea.QuizDevOps.back.Services.QuestionService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @PostMapping("/create")
    public QuestionEntity create(@RequestBody QuestionEntity question) {
        return service.createQuestion(question);
    }
    
    @GetMapping("/quizId/{id}")
    public List<QuestionEntity> readByQuizId(@PathVariable Long quizId) {
        return service.readByQuizId(quizId);
    }
}