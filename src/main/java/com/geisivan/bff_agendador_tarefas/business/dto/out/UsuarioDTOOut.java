package com.geisivan.bff_agendador_tarefas.business.dto.out;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTOOut {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOOut> enderecos;
    private List<TelefoneDTOOut> telefones;
}
