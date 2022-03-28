package br.pucminas.doacoes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
// @Table(name = "app_user", schema = "public")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @Column
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String name;

    @Column
    @NotEmpty(message = "{campo.telefone.obrigatorio}")
    private String phone;

    @Column
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

    @Column(name = "senha")
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String password;

    @Column
    private boolean admin = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Usuario(Integer id, @NotEmpty(message = "{campo.login.obrigatorio}") String username,
			@NotEmpty(message = "{campo.nome.obrigatorio}") String name,
			@NotEmpty(message = "{campo.telefone.obrigatorio}") String phone,
			@NotEmpty(message = "{campo.email.obrigatorio}") String email,
			@NotEmpty(message = "{campo.senha.obrigatorio}") String password, boolean admin) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}

	public Usuario() {
		super();
	}
    
    
    
    
    
    

}
