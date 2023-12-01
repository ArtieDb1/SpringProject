package com.sparta.springtasticsix.springproject.exceptions.citynotfoundprotocol;

import com.sparta.springtasticsix.springproject.exceptions.CustomResponse;
import com.sparta.springtasticsix.springproject.exceptions.invalidcodeprotocol.InvalidCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CityNotFoundAdvice {


    @ResponseBody
    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    //404
    public ResponseEntity<CustomResponse> cityNotFoundHandler(CityNotFoundException e) {

        CustomResponse cr = new CustomResponse(e.getMessage(), 404);
        return ResponseEntity
                .badRequest()
                .body(cr);

    }

}
