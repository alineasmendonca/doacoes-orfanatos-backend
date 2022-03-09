package br.pucminas.doacoes.resources.exceptions;

public class RegisteredUserException extends  RuntimeException {

    public RegisteredUserException(String login ) {
        super ("Usuário já cadastrado para o login " + login);
    }
}
