package br.pucminas.doacoes.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private Integer id;

    @NotEmpty(message = "{campo.usuario.obrigatorio}")
    private String username;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String name;

    @NotEmpty(message = "{campo.telefone.obrigatorio}")
    private String phone;

    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

    private boolean admin;

    private String password;

	public UsuarioDTO(Integer id, @NotEmpty(message = "{campo.usuario.obrigatorio}") String username,
			@NotEmpty(message = "{campo.nome.obrigatorio}") String name,
			@NotEmpty(message = "{campo.telefone.obrigatorio}") String phone,
			@NotEmpty(message = "{campo.email.obrigatorio}") String email, boolean admin, String password) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.admin = admin;
		this.password = password;
	}
    
    
    

}
