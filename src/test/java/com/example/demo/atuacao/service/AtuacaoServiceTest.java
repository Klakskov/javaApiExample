package com.example.demo.atuacao.service;

import com.example.demo.atuacao.mapper.IAtuacaoMappper;
import com.example.demo.atuacao.model.Atuacao;
import com.example.demo.atuacao.repository.IAtuacaoRepository;
import com.example.demo.exceptions.model.ValidationRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class AtuacaoServiceTest {

    @Mock
    private IAtuacaoRepository atuacaoRepository;

    @InjectMocks
    IAtuacaoMappper INSTANCE = Mappers.getMapper(IAtuacaoMappper.class);
    @InjectMocks
    private AtuacaoService atuacaoService;


    private Atuacao createAtuacao() {
        return Atuacao.builder()
                .estados(List.of("SP", "RJ"))
                .regiao("Sudeste")
                .build();
    }

    @Test
    void testCreation_GivenAtuacao_WhenRepositoryStores_ThenReturnAtuacaoWithDataStored() {
        Atuacao atuacaoPosSave = createAtuacao();
        long id = 1;
        atuacaoPosSave.setId(id);

        Mockito.when(
                atuacaoRepository.save(
                                (ArgumentMatchers.any(Atuacao.class))))
                .thenReturn(
                        atuacaoPosSave
                );


        Atuacao atuacaoTest = atuacaoService.create(
                createAtuacao()
        );

        Assertions.assertEquals(atuacaoPosSave, atuacaoTest);
    }

    @Test
    void testCreation_GivenAtuacao_WhenDoNotHaveEstados_ThenThrowValidationRequestException() {
        Atuacao atuacao = createAtuacao();
        atuacao.setEstados(null);
        assertThrows(ValidationRequestException.class,
                () -> atuacaoService.create(atuacao)
        );

    }

}
