package br.pucminas.doacoes.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.pucminas.doacoes.domain.Orfanato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrfanatoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	@Length(min = 3, max = 500, message = "O Campo DESCRIÇÃO deve ter entre 3 e 500 caracteres")
	private String historia;
	
	@NotNull(message = "O Campo DATA DA FUNDAÇÃO é obrigatório.")
	private LocalDateTime dataFundacao;
	
	@NotEmpty(message = "O Campo TELEFONE é obrigatório")
	@Length(min = 10, max = 10, message = "O Campo TELEFONE deve ter 10 digítos")
	private String telefone;

	@NotNull(message = "O Campo E-MAIL é obrigatório.")
	private String email;
	
	
	public OrfanatoDTO(Orfanato obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.endereco = obj.getEndereco();
		this.quantidadeCriancas = obj.getQuantidadeCriancas();
		this.historia = obj.getHistoria();
		this.dataFundacao = obj.getDataFundacao();
		this.telefone = obj.getTelefone();
		this.email = obj.getEmail();
	}
	

}
