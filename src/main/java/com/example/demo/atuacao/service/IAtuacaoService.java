package com.example.demo.atuacao.service;

import com.example.demo.atuacao.model.Atuacao;

public interface IAtuacaoService {

    void create(Atuacao atuacao);

    Atuacao getByRegiao(String regiao);
}
