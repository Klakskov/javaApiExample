package com.example.demo.vendedor.mapper;

import com.example.demo.vendedor.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel="spring")
public interface IVendedorMapper {

    IVendedorMapper INSTANCE = Mappers.getMapper( IVendedorMapper.class );
    Vendedor vendedorInputToVendedor(VendedorInput input);

    VendedorEntity vendedorToVendedorEntity(Vendedor vendedor);

    Vendedor vendedorEntityToVendedor(VendedorEntity entity);

    @Mapping(source = "estados", target = "estados")
    VendedorDto vendedorToVendedorDTO(Vendedor byId, List<String> estados);

    List<Vendedor> vendedorEntityListtoVendedorList(List<VendedorEntity> all);

    @Mapping(source = "estadosAtuacao", target = "estados")
    VendedorAtuacaoDto vendedorToVendedorAtuacaoDTO(Vendedor ele, List<String> estadosAtuacao);
}
