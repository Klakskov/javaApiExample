package com.example.demo.atuacao.repository;

import com.example.demo.atuacao.model.Atuacao;

import java.util.List;

public interface IAtuacaoRepository {

    Atuacao save(Atuacao atuacao);

    Atuacao getByRegiao(String regiao);
    List<String> getEstadosByRegiao(String regiao);


}
