package com.example.demo.vendedor.model;


import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vendedor {

    private long id;
    private String nome;
    private String telefone;
    private int idade;
    private String cidade;
    private String estado;
    private String regiao;
    private LocalDate dataCriacao;


}
