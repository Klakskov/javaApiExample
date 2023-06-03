package com.example.demo.exceptions.model;

public enum ProblemType {
    PARAMETRO_INVALIDO("Parâmetro inválido."),
    DADOS_INVALIDOS("Dados inválidos."),
    ERRO_DE_SISTEMA("Erro de sistema."),
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado."),
    ERRO_COMUNICACAO_SISTEMA_EXTERNO("Erro ao se comunicar com o sistema externo."),

    ERRO_NEGOCIO("Violação de regra de negócio.");

    private String title;

    private ProblemType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
