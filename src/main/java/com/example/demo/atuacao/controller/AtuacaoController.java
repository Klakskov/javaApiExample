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
@RequestMapping("atuacao")
class AtuacaoController implements IAtuacaoController{

    private final IAtuacaoMappper mappper;
    private final IAtuacaoService service;

    AtuacaoController(IAtuacaoMappper mappper, IAtuacaoService service) {
        this.mappper = mappper;
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Validated AtuacaoInput atuacaoInput) {

        service.create(
                mappper.atuacaoInputToAtuacao(atuacaoInput)
        );
        return ResponseEntity.status(CREATED).build();
    }
}
