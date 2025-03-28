package api.giybat.uz.controller;


import api.giybat.uz.dto.responce.ProfileDTO;
import api.giybat.uz.enums.AppLangulage;
import api.giybat.uz.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService userService;

    @PostMapping({"","/"})
    private ResponseEntity<ProfileDTO> createUser(@RequestBody ProfileDTO user) {
        return ResponseEntity.ok().body(userService.create(user));
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProfileDTO> getUser(@PathVariable("id") Integer id,
                                               @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLangulage lang) {
        return ResponseEntity.ok().body(userService.getByIds(id, lang));
    }

    @GetMapping({"","/"})
    private ResponseEntity<List<ProfileDTO>> getAllUsers(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLangulage lang) {
        return ResponseEntity.ok().body(userService.getAll());
    }

    @DeleteMapping({"/{id}"})
    private ResponseEntity<Boolean> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

