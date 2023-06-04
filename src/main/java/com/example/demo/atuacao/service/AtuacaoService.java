package com.example.demo.atuacao.service;

import com.example.demo.atuacao.model.Atuacao;
import com.example.demo.atuacao.repository.IAtuacaoRepository;
import com.example.demo.exceptions.model.ValidationRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
class AtuacaoService implements IAtuacaoService {

    private final IAtuacaoRepository repository;

    AtuacaoService(IAtuacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Atuacao create(Atuacao atuacao) {
        validateAtuacaoCreation(atuacao);
        return repository.save(atuacao);
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
