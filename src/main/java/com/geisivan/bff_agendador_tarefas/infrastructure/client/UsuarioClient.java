package com.geisivan.bff_agendador_tarefas.infrastructure.client;

import com.geisivan.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.in.EnderecoDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.LoginDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.TelefoneDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.UsuarioDTOIn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTO salvaUsuario(@RequestBody UsuarioDTOIn dto);

    @PostMapping("/login")
    String login(@RequestBody LoginDTOIn dto);

    @GetMapping("/usuario")
    UsuarioDTO buscaUsuarioPorEmail(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deletaUsuarioPorEmail(@PathVariable("email") String email,
                              @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTO atualizaDadosUsuario(@RequestBody UsuarioDTOIn dto,
                                   @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTO cadastraEndereco(@RequestBody EnderecoDTOIn dto,
                                 @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTO atualizaDadosEndereco(@RequestBody EnderecoDTOIn dto,
                                     @RequestParam("id") Long id,
                                     @RequestHeader("Authorization") String token);


    @PostMapping("/telefone")
    TelefoneDTO cadastraTelefone(@RequestBody TelefoneDTOIn dto,
                                 @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTO atualizaDadosTelefone(@RequestBody TelefoneDTOIn dto,
                                     @RequestParam("id") Long id,
                                     @RequestHeader("Authorization") String token);




}
