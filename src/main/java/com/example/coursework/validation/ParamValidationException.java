package com.example.coursework.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParamValidationException extends RuntimeException {
    public ParamValidationException(String param) {
        super("Invalid parameter: %s".formatted(param));

    }


}