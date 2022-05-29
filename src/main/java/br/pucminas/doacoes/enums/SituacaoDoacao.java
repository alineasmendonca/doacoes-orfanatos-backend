package br.pucminas.doacoes.enums;

public enum SituacaoDoacao {
    CRIADA(1, "Criada", "Doação cadastrada pelo Doador."),
    LIBERADA(2, "Liberada", "Doação liberada pelo Doador para demonstração de interesse pelos orfanatos."),
    AUTORIZADA(3, "Autorizada", "Doação autorizada pelo administrador do sistema.");

    private int cod;
    private String nome;
    private String descricao;

    private SituacaoDoacao(int cod, String nome, String descricao) {
        this.cod = cod;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static SituacaoDoacao toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (SituacaoDoacao tipo : SituacaoDoacao.values()) {
            if (cod.equals(tipo.cod)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Código da situação da doação inválida: " + cod);
    }
}