package com.example.demo.mapper;

import com.example.demo.model.Vendedor;
import com.example.demo.model.VendedorEntity;
import com.example.demo.model.VendedorInput;
import org.mapstruct.Mapper;


@Mapper
public interface IVendedorMapper {

    Vendedor vendedorInputToVendedor(VendedorInput input);

    VendedorEntity vendedorToVendedorEntity(Vendedor vendedor);

    Vendedor vendedorEntityToVendedor(VendedorEntity entity);
}
