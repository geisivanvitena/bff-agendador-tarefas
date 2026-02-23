package com.geisivan.bff_agendador_tarefas.infrastructure.client;

import com.geisivan.bff_agendador_tarefas.business.dto.request.EnderecoRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.request.LoginRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.request.TelefoneRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.request.UsuarioRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.EnderecoResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.TelefoneResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.UsuarioResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.ViaCepResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioResponseDTO criarUsuario(@RequestBody UsuarioRequestDTO dto);

    @PostMapping("/login")
    String loginUsuario(@RequestBody LoginRequestDTO dto);

    @GetMapping
    UsuarioResponseDTO buscarUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping
    UsuarioResponseDTO atualizarUsuario(
            @RequestBody UsuarioRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/enderecos")
    EnderecoResponseDTO criarEndereco(
            @RequestBody EnderecoRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/enderecos")
    EnderecoResponseDTO atualizarEndereco(
            @RequestParam("id") Long id,
            @RequestBody EnderecoRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token);

    @PostMapping("/telefones")
    TelefoneResponseDTO criarTelefone(
            @RequestBody TelefoneRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token);

    @PutMapping("/telefones")
    TelefoneResponseDTO atualizarTelefone(
            @RequestParam("id") Long id,
            @RequestBody TelefoneRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token);

    @GetMapping("/enderecos/{cep}")
    ViaCepResponseDTO buscarEnderecoPorCep(@PathVariable("cep") String cep);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(
            @PathVariable("email") String email,
            @RequestHeader(value = "Authorization", required = false) String token);
}
