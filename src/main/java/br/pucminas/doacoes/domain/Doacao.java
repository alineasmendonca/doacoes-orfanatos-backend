package br.pucminas.doacoes.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Doacao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer quantidade;
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private Categoria categoria;
	
	private Date dataCadastro;
	private Date dataLiberacao;
	private Date dataAutorizacao;
	private Integer idDoador;
	private Integer idOrfanatoContemplado;

//	private Doador doador;
//	private List<Orfanato> interessados = new ArrayList<Orfanato>();
//	private Orfanato orfanatoContemplado;
//	private Endereco localRetirada;
//	private Situacao situacao;
	
	public Doacao() {
		super();
	}
	
	
	public Doacao(Integer id, Integer quantidade, String descricao, Categoria categoria, Date dataCadastro,
		Date dataLiberacao, Date dataAutorizacao, Integer idDoador, Integer idOrfanatoContemplado) {
	super();
	this.id = id;
	this.quantidade = quantidade;
	this.descricao = descricao;
	this.categoria = categoria;
	this.dataCadastro = dataCadastro;
	this.dataLiberacao = dataLiberacao;
	this.dataAutorizacao = dataAutorizacao;
	this.idDoador = idDoador;
	this.idOrfanatoContemplado = idOrfanatoContemplado;
}
	


	/*public Doacao(Integer id, Integer quantidade, String descricao, Categoria categoria, Date dataCadastro,
			Date dataLiberacao, Date dataAutorizacao, Integer idDoador, Integer idOrfanatoContemplado, Doador doador,
			List<Orfanato> interessados, Orfanato orfanatoContemplado, Endereco localRetirada, Situacao situacao) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dataCadastro = dataCadastro;
		this.dataLiberacao = dataLiberacao;
		this.dataAutorizacao = dataAutorizacao;
		this.idDoador = idDoador;
		this.idOrfanatoContemplado = idOrfanatoContemplado;
		this.doador = doador;
		this.interessados = interessados;
		this.orfanatoContemplado = orfanatoContemplado;
		this.localRetirada = localRetirada;
		this.situacao = situacao;
	}*/


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataLiberacao() {
		return dataLiberacao;
	}
	public void setDataLiberacao(Date dataLiberacao) {
		this.dataLiberacao = dataLiberacao;
	}
	public Date getDataAutorizacao() {
		return dataAutorizacao;
	}
	public void setDataAutorizacao(Date dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}
	public Integer getIdDoador() {
		return idDoador;
	}
	public void setIdDoador(Integer idDoador) {
		this.idDoador = idDoador;
	}
	public Integer getIdOrfanatoContemplado() {
		return idOrfanatoContemplado;
	}
	public void setIdOrfanatoContemplado(Integer idOrfanatoContemplado) {
		this.idOrfanatoContemplado = idOrfanatoContemplado;
	}

	/*public Doador getDoador() {
		return doador;
	}


	public void setDoador(Doador doador) {
		this.doador = doador;
	}


	public List<Orfanato> getInteressados() {
		return interessados;
	}


	public void setInteressados(List<Orfanato> interessados) {
		this.interessados = interessados;
	}


	public Orfanato getOrfanatoContemplado() {
		return orfanatoContemplado;
	}


	public void setOrfanatoContemplado(Orfanato orfanatoContemplado) {
		this.orfanatoContemplado = orfanatoContemplado;
	}


	public Endereco getLocalRetirada() {
		return localRetirada;
	}


	public void setLocalRetirada(Endereco localRetirada) {
		this.localRetirada = localRetirada;
	}


	public Situacao getSituacao() {
		return situacao;
	}


	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}*/


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
		Doacao other = (Doacao) obj;
		return Objects.equals(id, other.id);
	}

}
