package com.example.demo.atuacao.service;

import com.example.demo.atuacao.mapper.IAtuacaoMappper;
import com.example.demo.atuacao.model.Atuacao;
import com.example.demo.atuacao.repository.IAtuacaoRepository;
import com.example.demo.exceptions.model.ValidationRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class AtuacaoService implements IAtuacaoService {

    private final IAtuacaoMappper mappper;
    private final IAtuacaoRepository repository;

    AtuacaoService(IAtuacaoMappper mappper, IAtuacaoRepository repository) {
        this.mappper = mappper;
        this.repository = repository;
    }

    @Override
    public void create(Atuacao atuacao) {
        validateAtuacaoCreation(atuacao);
        repository.save(atuacao);
    }

    @Override
    public Atuacao getByRegiao(String regiao) {
        return repository.getByRegiao(regiao);
    }

    private void validateAtuacaoCreation(Atuacao atuacao) {
        log.info("validating atuacao for creation ... ");
        if(atuacao.getEstados() == null || atuacao.getEstados().size() == 0){
            log.warn("atuacao with empty estados ...");
            throw new ValidationRequestException("O campo Estado esta vazio ou nulo",
                    "O campo Estado esta vazio ou nulo" );
        }
    }
}
