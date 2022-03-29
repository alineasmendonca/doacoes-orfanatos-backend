package br.pucminas.doacoes.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
