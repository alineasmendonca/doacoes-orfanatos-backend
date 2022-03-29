package br.pucminas.doacoes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.doacoes.domain.Account;
import br.pucminas.doacoes.domain.Usuario;
import br.pucminas.doacoes.dtos.AccountDTO;
import br.pucminas.doacoes.repositories.AccountRepository;
import br.pucminas.doacoes.repositories.UsuarioRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class AccountController {

    private final AccountRepository repository;
    private final UsuarioRepository appUserRepository;

    @Autowired
    private AccountController(AccountRepository repository, UsuarioRepository appUserRepository){
        this.repository = repository;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account save(@RequestBody @Valid AccountDTO accountDTO) {
        String usernameAppUser = accountDTO.getUsernameAppUser();

        Usuario appUser =
                this.appUserRepository.findByUsername(usernameAppUser)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inexistente"));

        Account account = new Account();
        account.setName(accountDTO.getName());
        account.setDescription(accountDTO.getDescription());
        account.setAppUser(appUser);

        return repository.save(account);
    }

    @GetMapping("{id}")
    public Account findById(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id) {
        this.repository
                .findById(id)
                .map(account -> {
                    repository.delete(account);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable Integer id, @RequestBody AccountDTO updatedAccountDTO) {
        this.repository
                .findById(id)
                .map(account -> {
                    account.setName(updatedAccountDTO.getName());
                    account.setDescription(updatedAccountDTO.getDescription());
                    this.repository.save(account);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    @GetMapping("{username}")
    @RequestMapping("/accountsByUsername")
    public List<Account> getAllByUsername(@RequestParam(value = "username", required = true) String username){
        return repository.findAllByUsernameOrderByNameAsc(username);
    }

}

