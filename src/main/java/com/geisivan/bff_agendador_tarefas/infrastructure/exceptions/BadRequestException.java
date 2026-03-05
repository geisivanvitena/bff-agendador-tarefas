package com.geisivan.bff_agendador_tarefas.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, HttpStatus.BAD_REQUEST, cause);
    }
}
