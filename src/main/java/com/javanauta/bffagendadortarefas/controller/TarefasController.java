package com.javanauta.bffagendadortarefas.controller;



import com.javanauta.bffagendadortarefas.business.TarefasService;
import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.config.SecurityConfig;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusTarefa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name= "Agendador de Tarefas", description = "Administra tarefas cadastradas por Usuários")
@SecurityRequirement(name= SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Salvar Tarefa", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Tarefa já cadastrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> salvaTarefa(@RequestBody TarefasDTORequest tarefasDTO,
                                                          @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.salvaTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Buscar tarefas", description = "Busca tarefas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "400", description = "Tarefas não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorPeriodo(
                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Buscar tarefas", description = "Busca tarefas por email de Usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "400", description = "Tarefas não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmailDeUsuario(@RequestHeader(name = "Authorization", required = false) String token){
        return  ResponseEntity.ok(tarefasService.buscaTarefasPorEmailUsuario(token));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Tarefas", description = "Deleta tarefas pelo id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada")
    @ApiResponse(responseCode = "400", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefa(@PathVariable String id, @RequestHeader(name = "Authorization", required = false) String token){
        tarefasService.deletaTarefaPorID(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Alterar status da Tarefa", description = "Altera o status da Tarefa")
    @ApiResponse(responseCode = "200", description = "Alteração de status concluída")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusDaTarefa(@RequestParam("status") StatusTarefa statusTarefa,
                                                                   @RequestParam("id") String id,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alteraStatus(statusTarefa, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualizar Tarefas", description = "Alterações nos dados da Tarefa")
    @ApiResponse(responseCode = "200", description = "Alterações concluídas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> atualizaTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                                                              @RequestParam("id") String id,
                                                              @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.updateTarefas(tarefasDTO, id, token));
    }

}
