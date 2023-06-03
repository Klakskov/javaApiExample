package com.example.demo.model;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VendedorInput {

    private String nome;
    private String telefone;
    private int idade;
    private String cidade;
    private String estado;
    private String regiao;

}
