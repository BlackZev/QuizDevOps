package edu.esiea.QuizDevOps.back.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.esiea.QuizDevOps.back.Entities.AdminEntity;
import edu.esiea.QuizDevOps.back.Services.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService service;
    
    @PostMapping("/create")
    public ResponseEntity<AdminEntity> create(@RequestBody AdminEntity admin) {
        return ResponseEntity.ok(service.create(admin));
    }
    
    @GetMapping("/id/{id")
    public ResponseEntity<AdminEntity> readById(@PathVariable Long id) {
        return ResponseEntity.ok(service.readById(id));
    }
}