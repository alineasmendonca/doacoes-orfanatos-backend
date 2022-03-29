package br.pucminas.doacoes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "account", schema = "public")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_app_user")
    private Usuario appUser;

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

	public Usuario getAppUser() {
		return appUser;
	}

	public void setAppUser(Usuario appUser) {
		this.appUser = appUser;
	}

	public Account(Integer id, @NotEmpty(message = "{campo.nome.obrigatorio}") String name, String description,
			Usuario appUser) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.appUser = appUser;
	}

	public Account() {
		super();
	}
    
    

}

