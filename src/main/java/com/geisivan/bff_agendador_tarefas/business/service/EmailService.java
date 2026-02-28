package com.geisivan.bff_agendador_tarefas.business.service;

import com.geisivan.bff_agendador_tarefas.business.dto.response.TarefaResponseDTO;
import com.geisivan.bff_agendador_tarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefaResponseDTO dto){
        emailClient.enviarEmail(dto);
    }
}
