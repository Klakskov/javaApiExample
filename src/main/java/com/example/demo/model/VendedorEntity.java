package com.example.demo.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

/*@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vendedor")*/
public class VendedorEntity {

    @Id
    @Column(name = "id")
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
