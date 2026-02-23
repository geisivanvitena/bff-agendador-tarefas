package com.geisivan.bff_agendador_tarefas.business.service;

import com.geisivan.bff_agendador_tarefas.business.dto.request.TarefaRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.TarefaResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.geisivan.bff_agendador_tarefas.infrastructure.client.TarefaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaClient tarefaClient;

    public TarefaResponseDTO criarTarefa(TarefaRequestDTO dto, String token){
        return tarefaClient.criarTarefa(dto, token);
    }

    public List<TarefaResponseDTO> buscarTarefasPorEmail(String token){
        return tarefaClient.buscarTarefasPorEmail(token);
    }

    public List<TarefaResponseDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicio,
                                                           LocalDateTime dataFim,
                                                           String token){
        return tarefaClient.buscarTarefasPorPeriodo(dataInicio, dataFim, token);
    }

    public TarefaResponseDTO atualizarTarefa(String id, TarefaRequestDTO dto, String token){
        return tarefaClient.atualizarTarefa(id, dto, token);
    }

    public TarefaResponseDTO atualizarStatusTarefa(String id, StatusNotificacaoEnum status,  String token){
        return tarefaClient.atualizarStatusTarefa(id, status, token);
    }

    public void deletarTarefa(String id, String token){
        tarefaClient.deletarTarefa(id, token);
    }
}
