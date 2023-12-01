package com.sparta.springtasticsix.springproject.exceptions.countrynotfoundprotocol;

import com.sparta.springtasticsix.springproject.exceptions.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CountryNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CountryNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomResponse> countryNotFoundHandler(CountryNotFoundException e){
        CustomResponse response = new CustomResponse(e.getMessage(), 406);
        return ResponseEntity
                .badRequest()
                .body(response);
    }
}
