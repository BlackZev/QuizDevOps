package edu.esiea.QuizDevOps.back.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.QuizDevOps.back.Entities.UserAnswerEntity;
import edu.esiea.QuizDevOps.back.Services.UserAnswerService;

@RestController
@RequestMapping("/api/users")
public class UserAnswerController {
    @Autowired
    private UserAnswerService userAnswerService;

    @PostMapping("/submit")
    public ResponseEntity<UserAnswerEntity> submitAnswer(@PathVariable Long userId, @PathVariable Long questionId, @PathVariable Long answerId) {
        return ResponseEntity.ok(userAnswerService.saveUserAnswer(userId, questionId, answerId));
    }

    @GetMapping("/score")
    public ResponseEntity<Integer> getScore(@PathVariable Long userId, @PathVariable Long quizId) {
        return ResponseEntity.ok(userAnswerService.calculateScore(userId, quizId));
    }
}