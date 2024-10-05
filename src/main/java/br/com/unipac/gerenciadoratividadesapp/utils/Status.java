package br.com.unipac.gerenciadoratividadesapp.utils;

public enum Status {

    BLOQUEADA("Bloqueada"),
    EM_ANDAMENTO("Em Andamento"),
    EM_ANALISE("Em Análise"),
    RESOLVIDA("Resolvida"),
    PENDENTE("Pendente"),
    AGUARDANDO_APROVACAO("Aguardando Aprovação"),
    REABERTA("Reaberta"),
    NOVA("Nova");

    private final String label;

    Status(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

