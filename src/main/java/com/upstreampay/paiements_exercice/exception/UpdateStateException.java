package com.upstreampay.paiements_exercice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UpdateStateException extends RuntimeException{
    public UpdateStateException(String message) {
        super(message);
    }
}
