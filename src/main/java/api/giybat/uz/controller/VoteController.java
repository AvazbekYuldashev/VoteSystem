package api.giybat.uz.controller;


import api.giybat.uz.dto.responce.VoteDTO;
import api.giybat.uz.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @PostMapping({"","/"})
    private ResponseEntity<VoteDTO> create(@RequestBody VoteDTO dto) {
        return ResponseEntity.ok().body(voteService.create(dto));
    }

    @GetMapping("/{id}")
    private ResponseEntity<VoteDTO> get(@PathVariable VoteDTO dto) {
        return ResponseEntity.ok().body(voteService.getById(dto.getId()));
    }

    @GetMapping({"", "/"})
    private ResponseEntity<List<VoteDTO>> getAll() {
        return ResponseEntity.ok().body(voteService.getAll());
    }

    @DeleteMapping("{id}")
    private ResponseEntity<VoteDTO> delete(@PathVariable VoteDTO dto) {
        voteService.delete(dto.getId());
        return ResponseEntity.ok().build();

    }

}

