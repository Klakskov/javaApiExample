package com.example.demo.vendedor.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vendedor")
@ToString
public class VendedorEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String telefone;
    private int idade;
    private String cidade;
    private String estado;
    private String regiao;

    @Column(name = "data_criacao")
    @CreationTimestamp
    private LocalDate dataCriacao;


}
