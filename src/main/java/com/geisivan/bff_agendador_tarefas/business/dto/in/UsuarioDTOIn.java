package com.geisivan.bff_agendador_tarefas.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTOIn {

    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOIn> enderecos;
    private List<TelefoneDTOIn> telefones;
}
