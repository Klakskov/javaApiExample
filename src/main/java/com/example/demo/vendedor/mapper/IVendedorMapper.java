package com.example.demo.vendedor.mapper;

import com.example.demo.vendedor.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel="spring")
public interface IVendedorMapper {

    Vendedor vendedorInputToVendedor(VendedorInput input);

    VendedorEntity vendedorToVendedorEntity(Vendedor vendedor);

    Vendedor vendedorEntityToVendedor(VendedorEntity entity);

    VendedorDto vendedorToVendedorDTO(Vendedor byId);

    List<Vendedor> vendedorEntityListtoVendedorList(List<VendedorEntity> all);

    @Mapping(source = "estadosAtuacao", target = "estados")
    VendedorAtuacaoDto vendedorToVendedorAtuacaoDTO(Vendedor ele, List<String> estadosAtuacao);
}
