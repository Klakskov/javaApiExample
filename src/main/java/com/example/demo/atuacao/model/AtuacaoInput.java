package com.example.demo.atuacao.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AtuacaoInput {
    @NotNull
    private String regiao;
    @NotNull
    private List<String> estados;
}
