package br.pucminas.doacoes.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.doacoes.domain.Usuario;
import br.pucminas.doacoes.dtos.UsuarioDTO;
import br.pucminas.doacoes.resources.exceptions.RegisteredUserException;
import br.pucminas.doacoes.services.UsuarioService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Usuario appUser){
    	System.out.println(appUser);
        try {
            service.save(appUser);
        } catch (RegisteredUserException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> getAll(){
        return service.findAllOrderByUsernameAsc();
    }

    @GetMapping("{id}")
    public UsuarioDTO findById(@PathVariable Integer id){
    	Usuario appUser = service
                .findById(id);

    	UsuarioDTO appUserDTO = new UsuarioDTO();
        appUserDTO.setId(appUser.getId());
        appUserDTO.setUsername(appUser.getUsername());
        appUserDTO.setNome(appUser.getNome());
        appUserDTO.setEmail(appUser.getEmail());
        appUserDTO.setAdmin(appUser.isAdmin());
        appUserDTO.setTelefoneCelular(appUser.getTelefoneCelular());
        appUserDTO.setSenha(appUser.getSenha());

        System.out.println("Usuário: ");
        System.out.println(appUserDTO);
        return appUserDTO;
    }

    @GetMapping("{username}")
    @RequestMapping("/userByUsername")
    public UsuarioDTO findByUsername(@RequestParam(value = "username", required = true) String username){
    	Usuario appUser = service
                .findByUsername(username);

    	UsuarioDTO appUserDTO = new UsuarioDTO();
        appUserDTO.setId(appUser.getId());
        appUserDTO.setUsername(appUser.getUsername());
        appUserDTO.setNome(appUser.getNome());
        appUserDTO.setEmail(appUser.getEmail());
        appUserDTO.setAdmin(appUser.isAdmin());
        appUserDTO.setTelefoneFixo(appUser.getTelefoneFixo());
        appUserDTO.setSenha(appUser.getSenha());

        System.out.println("Usuário: ");
        System.out.println(appUserDTO);
        return appUserDTO;
    }

    @GetMapping("{username}")
    @RequestMapping("/userIsAdmin")
    public boolean isUserAdmin(@RequestParam(value = "username", required = true) String username){
    	Usuario appUser = service
                .findByUsername(username);
        return appUser.isAdmin();
    }

    @DeleteMapping("{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable String username) {
        this.service.delete(username);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@RequestBody @Valid UsuarioDTO updatedAppUserDTO) {
        System.out.println(updatedAppUserDTO);
        this.service.update(updatedAppUserDTO);
    }

}
