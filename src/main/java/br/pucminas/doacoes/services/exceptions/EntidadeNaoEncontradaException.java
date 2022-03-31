package br.pucminas.doacoes.services.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 235180375466045989L;

	public EntidadeNaoEncontradaException(String nomeEntidade) {
		super(nomeEntidade);
	}
}