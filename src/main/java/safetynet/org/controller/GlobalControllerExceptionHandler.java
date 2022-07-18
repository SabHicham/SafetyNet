package safetynet.org.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import safetynet.org.exception.RessourceNotFoundException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(RessourceNotFoundException ex){
        String error = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
