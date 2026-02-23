package com.geisivan.bff_agendador_tarefas.business.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneRequestDTO {
    private String numero;
    private String ddd;
}
