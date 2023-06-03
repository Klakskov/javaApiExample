
package com.example.demo.vendedor.service;


import com.example.demo.atuacao.repository.IAtuacaoRepository;
import com.example.demo.vendedor.mapper.IVendedorMapper;
import com.example.demo.vendedor.model.Vendedor;
import com.example.demo.vendedor.model.VendedorAtuacaoDto;
import com.example.demo.vendedor.model.VendedorDto;
import com.example.demo.vendedor.repository.IVendedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
class VendedorService implements IVendedorService {

    @Autowired
    private final IVendedorMapper mapper;

    @Autowired
    private final IVendedorRepository vendedorRepository;

    @Autowired
    private final IAtuacaoRepository atuacaoRepository;

    public VendedorService(IVendedorMapper mapper,
                           IVendedorRepository vendedorRepository,
                           IAtuacaoRepository atuacaoRepository) {
        this.mapper = mapper;
        this.vendedorRepository = vendedorRepository;
        this.atuacaoRepository = atuacaoRepository;
    }

    @Override
    public void createVendedor(Vendedor vendedor) {
        log.info("creating vendedor {} ..." , vendedor);
        vendedorRepository.save(vendedor);

    }

    @Override
    public VendedorDto getVendedorById(long id) {
        return
                mapper.vendedorToVendedorDTO(
                        vendedorRepository.getById(id)
                );
    }


    @Override
    public List<VendedorAtuacaoDto> getVendedorAtuacaoDto(){
        List<Vendedor> vendedorList = vendedorRepository.getAll();

        return vendedorList.stream()
                .map(
                        ele -> {
                            List<String> estadosAtuacao = atuacaoRepository.getEstadosByRegiao(ele.getRegiao());
                            return mapper.vendedorToVendedorAtuacaoDTO(ele, estadosAtuacao);
                        }
                ).collect(Collectors.toList());
    }
}


