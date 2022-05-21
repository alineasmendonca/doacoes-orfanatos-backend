package br.pucminas.doacoes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "O Campo LOGIN é obrigatório")
    private String username;

    @Column
    @NotEmpty(message = "O Campo NOME é obrigatório")
    private String name;

    @Column
    @NotEmpty(message = "O Campo TELEFONE é obrigatório")
    private String phone;

    @Column
    @NotEmpty(message = "O Campo EMAIL é obrigatório")
    private String email;

    @Column(name = "senha")
    @NotEmpty(message = "O Campo SENHA é obrigatório")
    private String password;

    @Column
    private boolean admin = false;*/
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "O Campo LOGIN é obrigatório")
    private String username;

    @Column
    @NotEmpty(message = "O Campo NOME é obrigatório")
    private String nome;

    @Column
    @NotEmpty(message = "O Campo TELEFONE CELULAR é obrigatório")
    private String telefoneCelular;

    @Column
    @NotEmpty(message = "O Campo EMAIL é obrigatório")
    private String email;

    @Column
    @NotEmpty(message = "O Campo SENHA é obrigatório")
    private String senha;

    @Column
    private boolean admin = false;
    
    @Column
    @NotEmpty(message = "O Campo TELEFONE FIXO é obrigatório")
    private String telefoneFixo;
    
    @Column
    @NotEmpty(message = "O Campo ENDEREÇO é obrigatório")
    private String endereco;
    
    @Column
    @NotNull(message = "O Campo PERFIL é obrigatório")
    private Integer perfil;
    
    @JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="orfanato_id", nullable = true)
	private Orfanato orfanato;

}
