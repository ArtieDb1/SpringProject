package com.sparta.springtasticsix.springproject.exceptions.duplicatecityprotocol;

import com.sparta.springtasticsix.springproject.exceptions.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DuplicateCityAdvice {

    @ResponseBody
    @ExceptionHandler(DuplicateCityException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<CustomResponse> duplicateCityHandler(DuplicateCityException e){
        CustomResponse response = new CustomResponse(e.getMessage(), 406);
        return ResponseEntity
                .badRequest()
                .body(response);
    }


}
