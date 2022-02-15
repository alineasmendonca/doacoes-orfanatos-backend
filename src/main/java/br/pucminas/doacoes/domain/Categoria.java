package br.pucminas.doacoes.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Categoria {
	private Integer id;
	private String nome;
	private String descricao;
	private List<Doacao> doacoes = new ArrayList<Doacao>();
	
	public Categoria() {
		super();
	}
	public Categoria(Integer id, String nome, String descricao, List<Doacao> doacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.doacoes = doacoes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<Doacao> getDoacoes() {
		return doacoes;
	}
	public void setDoacoes(List<Doacao> doacoes) {
		this.doacoes = doacoes;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
