package br.pucminas.doacoes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class UsuarioDTO {

    private Integer id;

    @NotEmpty(message = "O Campo Login é obrigatório")
    private String login;

    @NotEmpty(message = "O Campo Nome é obrigatório")
    private String nome;

    @NotEmpty(message = "O Campo Telefone Celular é obrigatório")
    private String telefoneCelular;
    
    private String telefoneFixo;
    
    // @NotEmpty(message = "O Campo Perfil é obrigatório")
    private Integer perfil;
    
    @NotEmpty(message = "O Campo Endereço é obrigatório")
    private String endereco;

    @NotEmpty(message = "O Campo Email é obrigatório")
    private String email;

    @NotEmpty(message = "O Campo Senha é obrigatório")
    private String senha;
    
    private boolean admin;
    
    

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Integer id, @NotEmpty(message = "O Campo Login é obrigatório") String login,
			@NotEmpty(message = "O Campo Nome é obrigatório") String nome,
			@NotEmpty(message = "O Campo Telefone Celular é obrigatório") String telefoneCelular, String telefoneFixo,
			Integer perfil, @NotEmpty(message = "O Campo Endereço é obrigatório") String endereco,
			@NotEmpty(message = "O Campo Email é obrigatório") String email,
			@NotEmpty(message = "O Campo Senha é obrigatório") String senha, boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.nome = nome;
		this.telefoneCelular = telefoneCelular;
		this.telefoneFixo = telefoneFixo;
		this.perfil = perfil;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.admin = admin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getTelefoneFixo() {
		return telefoneFixo;
	}

	public void setTelefoneFixo(String telefoneFixo) {
		this.telefoneFixo = telefoneFixo;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
