package com.geisivan.bff_agendador_tarefas.business;

import com.geisivan.bff_agendador_tarefas.business.dto.in.LoginDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.out.TarefaDTOOut;
import com.geisivan.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
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
    public void buscaTarefasProximaHora(){
        String token = login(converterParaDTOIn());
        log.info("Iniciada a busca de tarefas");
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        List<TarefaDTOOut> listaTarefas = tarefaService.buscaTarefaAgendadaPorPeriodo(
                horaAtual, horaFutura, token);

        log.info("Tarefas encontradas" + listaTarefas);

        listaTarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            log.info("E-mail enviado para o usuário " + tarefa.getEmailUsuario());
            tarefaService.alteraStatusTarefa(StatusNotificacaoEnum.NOTIFICADO,
                    tarefa.getId(), token);
        });
        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(LoginDTOIn dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginDTOIn converterParaDTOIn(){
        return LoginDTOIn.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
