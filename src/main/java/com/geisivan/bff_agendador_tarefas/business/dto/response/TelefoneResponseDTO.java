package com.geisivan.bff_agendador_tarefas.business.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneResponseDTO {
    private Long id;
    private String numero;
    private String ddd;
}
