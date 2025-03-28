package api.giybat.uz.controller;


import api.giybat.uz.dto.responce.SurveyDTO;
import api.giybat.uz.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @PostMapping("")
    private ResponseEntity<SurveyDTO> create(@RequestBody SurveyDTO surveyDTO) {
        return ResponseEntity.ok().body(surveyService.create(surveyDTO));
    }

    @GetMapping("{id}")
    private ResponseEntity<SurveyDTO> get(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(surveyService.getById(id));
    }

    @GetMapping({"", "/"})
    private ResponseEntity<List<SurveyDTO>> getAll() {
        return ResponseEntity.ok().body(surveyService.getAll());
    }

    @DeleteMapping("{id}")
    private ResponseEntity<SurveyDTO> delete(@PathVariable("id") Integer id) {
        surveyService.delete(id);
        return ResponseEntity.ok().body(surveyService.getById(id));
    }
}

