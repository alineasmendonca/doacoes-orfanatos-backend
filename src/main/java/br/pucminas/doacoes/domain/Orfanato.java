package br.pucminas.doacoes.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orfanato implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O Campo NOME é obrigatório")
	@Length(min = 3, max = 250, message = "O Campo NOME deve ter entre 3 e 250 caracteres")
	private String nome;
	
	@NotEmpty(message = "O Campo ENDEREÇO é obrigatório")
	@Length(min = 3, max = 250, message = "O Campo ENDEREÇO deve ter entre 3 e 250 caracteres")
	private String endereco;
	
	@NotNull(message = "O Campo QUANTIDADE DE CRIANÇAS é obrigatório")
	private Integer quantidadeCriancas;
	
	@NotEmpty(message = "O Campo HISTÓRIA é obrigatório")
	@Length(min = 10, max = 500, message = "O Campo DESCRIÇÃO deve ter entre 10 e 500 caracteres")
	private String historia;
	
	@NotNull(message = "O Campo DATA DA FUNDAÇÃO é obrigatório.")
	private LocalDateTime dataFundacao;
	
	@NotEmpty(message = "O Campo TELEFONE é obrigatório")
	@Length(min = 10, max = 10, message = "O Campo TELEFONE deve ter 10 caracteres")
	private String telefone;
	
	@NotNull(message = "O Campo E-MAIL é obrigatório.")
	private String email;
	
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
		Orfanato other = (Orfanato) obj;
		return Objects.equals(id, other.id);
	}

}
