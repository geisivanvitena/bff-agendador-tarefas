package com.geisivan.bff_agendador_tarefas.business;

import com.geisivan.bff_agendador_tarefas.business.dto.out.TarefaDTOOut;
import com.geisivan.bff_agendador_tarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefaDTOOut dto){
        emailClient.enviarEmail(dto);
    }
}
