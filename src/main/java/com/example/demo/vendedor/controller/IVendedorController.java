package com.example.demo.vendedor.controller;

import com.example.demo.vendedor.model.VendedorAtuacaoDto;
import com.example.demo.vendedor.model.VendedorDto;
import com.example.demo.vendedor.model.VendedorInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface IVendedorController {

    ResponseEntity<Void> create(VendedorInput vendedorInput);

    ResponseEntity<VendedorDto> getById(Long id);


    @GetMapping()
    ResponseEntity<List<VendedorAtuacaoDto>> getVendedorAtuacao();
}
