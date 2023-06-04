package com.example.demo.vendedor.service;

import com.example.demo.atuacao.repository.IAtuacaoRepository;
import com.example.demo.vendedor.mapper.IVendedorMapper;
import com.example.demo.vendedor.model.Vendedor;
import com.example.demo.vendedor.model.VendedorAtuacaoDto;
import com.example.demo.vendedor.model.VendedorDto;
import com.example.demo.vendedor.repository.IVendedorRepository;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class VendedorServiceTeste {

    @Mock
    private IAtuacaoRepository atuacaoRepository;

    @Mock
    private IVendedorRepository vendedorRepository;

    @InjectMocks
    IVendedorMapper INSTANCE = Mappers.getMapper( IVendedorMapper.class );
    @InjectMocks
    private VendedorService vendedorService;


    private Vendedor createVendedor(){
        return Vendedor.builder()
                .idade(1)
                .nome("nome")
                .estado("Sao Paulo")
                .regiao("regiao")
                .telefone("telefone")
                .cidade("Sao Paulo")
                .build();
    }

    @Test
    void testCreation_GivenVendedor_WhenRepositoryStores_ThenReturnVendedorWithDataStored(){
        Vendedor vendedorPosSave = createVendedor();
        long id = 1;
        LocalDate dataCriacao = LocalDate.now().minusDays(15);
        vendedorPosSave.setId(id);
        vendedorPosSave.setDataCriacao(dataCriacao);

        Mockito.when(vendedorRepository.save((ArgumentMatchers.any(Vendedor.class)))).thenReturn(
                vendedorPosSave
        );


        Vendedor vendedorTest = vendedorService.createVendedor(
                createVendedor()
        );

        Assertions.assertEquals(vendedorTest, vendedorPosSave);
    }

    @Test
    void testCreate_GivenVendedor_WhenRepositoryStores_ThenReturnVendedorWithDataStored(){
        Vendedor vendedorPosSave = createVendedor();
        long id = 1;
        LocalDate dataCriacao = LocalDate.now();
        vendedorPosSave.setId(id);
        vendedorPosSave.setDataCriacao(dataCriacao);

        Mockito.when(vendedorRepository.save((ArgumentMatchers.any(Vendedor.class)))).thenReturn(
                vendedorPosSave
        );


        Vendedor vendedorTest = vendedorService.createVendedor(
                createVendedor()
        );

        Assertions.assertEquals(vendedorTest, vendedorPosSave);
    }

    @Test
    void testGet_GivenIdWithVendedorWithAtuacao_WhenGettingById_ThenReturnVendedorWithDataStored(){
        Vendedor vendedorPosSave = createVendedor();
        long id = 1;
        LocalDate dataCriacao = LocalDate.now();
        vendedorPosSave.setId(id);
        vendedorPosSave.setDataCriacao(dataCriacao);

        Mockito.when(vendedorRepository.getById((ArgumentMatchers.any(Long.class)))).thenReturn(
                vendedorPosSave
        );

        List<String> estados = List.of("SP, RJ");
        Mockito.when(atuacaoRepository.getEstadosByRegiao((ArgumentMatchers.any(String.class)))).thenReturn(
                estados
        );

        VendedorDto vendedorTest = vendedorService.getVendedorById(1);

        Assertions.assertNotNull(vendedorTest);
        Assertions.assertFalse(vendedorTest.getEstados() == null
                || vendedorTest.getEstados().isEmpty());

        Assertions.assertEquals(vendedorTest.getNome(), vendedorPosSave.getNome() );
        Assertions.assertEquals(vendedorTest.getDataCriacao(), vendedorPosSave.getDataCriacao() );
        Assertions.assertEquals(vendedorTest.getEstados(), estados );

    }

    @Test
    void testGet_GivenIdWithVendedorWithoutAtuacao_WhenGettingById_ThenReturnVendedorWithDataStored(){
        Vendedor vendedorPosSave = createVendedor();
        long id = 1;
        LocalDate dataCriacao = LocalDate.now();
        vendedorPosSave.setId(id);
        vendedorPosSave.setDataCriacao(dataCriacao);

        Mockito.when(vendedorRepository.getById((ArgumentMatchers.any(Long.class)))).thenReturn(
                vendedorPosSave
        );

        List<String> estados = Collections.emptyList();
        Mockito.when(atuacaoRepository.getEstadosByRegiao((ArgumentMatchers.any(String.class)))).thenReturn(
                estados
        );

        VendedorDto vendedorTest = vendedorService.getVendedorById(1);

        Assertions.assertNotNull(vendedorTest);
        Assertions.assertTrue(vendedorTest.getEstados() != null
                && vendedorTest.getEstados().isEmpty());

        Assertions.assertEquals(vendedorTest.getNome(), vendedorPosSave.getNome() );
        Assertions.assertEquals(vendedorTest.getDataCriacao(), vendedorPosSave.getDataCriacao() );
        Assertions.assertEquals(vendedorTest.getEstados(), estados );

    }

    @Test
    void testGet_GivenGetVendedorAtuacao_WhenGetting_ThenReturnVendedorAtuacaoWithDataStored(){
        Vendedor vendedorPosSave = createVendedor();
        LocalDate dataCriacao = LocalDate.now();
        vendedorPosSave.setId(1);
        vendedorPosSave.setDataCriacao(dataCriacao);

        Vendedor vendedorPosSave2 = createVendedor();
        vendedorPosSave2.setId(1);
        vendedorPosSave2.setDataCriacao(dataCriacao);
        List<Vendedor> vendedorList = List.of(vendedorPosSave, vendedorPosSave2);

        Mockito.when(vendedorRepository.getAll()).thenReturn(
                vendedorList
        );

        List<String> estados = List.of("SP", "RJ");
        Mockito.when(atuacaoRepository.getEstadosByRegiao((ArgumentMatchers.any(String.class)))).thenReturn(
                estados
        );


        List<VendedorAtuacaoDto> vendedorAtuacaoList = vendedorService.getVendedorAtuacaoDto();

        Assertions.assertNotNull(vendedorAtuacaoList);
        Assertions.assertFalse(vendedorAtuacaoList.isEmpty());

        Assertions.assertEquals( vendedorList.size(), vendedorAtuacaoList.size());
        Assertions.assertNotNull(vendedorAtuacaoList.get(0).getEstados());

        List<String> estadosList = vendedorAtuacaoList.get(0).getEstados();
        Assertions.assertFalse(estadosList.isEmpty());
        Assertions.assertEquals(estados, estadosList);


    }

}
