package com.example.demo.repository;/*
package com.example.demo.repository;

import com.example.demo.mapper.IVendedorMapper;
import com.example.demo.model.Vendedor;
import com.example.demo.model.VendedorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class VendedorRepository implements IVendedorRepository {

    @Autowired
    private final SpringDataVendedorRepository repository;
    @Autowired
    private final IVendedorMapper mapper;

    public VendedorRepository(SpringDataVendedorRepository repository, IVendedorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Vendedor create(Vendedor vendedor) {
        VendedorEntity entity = mapper.vendedorToVendedorEntity(vendedor);
        entity = repository.save(entity);
        return mapper.vendedorEntityToVendedor(entity);
    }
}
*/
