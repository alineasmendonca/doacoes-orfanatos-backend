package br.pucminas.doacoes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

    private Integer id;

    @NotEmpty(message = "O Campo NOME é obrigatório")
    private String name;

    private String description;

    @NotEmpty(message = "O Campo USUARIO é obrigatório")
    private String usernameAppUser;
}
