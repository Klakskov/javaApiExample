package com.example.demo.atuacao.repository;

import com.example.demo.atuacao.mapper.IAtuacaoMappper;
import com.example.demo.atuacao.model.Atuacao;
import com.example.demo.atuacao.model.AtuacaoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class AtuacaoRepository implements IAtuacaoRepository{

    private final IAtuacaoMappper mappper;
    private final SpringDataAtuacaoRepository repository;

    public AtuacaoRepository(IAtuacaoMappper mappper, SpringDataAtuacaoRepository repository) {
        this.mappper = mappper;
        this.repository = repository;
    }

    @Override
    public Atuacao save(Atuacao atuacao) {
        log.info("creating atuacao {} ... ", atuacao);
        AtuacaoEntity entity = mappper.atuacaoToAtuacaoEntity(atuacao);
        entity = repository.save(entity);
        log.info("atuacao created with id {} ...", entity.getId());

        return mappper.atuacaoEntityToAtuacao(entity);
    }

    @Override
    public Atuacao getByRegiao(String regiao) {
        log.info("getting atuacao by regiao {} ...", regiao);

        return mappper.atuacaoEntityToAtuacao(
                repository.getByRegiao(regiao)
        );
    }

    @Override
    public List<String> getEstadosByRegiao(String regiao) {
        log.info("getting estados atuacao by regiao {} ...", regiao);
        AtuacaoEntity entity = repository.getByRegiao(regiao);
        return entity != null ? entity.getEstados() : Collections.emptyList();
    }
}
