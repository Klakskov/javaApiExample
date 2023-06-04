package com.example.demo.atuacao.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Atuacao {
    private long id;
    private String regiao;
    private List<String> estados;
}
