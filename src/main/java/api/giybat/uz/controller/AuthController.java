package api.giybat.uz.controller;

import api.giybat.uz.dto.responce.AppResponse;
import api.giybat.uz.dto.request.ProfileRequestDTO;
import api.giybat.uz.dto.responce.ProfileDTO;
import api.giybat.uz.dto.request.RegistrationDTO;
import api.giybat.uz.enums.AppLangulage;
import api.giybat.uz.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth APIs", description = "API list for managing Auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "Create User", description = "Api used for creating new User")
    @PostMapping("/registration")
    public ResponseEntity<AppResponse<String>> registration(@Valid @RequestBody RegistrationDTO dto,
                                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLangulage lang) {
        
        return ResponseEntity.ok().body(authService.registration(dto, lang));
    }

    @Operation(summary = "verification by link", description = "API used for verification")
    @GetMapping("/registration/verification/{token}/{lang}")
    public ResponseEntity<String> regVerification(@PathVariable("token") String token,
                                                  @PathVariable("lang") AppLangulage lang) {
        return ResponseEntity.ok().body(authService.regVerification(token, lang));
    }

    @Operation(summary = "login by username and password", description = "API used for Login")
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@Valid @RequestBody ProfileRequestDTO dto,
                                            @RequestHeader(value = "Accept-Language", defaultValue = "UZ") AppLangulage lang) {
        return ResponseEntity.ok().body(authService.login(dto, lang));
    }
}
