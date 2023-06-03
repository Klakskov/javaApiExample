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
public class VendedorDto {

    private String nome;

    @JsonProperty("dataInclusao")
    private LocalDate dataCriacao;

    private List<String> estados;

}
