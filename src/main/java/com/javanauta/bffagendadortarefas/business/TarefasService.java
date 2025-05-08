package com.javanauta.bffagendadortarefas.business;

import com.javanauta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.TarefasClient;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusTarefa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse salvaTarefa(String token, TarefasDTORequest tarefasDTO) {
        return tarefasClient.salvaTarefa(tarefasDTO, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                    LocalDateTime dataFinal, String token) {
        return tarefasClient.buscaTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmailUsuario(String token) {
        return tarefasClient.buscaTarefasPorEmailDeUsuario(token);
    }

    public void deletaTarefaPorID(String id, String token) {
        tarefasClient.deletaTarefa(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusTarefa statusTarefa, String id, String token) {
        return tarefasClient.alteraStatusDaTarefa(statusTarefa, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest tarefasDTO, String id, String token){
        return tarefasClient.atualizaTarefas(tarefasDTO, id, token);
    }
}
