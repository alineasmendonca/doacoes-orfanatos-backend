package br.pucminas.doacoes.dtos;

import java.io.Serializable;

import br.pucminas.doacoes.domain.Interesse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InteresseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer idDoacao;
	private Integer idUsuarioInteressado;
	private Integer idOrfanatoInteressado;
	
	public InteresseDTO(Interesse obj) {
		super();
		this.id = obj.getId();
		this.idDoacao = obj.getIdDoacao();
		this.idUsuarioInteressado = obj.getIdUsuarioInteressado();
		this.idOrfanatoInteressado = obj.getIdOrfanatoInteressado();
		
//		this.id = obj.getId();
//		if(obj.getDoacao() != null) {
//			this.idDoacao = obj.getDoacao().getId();	
//		}
//		
//		if(obj.getUsuarioInteressado() != null) {
//			this.idUsuarioInteressado = obj.getUsuarioInteressado().getId();	
//		}
//		
//		if(obj.getOrfanatoInteressado() != null) {
//			this.idOrfanatoInteressado = obj.getOrfanatoInteressado().getId();	
//		}
	}
}
