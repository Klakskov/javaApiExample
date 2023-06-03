package com.example.demo.vendedor.model;
import com.sun.istack.NotNull;
import lombok.*;

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
    private int idade;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    @NotNull
    private String regiao;

}
