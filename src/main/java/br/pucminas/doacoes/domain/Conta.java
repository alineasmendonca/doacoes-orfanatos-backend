package br.pucminas.doacoes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O Campo Nome é obrigatório")
    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Conta(Integer id, @NotEmpty(message = "{campo.nome.obrigatorio}") String name, String description,
			Usuario usuario) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.usuario = usuario;
	}

	public Conta() {
		super();
	}
    
    

}

