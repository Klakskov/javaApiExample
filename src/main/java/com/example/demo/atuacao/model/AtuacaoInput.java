package com.example.demo.atuacao.model;

import com.sun.istack.NotNull;
import lombok.*;

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
