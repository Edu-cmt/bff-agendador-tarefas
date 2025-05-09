package com.javanauta.bffagendadortarefas.infrastructure.client;


import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;


@FeignClient(name = "notificador", url = "${notificador.url}")
public interface NotificadorClient {

    /*Aqui é utilizado o TarefasDTOResponse porque nesse objeto contém o atributo emailUsuario que precisamos para o metodo
    enviaEmail() funcionar */
    @PostMapping
    void enviaEmail(@RequestBody TarefasDTOResponse tarefasDTO);
}
