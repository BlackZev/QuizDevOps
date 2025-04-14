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

import edu.esiea.QuizDevOps.back.Entities.QuizEntity;
import edu.esiea.QuizDevOps.back.Services.QuizService;

@RestController
@RequestMapping("/api/quizs")
public class QuizController {

    @Autowired
    private QuizService service;
    
    @PostMapping("/create")
    public ResponseEntity<QuizEntity> create(@RequestBody QuizEntity quiz) {
        return ResponseEntity.ok(service.create(quiz));
    }

    @GetMapping("/all")
    public List<QuizEntity> getAll() {
        return service.readAll();
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<QuizEntity> readById(@PathVariable Long id) {
        return ResponseEntity.ok(service.readById(id));
    }
    
    @PostMapping("/update/{id}")
    public ResponseEntity<QuizEntity> update(@PathVariable Long id, @RequestBody QuizEntity quiz) {
    	QuizEntity updatedQuiz = service.update(id, quiz);
        return ResponseEntity.ok(updatedQuiz);
    }
    
    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}