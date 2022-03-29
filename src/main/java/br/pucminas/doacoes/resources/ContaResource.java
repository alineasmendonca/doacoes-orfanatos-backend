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

@CrossOrigin("*")
@RestController
@RequestMapping(value="/contas")
public class ContaResource {

	@Autowired
    private ContaRepository repository;
	
	@Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContaResource(ContaRepository repository, UsuarioRepository appUserRepository){
        this.repository = repository;
        this.usuarioRepository = appUserRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Conta save(@RequestBody @Valid ContaDTO contaDTO) {
        String loginUsuario = contaDTO.getUsernameAppUser();

        Usuario appUser =
                this.usuarioRepository.findByLogin(loginUsuario)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário inexistente"));

        Conta conta = new Conta();
        conta.setName(contaDTO.getName());
        conta.setDescription(contaDTO.getDescription());
        conta.setUsuario(appUser);

        return repository.save(conta);
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
                .map(conta -> {
                    repository.delete(conta);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable Integer id, @RequestBody ContaDTO contaAtualizadaDTO) {
        this.repository
                .findById(id)
                .map(conta -> {
                    conta.setName(contaAtualizadaDTO.getName());
                    conta.setDescription(contaAtualizadaDTO.getDescription());
                    this.repository.save(conta);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    @GetMapping("{username}")
    @RequestMapping("/accountsByUsername")
    public List<Conta> getAllByUsername(@RequestParam(value = "username", required = true) String username){
        return repository.findAllByLoginOrderByNameAsc(username);
    }

}

