package com.javanauta.bffagendadortarefas.business;


import com.javanauta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanauta.bffagendadortarefas.infrastructure.client.NotificadorClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final NotificadorClient notificadorClient;

    /*Aqui é utilizado o TarefasDTOResponse porque nesse objeto contém o atributo emailUsuario que precisamos para o metodo
    enviaEmail() funcionar */
    public void enviaEmail(TarefasDTOResponse tarefasDTO){
        notificadorClient.enviaEmail(tarefasDTO);
    }
}
