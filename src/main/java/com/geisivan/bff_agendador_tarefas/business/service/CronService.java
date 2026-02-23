package com.geisivan.bff_agendador_tarefas.business.service;

import com.geisivan.bff_agendador_tarefas.business.dto.request.LoginRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.TarefaResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefasProximaHora(){

        log.info("Iniciada a busca de tarefas");

        try {
            String token = login(converterParaRequestDTO());
            LocalDateTime agora = LocalDateTime.now();
            LocalDateTime inicioJanela = agora.plusHours(1);
            LocalDateTime fimJanela = inicioJanela.plusMinutes(5);

            log.info("Buscando tarefas com evento entre {} e {}", inicioJanela, fimJanela);

            List<TarefaResponseDTO> listaTarefas = tarefaService.buscarTarefasPorPeriodo(
                    inicioJanela, fimJanela, token);

            log.info("Tarefas encontradas: {}", listaTarefas.size());

            listaTarefas.forEach(tarefa -> {
                try {
                    emailService.enviarEmail(tarefa);
                    tarefaService.atualizarStatusTarefa(
                            tarefa.getId(),
                            StatusNotificacaoEnum.NOTIFICADO,
                            token);

                    log.info("E-mail enviado para o usuário {}",
                            tarefa.getEmailUsuario());

                }catch (Exception e){
                    log.error("Erro ao processar tarefa {}: {}",
                            tarefa.getId(),
                            e.getMessage(), e);

                    tarefaService.atualizarStatusTarefa(
                            tarefa.getId(),
                            StatusNotificacaoEnum.ERRO,
                            token);
                }
            });

            log.info("Finalizada a busca e notificação de tarefas");

        }catch (Exception e){
            log.error("Erro geral no job de notificação: {}",
                    e.getMessage(), e);
        }
    }

    private String login(LoginRequestDTO dto){
        return usuarioService.loginUsuario(dto);
    }

    private LoginRequestDTO converterParaRequestDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
