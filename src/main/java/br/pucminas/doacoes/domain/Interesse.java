package br.pucminas.doacoes.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Interesse implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Integer idDoacao;
	
	@Column
	private Integer idUsuarioInteressado;
	
	@Column
	private Integer idOrfanatoInteressado;
	
	@Column
	private LocalDateTime dataDemonstracaoInteresse;
    
	@Override
    public String toString() {
		return "id:" + id + "idDoacao" + idDoacao + "idUsuarioInteressado" + idUsuarioInteressado + "idOrfanatoInteressado:" + idOrfanatoInteressado
				+ "dataDemonstracaoInteresse:" + dataDemonstracaoInteresse;
//        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("idDoacao",
//                Optional.ofNullable(getDoacao()).map(p -> getDoacao().getId())
//                    .orElse(null))
//            .append("idUsuarioInteressado",
//                    Optional.ofNullable(getUsuarioInteressado()).map(p -> getUsuarioInteressado().getId())
//                        .orElse(null))	        
//            .toString();
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
		Interesse other = (Interesse) obj;
		return Objects.equals(id, other.id);
	}

}
/*public class Interesse implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_doacao")
	private Doacao doacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_usuario_interessado")
    private Usuario usuarioInteressado;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_orfanato_interessado")
    private Orfanato orfanatoInteressado;
    
	@Override
    public String toString() {
		return "id:" + id + "idDoacao" + doacao.getId() + "idUsuarioInteressado" + usuarioInteressado.getId();
//        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("idDoacao",
//                Optional.ofNullable(getDoacao()).map(p -> getDoacao().getId())
//                    .orElse(null))
//            .append("idUsuarioInteressado",
//                    Optional.ofNullable(getUsuarioInteressado()).map(p -> getUsuarioInteressado().getId())
//                        .orElse(null))	        
//            .toString();
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
		Interesse other = (Interesse) obj;
		return Objects.equals(id, other.id);
	}

}*/
