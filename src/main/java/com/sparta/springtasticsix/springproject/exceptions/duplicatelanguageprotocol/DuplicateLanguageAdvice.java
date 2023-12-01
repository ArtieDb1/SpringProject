package com.sparta.springtasticsix.springproject.exceptions.duplicatelanguageprotocol;

import com.sparta.springtasticsix.springproject.exceptions.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DuplicateLanguageAdvice {


    @ResponseBody
    @ExceptionHandler(DuplicateLanguageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomResponse> duplicateLanguageHandler(DuplicateLanguageException e){
        CustomResponse response = new CustomResponse(e.getMessage(), 400);
        return ResponseEntity
                .badRequest()
                .body(response);
    }

}
