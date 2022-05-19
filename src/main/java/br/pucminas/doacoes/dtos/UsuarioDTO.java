package br.pucminas.doacoes.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.pucminas.doacoes.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

    @NotEmpty(message = "O Campo USERNAME é obrigatório")
    private String username;

    @NotEmpty(message = "O Campo NOME é obrigatório")
    private String nome;

    @NotEmpty(message = "O Campo TELEFONE é obrigatório")
    private String telefoneCelular;

    @NotEmpty(message = "O Campo EMAIL é obrigatório")
    private String email;

    private String senha;
    
    private boolean admin;
    
    @NotEmpty(message = "O Campo TELEFONE FIXO é obrigatório")
    private String telefoneFixo;
    
    @NotEmpty(message = "O Campo ENDEREÇO é obrigatório")
    private String endereco;
    
    @NotNull(message = "O Campo QUANTIDADE é obrigatório")
    private Integer perfil;
    
    public UsuarioDTO(Usuario obj) {
		super();
		this.id = obj.getId();
		this.username = obj.getUsername();
		this.nome = obj.getNome();
		this.telefoneCelular = obj.getTelefoneCelular();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.admin = obj.isAdmin();
		this.telefoneFixo = obj.getTelefoneFixo();
		this.endereco = obj.getEndereco();
		this.perfil = obj.getPerfil();
	}
	

}
