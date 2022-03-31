package br.pucminas.doacoes.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doacao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "O Campo QUANTIDADE é obrigatório")
	private Integer quantidade;
	
	@NotEmpty(message = "O Campo DESCRIÇÃO é obrigatório")
	@Length(min = 3, max = 200, message = "O Campo DESCRIÇÃO deve ter entre 3 e 250 caracteres")
	private String descricao;
	
	@JsonIgnore
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
