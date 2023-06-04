package com.example.demo.atuacao.repository;

import com.example.demo.atuacao.mapper.IAtuacaoMappper;
import com.example.demo.atuacao.model.Atuacao;
import com.example.demo.atuacao.model.AtuacaoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@Slf4j
class AtuacaoRepository implements IAtuacaoRepository{

    @Autowired
    private final SpringDataAtuacaoRepository repository;

    public AtuacaoRepository(SpringDataAtuacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Atuacao save(Atuacao atuacao) {
        log.info("creating atuacao {} ... ", atuacao);
        AtuacaoEntity entity = IAtuacaoMappper.INSTANCE.atuacaoToAtuacaoEntity(atuacao);
        entity = repository.save(entity);
        log.info("atuacao created with id {} ...", entity.getId());

        return IAtuacaoMappper.INSTANCE.atuacaoEntityToAtuacao(entity);
    }

    @Override
    public Atuacao getByRegiao(String regiao) {
        log.info("getting atuacao by regiao {} ...", regiao);

        return IAtuacaoMappper.INSTANCE.atuacaoEntityToAtuacao(
                repository.getByRegiao(regiao)
        );
    }

    @Override
    public List<String> getEstadosByRegiao(String regiao) {
        log.info("getting estados atuacao by regiao {} ...", regiao);
        AtuacaoEntity entity = repository.getByRegiao(regiao);

        if(entity == null ){
            log.info("estados no found for region {} returning empty list ...",
                    regiao);
            return Collections.emptyList();
        }
        return entity.getEstados() ;
    }
}
