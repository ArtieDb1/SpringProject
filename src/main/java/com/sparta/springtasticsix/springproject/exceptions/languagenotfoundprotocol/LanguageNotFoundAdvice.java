package com.sparta.springtasticsix.springproject.exceptions.languagenotfoundprotocol;

import com.sparta.springtasticsix.springproject.exceptions.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LanguageNotFoundAdvice{

    @ResponseBody
    @ExceptionHandler(LanguageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomResponse> languageNotFoundHandler(LanguageNotFoundException e){
        CustomResponse response = new CustomResponse(e.getMessage(), 404);
        return ResponseEntity
                .badRequest()
                .body(response);
    }

}
