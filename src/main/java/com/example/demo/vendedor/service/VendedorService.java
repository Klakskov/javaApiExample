
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
class VendedorService implements IVendedorService {

    @Autowired
    private final IVendedorRepository vendedorRepository;

    @Autowired
    private final IAtuacaoRepository atuacaoRepository;

    public VendedorService(IVendedorRepository vendedorRepository,
                           IAtuacaoRepository atuacaoRepository) {
        this.vendedorRepository = vendedorRepository;
        this.atuacaoRepository = atuacaoRepository;
    }

    @Override
    public Vendedor createVendedor(Vendedor vendedor) {
        log.info("creating vendedor {} ..." , vendedor);
        return vendedorRepository.save(vendedor);

    }

    @Override
    public VendedorDto getVendedorById(long id) {
        Vendedor vendedor = vendedorRepository.getById(id);

        if(vendedor == null) {
            log.info("vendedor nao encontrado para id {}", id);
            return null;
        }
        List<String> estados  = atuacaoRepository.getEstadosByRegiao(vendedor.getRegiao());

        return
                IVendedorMapper.INSTANCE.vendedorToVendedorDTO(
                        vendedor, estados
                );
    }


    @Override
    public List<VendedorAtuacaoDto> getVendedorAtuacaoDto(){
        List<Vendedor> vendedorList = vendedorRepository.getAll();

        return vendedorList.stream()
                .map(
                        ele -> {
                            List<String> estadosAtuacao = atuacaoRepository.getEstadosByRegiao(ele.getRegiao());
                            return IVendedorMapper.INSTANCE.vendedorToVendedorAtuacaoDTO(ele, estadosAtuacao);
                        }
                ).collect(Collectors.toList());
    }
}


