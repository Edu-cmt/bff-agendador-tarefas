package com.javanauta.bffagendadortarefas.business.dto.out;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.bffagendadortarefas.infrastructure.enums.StatusTarefa;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTOResponse {

    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAlteracao;
    private StatusTarefa statusTarefa;
}
