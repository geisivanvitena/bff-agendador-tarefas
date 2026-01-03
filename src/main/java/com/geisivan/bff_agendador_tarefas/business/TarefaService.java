package com.geisivan.bff_agendador_tarefas.business;

import com.geisivan.bff_agendador_tarefas.business.dto.TarefaDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.in.TarefaDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.out.TarefaDTOOut;
import com.geisivan.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.geisivan.bff_agendador_tarefas.infrastructure.client.TarefaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaClient tarefaClient;

    public TarefaDTO salvaTarefa(TarefaDTOIn dto, String token){
        return tarefaClient.salvaTarefa(dto, token);
    }

    public List<TarefaDTOOut> buscaTarefaAgendadaPorPeriodo(LocalDateTime dataInicio,
                                                            LocalDateTime dataFim,
                                                            String token){
        return tarefaClient.buscaTarefaAgendadaPorPeriodo(dataInicio, dataFim, token);
    }

    public List<TarefaDTO> buscaTarefaPorEmail(String token){
        return tarefaClient.buscaTarefaPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token){
        tarefaClient.deletaTarefaPorId(id, token);
    }

    public TarefaDTO alteraStatusTarefa(StatusNotificacaoEnum status, String id, String token){
        return tarefaClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefaDTO updateTarefa(TarefaDTOIn dto, String id, String token){
        return tarefaClient.updateTarefa(dto, id, token);
    }
}
