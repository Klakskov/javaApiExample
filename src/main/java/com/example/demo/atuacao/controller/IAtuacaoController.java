package com.example.demo.atuacao.controller;

import com.example.demo.atuacao.model.AtuacaoInput;
import com.example.demo.vendedor.model.VendedorInput;
import org.springframework.http.ResponseEntity;

public interface IAtuacaoController {

    ResponseEntity<Void> create(AtuacaoInput atuacaoInput);

}
