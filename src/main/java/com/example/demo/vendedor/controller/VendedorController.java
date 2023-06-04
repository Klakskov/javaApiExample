package com.example.demo.vendedor.controller;

import com.example.demo.vendedor.mapper.IVendedorMapper;
import com.example.demo.vendedor.model.VendedorAtuacaoDto;
import com.example.demo.vendedor.model.VendedorDto;
import com.example.demo.vendedor.model.VendedorInput;
import com.example.demo.vendedor.service.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "vendedor")
class VendedorController implements IVendedorController {


    @Autowired
    private final IVendedorService vendedorService;

    public VendedorController(IVendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid VendedorInput vendedorInput) {
        vendedorService.createVendedor(
                IVendedorMapper.INSTANCE.vendedorInputToVendedor(vendedorInput)
        );
        return ResponseEntity.status(CREATED).build();

    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<VendedorDto> getById(@PathVariable("id") Long id) {
        VendedorDto dto = vendedorService.getVendedorById(id);
        if(dto == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dto);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<VendedorAtuacaoDto> > getVendedorAtuacao() {
        List<VendedorAtuacaoDto> dto = vendedorService.getVendedorAtuacaoDto();
        if(dto == null || dto.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(dto);
    }
}
