package com.geisivan.bff_agendador_tarefas.controller;

import com.geisivan.bff_agendador_tarefas.business.service.TarefaService;
import com.geisivan.bff_agendador_tarefas.business.dto.request.TarefaRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.TarefaResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.geisivan.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
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
    @Operation(summary = "Criar nova tarefa",
               description = "Cria uma nova tarefa vinculada ao usuário autenticado."
    )
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<TarefaResponseDTO> criarTarefa(
            @RequestBody TarefaRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tarefaService.criarTarefa(dto, token));
    }

    @GetMapping
    @Operation(summary = "Listar tarefas",
               description = "Retorna todas as tarefas associadas ao usuário autenticado."
    )
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas.")
    @ApiResponse(responseCode = "401", description = "Acesso negado, usuário não autorizado")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<List<TarefaResponseDTO>> buscarTarefasPorEmail(
            @RequestHeader(value = "Authorization", required = false) String token){
        List<TarefaResponseDTO> listaTarefas = tarefaService.buscarTarefasPorEmail(token);
        return ResponseEntity.ok(listaTarefas);
    }

    @GetMapping("/periodo")
    @Operation(summary = "Listar tarefas por período",
               description = "Retorna as tarefas do usuário filtradas pelo período informado."
    )
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas.")
    @ApiResponse(responseCode = "401", description = "Acesso negado, usuário não autorizado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<List<TarefaResponseDTO>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(
                tarefaService.buscarTarefasPorPeriodo(dataInicio, dataFim, token));
    }

    @PutMapping
    @Operation(
            summary = "Atualizar tarefa",
            description = "Atualiza os dados da tarefa com base no ID informado."
    )
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso.")
    @ApiResponse(responseCode = "401", description = "Acesso negado, usuário não autorizado")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<TarefaResponseDTO> atualizarTarefa(
            @RequestParam("id") String id,
            @RequestBody TarefaRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token){
        return  ResponseEntity.ok(tarefaService.atualizarTarefa(id, dto, token));
    }

    @PatchMapping
    @Operation(
            summary = "Atualizar status da tarefa",
            description = "Atualiza o status da tarefa específica do usuário autenticado."
    )
    @ApiResponse(responseCode = "200", description = "Status da tarefa atualizado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Acesso negado, usuário não autorizado")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<TarefaResponseDTO> atualizarStatusTarefa(
            @RequestParam("id") String id,
            @RequestParam("status") StatusNotificacaoEnum status,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefaService.atualizarStatusTarefa(id, status, token));
    }

    @DeleteMapping
    @Operation(
            summary = "Excluir tarefa",
            description = "Remove uma tarefa com base no ID informado."
    )
    @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso.")
    @ApiResponse(responseCode = "401", description = "Acesso negado, usuário não autorizado")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<Void> deletarTarefa(
            @RequestParam("id") String id,
            @RequestHeader(value = "Authorization", required = false) String token){
        tarefaService.deletarTarefa(id, token);
        return ResponseEntity.noContent().build();
    }
}
