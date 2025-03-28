package api.giybat.uz.controller;


import api.giybat.uz.dto.responce.QuestionDTO;
import api.giybat.uz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<QuestionDTO> create(@RequestBody QuestionDTO dto) {
        return ResponseEntity.ok().body(questionService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> get(@PathVariable Integer id) {
        return ResponseEntity.ok().body(questionService.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<QuestionDTO>> getAll() {
        return ResponseEntity.ok().body(questionService.getAll());
    }

    @GetMapping("/survey/{id}")
    public ResponseEntity<List<QuestionDTO>> getSurvey(@PathVariable Integer id) {
        return ResponseEntity.ok().body(questionService.getBySurveyId(id));
    }
}

