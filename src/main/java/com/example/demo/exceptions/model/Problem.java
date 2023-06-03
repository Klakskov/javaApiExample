package com.example.demo.exceptions.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Problem {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "Dados inválidos")
    private String title;

    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String detail;

    /* details/extensão/complemento */
    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String userMessage;

    @Schema(example = "2019-12-01T18:09:02.3314401") //, position = 20)
    private LocalDateTime timestamp;

    @Schema(description = "ObjetoProblema")
    @Getter
    @Builder
    public static class Object {

        @Schema(example = "preço")
        private String name;

        @Schema(example = "O preço é obrigatório")
        private String userMessage;

    }

}
