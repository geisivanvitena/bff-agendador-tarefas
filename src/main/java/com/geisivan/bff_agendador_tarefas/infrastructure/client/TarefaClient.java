package com.geisivan.bff_agendador_tarefas.infrastructure.client;

import com.geisivan.bff_agendador_tarefas.business.dto.request.TarefaRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.TarefaResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefa", url = "${tarefa.url}")
public interface TarefaClient {

    @PostMapping
    TarefaResponseDTO criarTarefa(
            @RequestBody TarefaRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping
    List<TarefaResponseDTO> buscarTarefasPorEmail(
            @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping("/periodo")
    List<TarefaResponseDTO> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    TarefaResponseDTO atualizarTarefa(
            @RequestParam("id") String id,
            @RequestBody TarefaRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token);

    @PatchMapping
    TarefaResponseDTO atualizarStatusTarefa(
            @RequestParam("id") String id,
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestHeader(value = "Authorization", required = false) String token);

    @DeleteMapping
    void deletarTarefa(
            @RequestParam("id") String id,
            @RequestHeader(value = "Authorization", required = false) String token);
}