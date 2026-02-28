package com.geisivan.bff_agendador_tarefas.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException{

    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
    public ConflictException(String message, Throwable cause) {
        super(message, HttpStatus.CONFLICT, cause);
    }
}
