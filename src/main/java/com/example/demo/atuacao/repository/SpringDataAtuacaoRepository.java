package com.example.demo.atuacao.repository;

import com.example.demo.atuacao.model.AtuacaoEntity;
import com.example.demo.vendedor.model.VendedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SpringDataAtuacaoRepository extends JpaRepository<AtuacaoEntity, Long> {


    AtuacaoEntity getByRegiao(String regiao);
}