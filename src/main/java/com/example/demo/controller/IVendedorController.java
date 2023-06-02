package com.example.demo.controller;

import com.example.demo.model.VendedorInput;
import org.springframework.http.ResponseEntity;

public interface IVendedorController {

    ResponseEntity<Void> create(VendedorInput vendedorInput);
}
