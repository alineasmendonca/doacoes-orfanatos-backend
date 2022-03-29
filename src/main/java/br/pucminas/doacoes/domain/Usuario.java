package br.pucminas.doacoes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O Campo Login é obrigatório")
    private String login;
    
    @NotEmpty(message = "O Campo Nome é obrigatório")
    private String nome;
    
    @NotEmpty(message = "O Campo Telefone Celular é obrigatório")
    private String telefoneCelular;
    
    private String telefoneFixo;
    
    private Integer perfil;
    
    @NotEmpty(message = "O Campo Endereço é obrigatório")
    private String endereco;

    @NotEmpty(message = "O Campo Email é obrigatório")
    private String email;

    @NotEmpty(message = "O Campo Senha é obrigatório")
    private String senha;

    private boolean admin = false;

	
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

	
	

	public Usuario(Integer id, @NotEmpty(message = "O Campo Login é obrigatório") String login,
			@NotEmpty(message = "O Campo Perfil é obrigatório") Integer perfil,
			@NotEmpty(message = "O Campo Nome é obrigatório") String nome,
			@NotEmpty(message = "O Campo Telefone Celular é obrigatório") String telefoneCelular, String telefoneFixo,
			@NotEmpty(message = "O Campo Endereço é obrigatório") String endereco,
			@NotEmpty(message = "O Campo Email é obrigatório") String email,
			@NotEmpty(message = "O Campo Senha é obrigatório") String senha, boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.perfil = perfil;
		this.nome = nome;
		this.telefoneCelular = telefoneCelular;
		this.telefoneFixo = telefoneFixo;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.admin = admin;
	}

	/*public Usuario(Integer id, @NotEmpty(message = "{campo.login.obrigatorio}") String login,
			@NotEmpty(message = "{campo.nome.obrigatorio}") String name,
			@NotEmpty(message = "{campo.telefone.obrigatorio}") String phone,
			@NotEmpty(message = "{campo.email.obrigatorio}") String email,
			@NotEmpty(message = "{campo.senha.obrigatorio}") String password, boolean admin) {
		super();
		this.id = id;
		this.login = login;
		this.nome = name;
		this.telefoneCelular = phone;
		this.email = email;
		this.senha = password;
		this.admin = admin;
	}*/

	public Usuario() {
		super();
	}
    
    
    
    
    
    

}
