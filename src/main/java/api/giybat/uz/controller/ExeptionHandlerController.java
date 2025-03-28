package api.giybat.uz.controller;

import api.giybat.uz.exps.AppBadExeption;
import api.giybat.uz.exps.NotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExeptionHandlerController {
    @ExceptionHandler(AppBadExeption.class)
    public ResponseEntity<String> handle(AppBadExeption ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    @ExceptionHandler(NotFoundExeption.class)
    public ResponseEntity<String> handle(NotFoundExeption ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleExp(RuntimeException ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
