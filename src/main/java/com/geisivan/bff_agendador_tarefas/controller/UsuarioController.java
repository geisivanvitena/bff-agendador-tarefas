package com.geisivan.bff_agendador_tarefas.controller;

import com.geisivan.bff_agendador_tarefas.business.service.UsuarioService;
import com.geisivan.bff_agendador_tarefas.business.dto.request.EnderecoRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.request.LoginRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.request.TelefoneRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.request.UsuarioRequestDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.EnderecoResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.TelefoneResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.UsuarioResponseDTO;
import com.geisivan.bff_agendador_tarefas.business.dto.response.ViaCepResponseDTO;
import com.geisivan.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(
        name = "Usuarios",
        description = "Operações relacionadas ao gerenciamento de usuários"
)
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Criar usuário",
               description = "Cria um novo usuário no sistema."
    )
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso.")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.criarUsuario(dto));
    }

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário",
               description = "Realiza a autenticação do usuário e retorna um token JWT."
    )
    @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Email ou senha inválidos.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public String loginUsuario(@RequestBody LoginRequestDTO dto){
        return usuarioService.loginUsuario(dto);
    }

    @GetMapping
    @Operation(summary = "Buscar usuário por email",
               description = "Retorna os dados do usuário a partir do e-mail informado."
    )
    @ApiResponse(responseCode = "200", description = "Usuário encontrado.")
    @ApiResponse(responseCode = "401", description = "Token inválido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(
            @RequestParam("email") String email,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @PutMapping
    @Operation(summary = "Atualizar usuário",
               description = "Atualiza os dados do usuário autenticado."
    )
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token inválido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(
            @RequestBody UsuarioRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(dto, token));
    }

    @PostMapping("/enderecos")
    @Operation(summary = "Criar endereço",
               description = "Adiciona um endereço ao usuário autenticado."
    )
    @ApiResponse(responseCode = "201", description = "Endereço cadastrado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token inválido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<EnderecoResponseDTO> criarEndereco(
            @RequestBody EnderecoRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.criarEndereco(dto, token));
    }

    @PutMapping("/enderecos")
    @Operation(summary = "Atualizar endereço",
               description = "Atualiza o endereço do usuário autenticado com base no ID informado."
    )
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token inválido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<EnderecoResponseDTO> atualizarEndereco(
            @RequestParam("id") Long id,
            @RequestBody EnderecoRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(id, dto, token));
    }

    @PostMapping("/telefones")
    @Operation(summary = "Criar telefone",
               description = "Adiciona um telefone ao usuário autenticado."
    )
    @ApiResponse(responseCode = "201", description = "Telefone cadastrado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token inválido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Telefone não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<TelefoneResponseDTO> criarTelefone(
            @RequestBody TelefoneRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.criarTelefone(dto, token));
    }

    @PutMapping("/telefones")
    @Operation(summary = "Atualizar telefone",
               description = "Atualiza o telefone do usuário autenticado com base no ID informado."
    )
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token inválido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Telefone não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<TelefoneResponseDTO> atualizarTelefone(
            @RequestParam("id") Long id,
            @RequestBody TelefoneRequestDTO dto,
            @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(id, dto, token));
    }

    @GetMapping("/enderecos/{cep}")
    @Operation(summary = "Buscar endereço por CEP",
               description = "Retorna os dados do endereço com base no CEP informado."
    )
    @ApiResponse(responseCode = "200", description = "Endereço localizado com sucesso.")
    @ApiResponse(responseCode = "404", description = "Endereço não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<ViaCepResponseDTO> buscarEnderecoPorCep(@PathVariable String cep){
        return ResponseEntity.ok(usuarioService.buscarEnderecoPorCep(cep));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Excluir usuário",
               description = "Remove um usuário do sistema com base no e-mail informado."
    )
    @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso.")
    @ApiResponse(responseCode = "401", description = "Token inválido ou ausente.")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    public ResponseEntity<Void> deletarUsuarioPorEmail(
            @PathVariable String email,
            @RequestHeader(value = "Authorization", required = false) String token){
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.noContent().build();
    }
}
