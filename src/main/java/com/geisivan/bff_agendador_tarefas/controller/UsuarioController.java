package com.geisivan.bff_agendador_tarefas.controller;

import com.geisivan.bff_agendador_tarefas.business.UsuarioService;
import com.geisivan.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.in.EnderecoDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.LoginDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.TelefoneDTOIn;
import com.geisivan.bff_agendador_tarefas.business.dto.in.UsuarioDTOIn;
import com.geisivan.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(
        name = "Usuarios",
        description = "Operações relacionadas ao gerenciamento de usuários"
)
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário",
            description = "Realiza a autenticação do usuário e retorna um token de acesso")
    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public String login(@RequestBody LoginDTOIn dto){
        return usuarioService.loginUsuario(dto);
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTOIn dto){
        return ResponseEntity.ok(usuarioService.salvaUsuario(dto));
    }

    @GetMapping
    @Operation(summary = "Buscar usuário por email",
            description = "Retorna os dados do usuário a partir do e-mail informado")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTO> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                           @RequestHeader(value = "Authorization",
                                                           required = false) String token){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email, token));
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do usuário",
            description = "Atualiza as informações cadastrais do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<UsuarioDTO> atualizaDadosUsuario(@RequestBody UsuarioDTOIn dto,
                                                           @RequestHeader(value = "Authorization",
                                                           required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(dto, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Excluir usuário",
            description = "Remove um usuário do sistema a partir do e-mail informado")
    @ApiResponse(responseCode = "200", description = "Usuário excluído com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable("email") String email,
                                                      @RequestHeader(value = "Authorization",
                                                      required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/endereco")
    @Operation(summary = "Cadastrar endereço do usuário",
            description = "Adiciona um novo endereço ao usuário autenticado")
    @ApiResponse(responseCode = "200", description = "Endereço cadastrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EnderecoDTO> cadastraEndereco(@RequestBody EnderecoDTOIn dto,
                                                        @RequestHeader(value = "Authorization",
                                                        required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(dto, token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço do usuário",
            description = "Atualiza os dados de um endereço já cadastrado do usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<EnderecoDTO> atualizaDadosEndereco(@RequestBody EnderecoDTOIn dto,
                                                             @RequestParam("id") Long id,
                                                             @RequestHeader(value = "Authorization",
                                                             required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosEndereco(dto, id, token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Cadastrar telefone do usuário",
            description = "Adiciona um novo telefone ao usuário autenticado")
    @ApiResponse(responseCode = "200", description = "Telefone cadastrado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TelefoneDTO> cadastraTelefone(@RequestBody TelefoneDTOIn dto,
                                                        @RequestHeader(value = "Authorization",
                                                        required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone do usuário",
            description = "Atualiza os dados de um telefone já cadastrado do usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    public ResponseEntity<TelefoneDTO> atualizaDadosTelefone(@RequestBody TelefoneDTOIn dto,
                                                             @RequestParam("id") Long id,
                                                             @RequestHeader(value = "Authorization",
                                                             required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosTelefone(dto, id, token));
    }
}
