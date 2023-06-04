package com.example.demo.vendedor.repository;

import com.example.demo.vendedor.mapper.IVendedorMapper;
import com.example.demo.vendedor.model.Vendedor;
import com.example.demo.vendedor.model.VendedorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
class VendedorRepository implements IVendedorRepository {

    @Autowired
    private final SpringDataVendedorRepository repository;

    public VendedorRepository(SpringDataVendedorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vendedor save(Vendedor vendedor) {
        log.info("creating vendedor {} ", vendedor);
        VendedorEntity entity = IVendedorMapper.INSTANCE.vendedorToVendedorEntity(vendedor);
        entity = repository.save(entity);
        log.info("vendedor created with id {} ", entity.getId());
        return IVendedorMapper.INSTANCE.vendedorEntityToVendedor(entity);
    }

    @Override
    public Vendedor getById(long id) {
        log.info("getting vendedor by id {} ...", id);
        VendedorEntity entity = repository.findById(id)
                .orElse(null);
        log.info("returned vendedor {} ", entity);
        return IVendedorMapper.INSTANCE.vendedorEntityToVendedor(
                entity
        );
    }

    @Override
    public List<Vendedor> getAll() {
        return
                IVendedorMapper.INSTANCE.vendedorEntityListtoVendedorList(
                        repository.findAll()
                );
    }
}


