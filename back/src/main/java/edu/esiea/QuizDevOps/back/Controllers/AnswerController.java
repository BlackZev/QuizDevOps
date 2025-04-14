package edu.esiea.QuizDevOps.back.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.QuizDevOps.back.Entities.AnswerEntity;
import edu.esiea.QuizDevOps.back.Services.AnswerService;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    @Autowired
    private AnswerService service;

    @PostMapping("/create")
    public ResponseEntity<AnswerEntity> create(@RequestBody AnswerEntity answer) {
        return ResponseEntity.ok(service.create(answer));
    }
    
    @GetMapping("/questionId/{id}")
    public List<AnswerEntity> readByQuestionId(@PathVariable Long questionId) {
        return service.readByQuestionId(questionId);
    }
}