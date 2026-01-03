package com.geisivan.bff_agendador_tarefas.infrastructure.client;

import com.geisivan.bff_agendador_tarefas.business.dto.TarefaDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.out.TarefaDTOOut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email", url = "${email.url}")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefaDTOOut dto);
}
