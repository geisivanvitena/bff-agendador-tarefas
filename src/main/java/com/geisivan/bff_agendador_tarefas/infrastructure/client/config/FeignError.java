package com.geisivan.bff_agendador_tarefas.infrastructure.client.config;

import com.geisivan.bff_agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.geisivan.bff_agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.geisivan.bff_agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.geisivan.bff_agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        return   switch (response.status()){
            case 401 -> new UnauthorizedException("Acesso negado: Usuário não autorizado.");
            case 404 -> new ResourceNotFoundException("Recurso não encontrado.");
            case 409 -> new ConflictException("Usuário já cadastrado.");
            default -> new BusinessException("Erro interno do servidor.");
       };
    }
}
