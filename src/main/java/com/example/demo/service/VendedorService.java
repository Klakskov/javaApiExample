package com.example.demo.service;/*
package com.example.demo.service;

import com.example.demo.mapper.IVendedorMapper;
import com.example.demo.model.Vendedor;
import com.example.demo.repository.SpringDataVendedorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VendedorService implements IVendedorService{

    @Autowired
    private final IVendedorMapper mapper;

    @Autowired
    private final SpringDataVendedorRepository vendedorRepository;

    public VendedorService(IVendedorMapper mapper, SpringDataVendedorRepository vendedorRepository) {
        this.mapper = mapper;
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public void createVendedor(Vendedor vendedor) {
        log.info("creating vendedor {} ", vendedor);
        vendedorRepository.save(mapper.vendedorToVendedorEntity(vendedor));
        log.info("all done!");
    }
}
*/
