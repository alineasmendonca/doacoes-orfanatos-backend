package br.pucminas.doacoes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ContaDTO {

    private Integer id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String name;

    private String description;

    @NotEmpty(message = "{campo.usuario.obrigatorio}")
    private String usernameAppUser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsernameAppUser() {
		return usernameAppUser;
	}

	public void setUsernameAppUser(String usernameAppUser) {
		this.usernameAppUser = usernameAppUser;
	}

	public ContaDTO(Integer id, @NotEmpty(message = "{campo.nome.obrigatorio}") String name, String description,
			@NotEmpty(message = "{campo.usuario.obrigatorio}") String usernameAppUser) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.usernameAppUser = usernameAppUser;
	}

	public ContaDTO() {
		super();
	}
    
    
}
