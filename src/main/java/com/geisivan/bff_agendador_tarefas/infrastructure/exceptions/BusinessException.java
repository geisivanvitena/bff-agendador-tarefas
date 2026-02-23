package com.geisivan.bff_agendador_tarefas.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends ApiException{

    public BusinessException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public BusinessException(String message, Throwable cause) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
