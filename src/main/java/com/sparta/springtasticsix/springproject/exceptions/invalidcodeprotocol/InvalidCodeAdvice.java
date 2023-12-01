package com.sparta.springtasticsix.springproject.exceptions.invalidcodeprotocol;

import com.sparta.springtasticsix.springproject.exceptions.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidCodeAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    public ResponseEntity<CustomResponse> invalidCodeHandler(InvalidCodeException e){

        CustomResponse cr = new CustomResponse(e.getMessage(), 400);


        return ResponseEntity
                .badRequest()
                .body(cr);
    }


}
