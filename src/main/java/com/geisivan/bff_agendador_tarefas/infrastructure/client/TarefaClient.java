package com.geisivan.bff_agendador_tarefas.infrastructure.client;

import com.geisivan.bff_agendador_tarefas.business.dto.TarefaDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.in.TarefaDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.out.TarefaDTOOut;
import com.geisivan.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefa", url = "${tarefa.url}")
public interface TarefaClient {

    @PostMapping
    TarefaDTO salvaTarefa(@RequestBody TarefaDTOIn dto,
                          @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefaDTOOut> buscaTarefaAgendadaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefaDTO> buscaTarefaPorEmail(@RequestHeader("Authorization") String token);

    @PutMapping
    TarefaDTO updateTarefa(@RequestBody TarefaDTOIn dto,
                           @RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDTO alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum satus,
                                      @RequestParam("id") String id,
                                      @RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id,
                           @RequestHeader("Authorization") String token);

}