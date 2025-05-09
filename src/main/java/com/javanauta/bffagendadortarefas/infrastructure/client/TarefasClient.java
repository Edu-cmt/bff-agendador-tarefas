package com.javanauta.bffagendadortarefas.infrastructure.client;

import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusTarefa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {


    @PostMapping
    TarefasDTOResponse salvaTarefa(@RequestBody TarefasDTORequest tarefasDTO,
                                   @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmailDeUsuario(@RequestHeader("Authorization") String token);

    @DeleteMapping("/{id}")
    void deletaTarefa(@PathVariable String id,
                      @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusDaTarefa(@RequestParam("status") StatusTarefa statusTarefa,
                                            @RequestParam("id") String id,
                                            @RequestHeader("Authorization") String token);
    @PutMapping
    TarefasDTOResponse atualizaTarefas(@RequestBody TarefasDTORequest tarefasDTO,
                                       @RequestParam("id") String id,
                                       @RequestHeader("Authorization") String token);

}
