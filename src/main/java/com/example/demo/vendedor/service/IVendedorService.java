package com.example.demo.vendedor.service;

import com.example.demo.vendedor.model.Vendedor;
import com.example.demo.vendedor.model.VendedorAtuacaoDto;
import com.example.demo.vendedor.model.VendedorDto;

import java.util.List;

public interface IVendedorService {

    Vendedor createVendedor(Vendedor vendedorInput);

    VendedorDto getVendedorById(long id);

    List<VendedorAtuacaoDto> getVendedorAtuacaoDto();
}
