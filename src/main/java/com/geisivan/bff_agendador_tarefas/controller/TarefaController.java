package com.geisivan.bff_agendador_tarefas.controller;

import com.geisivan.bff_agendador_tarefas.business.TarefaService;
import com.geisivan.bff_agendador_tarefas.business.dto.TarefaDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.in.TarefaDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.out.TarefaDTOOut;
import com.geisivan.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.geisivan.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(
        name = "Tarefas",
        description = "Operações relacionadas ao gerenciamento de tarefas"
)
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Cadastrar tarefa",
            description = "Cria uma nova tarefa associada ao usuário autenticado")
    @ApiResponse(responseCode = "200", description = "Tarefa cadastrada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TarefaDTO> salvaTarefa(@RequestBody TarefaDTOIn dto,
                                                 @RequestHeader(value = "Authorization",
                                                 required = false) String token){
        return ResponseEntity.ok(tarefaService.salvaTarefa(dto, token));
    }

    @GetMapping
    @Operation(summary = "Buscar tarefas do usuário",
            description = "Retorna todas as tarefas associadas ao usuário autenticado")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "404", description = "Nenhuma tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefaDTO>> buscaTarefaPorEmail(@RequestHeader(value = "Authorization",
                                                                required = false) String token){
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar tarefas por período",
            description = "Retorna tarefas agendadas dentro de um período informado")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "404", description = "Nenhuma tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<List<TarefaDTOOut>> buscaTarefaAgendadaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader(value = "Authorization", required = false) String token){

        return ResponseEntity.ok(
                tarefaService.buscaTarefaAgendadaPorPeriodo(dataInicio, dataFim, token));
    }

    @PutMapping
    @Operation(
            summary = "Atualizar tarefa",
            description = "Atualiza os dados de uma tarefa existente"
    )
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefaDTO> updateTarefa(@RequestBody TarefaDTOIn dto,
                                                  @RequestParam("id") String id,
                                                  @RequestHeader(value = "Authorization",
                                                  required = false) String token){
        return  ResponseEntity.ok(tarefaService.updateTarefa(dto, id, token));
    }

    @PatchMapping
    @Operation(
            summary = "Alterar status da tarefa",
            description = "Altera o status da tarefa (PENDENTE, NOTIFICADO, CONCLUÍDO)"
    )
    @ApiResponse(responseCode = "200", description = "Status da tarefa atualizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                             @RequestParam("id") String id,
                                                             @RequestHeader(value = "Authorization",
                                                             required = false) String token){
        return ResponseEntity.ok(tarefaService.alteraStatusTarefa(status, id, token));
    }

    @DeleteMapping
    @Operation(
            summary = "Excluir tarefa",
            description = "Remove uma tarefa do sistema a partir do ID informado"
    )
    @ApiResponse(responseCode = "200", description = "Tarefa excluída com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuário não autorizado")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(value = "Authorization",
                                                  required = false) String token){
        tarefaService.deletaTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }
}
