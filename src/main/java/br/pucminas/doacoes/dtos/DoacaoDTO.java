package br.pucminas.doacoes.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.pucminas.doacoes.domain.Categoria;
import br.pucminas.doacoes.domain.Doacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoacaoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotNull(message = "O Campo QUANTIDADE é obrigatório")
	private Integer quantidade;
	
	@NotEmpty(message = "O Campo DESCRIÇÃO é obrigatório")
	@Length(min = 3, max = 200, message = "O Campo DESCRIÇÃO deve ter entre 3 e 250 caracteres")
	private String descricao;
	
	@NotNull(message = "Categoria deve ser preenchida.")
    private Integer idCategoria;
	
	@NotEmpty(message = "O Campo ENDEREÇO RETIRADA é obrigatório")
	@Length(min = 3, max = 250, message = "O Campo ENDEREÇO RETIRADA deve ter entre 3 e 250 caracteres")
	private String localRetirada;
	
	private Categoria categoria;
	
	private LocalDateTime dataCadastro;
	
	private Date dataLiberacao;
	private Date dataAutorizacao;
	private Integer idDoador;
	private Integer idOrfanatoContemplado;
	
	
	public DoacaoDTO(Doacao obj) {
		super();
		this.id = obj.getId();
		this.quantidade = obj.getQuantidade();
		this.descricao = obj.getDescricao();
		this.dataAutorizacao = obj.getDataAutorizacao();
		this.dataCadastro = obj.getDataCadastro();
		this.dataLiberacao = obj.getDataLiberacao();
		if(obj.getCategoria() != null) {
			this.idCategoria = obj.getCategoria().getId();	
		}
		this.categoria = obj.getCategoria();
		this.idDoador = obj.getIdDoador();
		this.idOrfanatoContemplado = obj.getIdOrfanatoContemplado();
		this.localRetirada = obj.getLocalRetirada();
	}
}
