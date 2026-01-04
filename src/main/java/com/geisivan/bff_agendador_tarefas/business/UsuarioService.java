package com.geisivan.bff_agendador_tarefas.business;

import com.geisivan.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.in.EnderecoDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.LoginDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.TelefoneDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.UsuarioDTOIn;
import com.geisivan.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTO salvaUsuario(UsuarioDTOIn dto){
        return  usuarioClient.salvaUsuario(dto);
    }

    public String loginUsuario(LoginDTOIn dto){
        return usuarioClient.login(dto);
    }

    public UsuarioDTO buscaUsuarioPorEmail(String email, String token){
        return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletaUsuarioPorEmail(String email, String token){
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }

    public UsuarioDTO atualizaDadosUsuario(UsuarioDTOIn dto, String token){
        return usuarioClient.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTO atualizaDadosEndereco(EnderecoDTOIn dto, Long id, String token){
        return usuarioClient.atualizaDadosEndereco(dto, id, token);
    }

    public TelefoneDTO atualizaDadosTelefone(TelefoneDTOIn dto, Long id, String token){
        return usuarioClient.atualizaDadosTelefone(dto, id, token);
    }

    public EnderecoDTO cadastraEndereco(EnderecoDTOIn dto, String token){
        return usuarioClient.cadastraEndereco(dto, token);
    }

    public TelefoneDTO cadastraTelefone(TelefoneDTOIn dto, String token){
        return usuarioClient.cadastraTelefone(dto, token);
    }
}
