package com.example.demo.controller;

import com.example.demo.model.VendedorInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("vendedor")
public class VendedorController implements IVendedorController {


    @Override
    @PostMapping
    public ResponseEntity<Void> create(VendedorInput vendedorInput) {

        return ResponseEntity.status(CREATED).build();

    }
}
