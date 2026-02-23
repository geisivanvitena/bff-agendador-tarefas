package com.geisivan.bff_agendador_tarefas.business.service;

import com.geisivan.bff_agendador_tarefas.business.dto.request.*;
import com.geisivan.bff_agendador_tarefas.business.dto.response.EnderecoResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.TelefoneResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.UsuarioResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.ViaCepResponseDTO;
import com.geisivan.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto){
        return  usuarioClient.criarUsuario(dto);
    }

    public String loginUsuario(LoginRequestDTO dto){
        return usuarioClient.loginUsuario(dto);
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email, String token){
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public UsuarioResponseDTO atualizarUsuario(UsuarioRequestDTO dto, String token){
        return usuarioClient.atualizarUsuario(dto, token);
    }

    public EnderecoResponseDTO criarEndereco(EnderecoRequestDTO dto, String token){
        return usuarioClient.criarEndereco(dto, token);
    }

    public EnderecoResponseDTO atualizarEndereco(Long id, EnderecoRequestDTO dto, String token){
        return usuarioClient.atualizarEndereco(id, dto, token);
    }

    public TelefoneResponseDTO criarTelefone(TelefoneRequestDTO dto, String token){
        return usuarioClient.criarTelefone(dto, token);
    }

    public TelefoneResponseDTO atualizarTelefone(Long id, TelefoneRequestDTO dto, String token){
        return usuarioClient.atualizarTelefone(id, dto, token);
    }

    public ViaCepResponseDTO buscarEnderecoPorCep(String cep){
        return usuarioClient.buscarEnderecoPorCep(cep);
    }

    public void deletarUsuarioPorEmail(String email, String token){
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }
}
