package com.example.demo.atuacao.controller;

import com.example.demo.atuacao.model.AtuacaoInput;
import org.springframework.http.ResponseEntity;

public interface IAtuacaoController {

    ResponseEntity<Void> create(AtuacaoInput atuacaoInput);

}
