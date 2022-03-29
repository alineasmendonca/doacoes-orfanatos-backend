package br.pucminas.doacoes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import br.pucminas.doacoes.domain.Categoria;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    /*private Integer id;

    @NotEmpty(message = "O Campo USERNAME é obrigatório")
    private String username;

    @NotEmpty(message = "O Campo NOME é obrigatório")
    private String name;

    @NotEmpty(message = "O Campo TELEFONE é obrigatório")
    private String phone;

    @NotEmpty(message = "O Campo EMAIL é obrigatório")
    private String email;

    private boolean admin;

    private String password;*/
	
	private Integer id;

    @NotEmpty(message = "O Campo USERNAME é obrigatório")
    private String username;

    @NotEmpty(message = "O Campo NOME é obrigatório")
    private String nome;

    @NotEmpty(message = "O Campo TELEFONE é obrigatório")
    private String telefoneCelular;

    @NotEmpty(message = "O Campo EMAIL é obrigatório")
    private String email;

    private String senha;
    
    private boolean admin;
    
    @NotEmpty(message = "O Campo TELEFONE FIXO é obrigatório")
    private String telefoneFixo;

}
