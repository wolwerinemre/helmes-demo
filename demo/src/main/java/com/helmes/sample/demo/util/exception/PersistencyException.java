package com.helmes.sample.demo.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PersistencyException extends RuntimeException {
    public PersistencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
