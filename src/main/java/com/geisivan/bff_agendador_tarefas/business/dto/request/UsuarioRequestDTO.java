package com.geisivan.bff_agendador_tarefas.business.dto.request;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoRequestDTO> enderecos;
    private List<TelefoneRequestDTO> telefones;
}
