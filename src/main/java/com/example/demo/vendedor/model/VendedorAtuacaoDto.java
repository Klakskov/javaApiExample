package com.example.demo.vendedor.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VendedorAtuacaoDto {

    private String nome;
    private String telefone;
    private int idade;
    private String cidade;
    private String estado;
    private List<String> estados;

}
