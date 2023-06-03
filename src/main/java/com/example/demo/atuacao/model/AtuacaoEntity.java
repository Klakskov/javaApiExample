package com.example.demo.atuacao.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "atuacao")
public class AtuacaoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String regiao;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "Estados", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "estados", nullable = false)
    private List<String> estados;
}
