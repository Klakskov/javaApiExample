package com.example.demo.vendedor.model;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendedorInput {

    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @NotNull
    @Positive
    private Integer idade;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    @NotNull
    private String regiao;

}
