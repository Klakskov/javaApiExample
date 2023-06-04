package com.example.demo.atuacao.mapper;

import com.example.demo.atuacao.model.Atuacao;
import com.example.demo.atuacao.model.AtuacaoEntity;
import com.example.demo.atuacao.model.AtuacaoInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface IAtuacaoMappper {

    IAtuacaoMappper INSTANCE = Mappers.getMapper( IAtuacaoMappper.class );

    AtuacaoInput atuacaoToAtuacaoInput(Atuacao atuacao);
    Atuacao atuacaoInputToAtuacao(AtuacaoInput atuacaoInput);
    AtuacaoEntity atuacaoToAtuacaoEntity(Atuacao atuacao);
    Atuacao atuacaoEntityToAtuacao(AtuacaoEntity entity);
}
