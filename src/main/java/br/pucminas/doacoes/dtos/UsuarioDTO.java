package br.pucminas.doacoes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import br.pucminas.doacoes.domain.Categoria;

@Data
@AllArgsConstructor
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

}
