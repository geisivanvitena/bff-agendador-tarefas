package com.geisivan.bff_agendador_tarefas.infrastructure.client.config;

import com.geisivan.bff_agendador_tarefas.infrastructure.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String message = erroMessage(response);

        return   switch (response.status()){
            case 400 -> new BadRequestException(message);
            case 401 -> new UnauthorizedException(message);
            case 404 -> new ResourceNotFoundException(message);
            case 409 -> new ConflictException(message);
            default -> new BusinessException(message);
       };
    }

    private String erroMessage(Response response) {
        if (response.body() == null) {
            return "Erro ao processar requisição externa";
        }
        try {
            return new String(response.body().asInputStream()
                    .readAllBytes(), StandardCharsets.UTF_8);

        }catch (IOException e){
            return "Erro ao ler resposta externa";
        }
    }
}
