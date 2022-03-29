package br.pucminas.doacoes.resources;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.doacoes.domain.Usuario;
import br.pucminas.doacoes.dtos.UsuarioDTO;
import br.pucminas.doacoes.resources.exceptions.RegisteredUserException;
import br.pucminas.doacoes.services.UsuarioService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
    private UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Usuario usuario){
        try {
            service.save(usuario);
        } catch (RegisteredUserException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario> getAll(){
        return service.findAllOrdernadoPeloLoginAsc();
    }

    @GetMapping("{id}")
    public UsuarioDTO findById(@PathVariable Integer id){
    	Usuario usuario = service
                .findById(id);

    	UsuarioDTO appUserDTO = new UsuarioDTO();
        appUserDTO.setId(usuario.getId());
        appUserDTO.setLogin(usuario.getLogin());
        appUserDTO.setNome(usuario.getNome());
        appUserDTO.setEmail(usuario.getEmail());
        appUserDTO.setAdmin(usuario.isAdmin());
        appUserDTO.setTelefoneCelular(usuario.getTelefoneCelular());
        appUserDTO.setTelefoneFixo(usuario.getTelefoneFixo());
        appUserDTO.setSenha(usuario.getSenha());
        appUserDTO.setPerfil(usuario.getPerfil());
        appUserDTO.setEndereco(usuario.getEndereco());
        appUserDTO.setSenha(usuario.getSenha());

        System.out.println("Usuário: ");
        System.out.println(appUserDTO);
        return appUserDTO;
    }

    @GetMapping("{username}")
    @RequestMapping("/userByUsername")
    public UsuarioDTO findByUsername(@RequestParam(value = "username", required = true) String username){
    	Usuario appUser = service
                .findByLogin(username);

    	UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(appUser.getId());
        usuarioDTO.setLogin(appUser.getLogin());
        usuarioDTO.setNome(appUser.getNome());
        usuarioDTO.setEmail(appUser.getEmail());
        usuarioDTO.setAdmin(appUser.isAdmin());
        usuarioDTO.setTelefoneCelular(appUser.getTelefoneCelular());
        usuarioDTO.setTelefoneFixo(appUser.getTelefoneFixo());
        usuarioDTO.setSenha(appUser.getSenha());
        usuarioDTO.setPerfil(appUser.getPerfil());
        usuarioDTO.setEndereco(appUser.getEndereco());

        System.out.println("Usuário: ");
        System.out.println(usuarioDTO);
        return usuarioDTO;
    }

    @GetMapping("{username}")
    @RequestMapping("/userIsAdmin")
    public boolean isUserAdmin(@RequestParam(value = "username", required = true) String username){
    	Usuario appUser = service
                .findByLogin(username);
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
