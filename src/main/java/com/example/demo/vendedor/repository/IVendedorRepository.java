package com.example.demo.vendedor.repository;

import com.example.demo.vendedor.model.Vendedor;

import java.util.List;

public interface IVendedorRepository {

    Vendedor save(Vendedor vendedor);

    Vendedor getById(long id);

    List<Vendedor> getAll();
}
