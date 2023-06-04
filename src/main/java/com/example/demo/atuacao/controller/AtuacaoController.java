package com.example.demo.atuacao.controller;

import com.example.demo.atuacao.mapper.IAtuacaoMappper;
import com.example.demo.atuacao.model.AtuacaoInput;
import com.example.demo.atuacao.service.IAtuacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "atuacao")
class AtuacaoController implements IAtuacaoController{

    private final IAtuacaoService service;

    AtuacaoController(IAtuacaoService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated AtuacaoInput atuacaoInput) {

        service.create(
                IAtuacaoMappper.INSTANCE.atuacaoInputToAtuacao(atuacaoInput)
        );
        return ResponseEntity.status(CREATED).build();
    }
}
