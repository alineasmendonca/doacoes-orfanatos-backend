package br.pucminas.doacoes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.doacoes.domain.Conta;
import br.pucminas.doacoes.domain.Usuario;
import br.pucminas.doacoes.dtos.ContaDTO;
import br.pucminas.doacoes.repositories.ContaRepository;
import br.pucminas.doacoes.repositories.UsuarioRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaResource {

    private final ContaRepository repository;
    private final UsuarioRepository appUserRepository;

    @Autowired
    private ContaResource(ContaRepository repository, UsuarioRepository appUserRepository){
        this.repository = repository;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta save(@RequestBody @Valid ContaDTO accountDTO) {
        String usernameAppUser = accountDTO.getUsernameAppUser();

        Usuario appUser =
                this.appUserRepository.findByUsername(usernameAppUser)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inexistente"));

        Conta account = new Conta();
        account.setName(accountDTO.getName());
        account.setDescription(accountDTO.getDescription());
        account.setAppUser(appUser);

        return repository.save(account);
    }

    @GetMapping("{id}")
    public Conta findById(@PathVariable Integer id){
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
    public void update (@PathVariable Integer id, @RequestBody ContaDTO updatedAccountDTO) {
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
    public List<Conta> getAllByUsername(@RequestParam(value = "username", required = true) String username){
        return repository.findAllByUsernameOrderByNameAsc(username);
    }

}

