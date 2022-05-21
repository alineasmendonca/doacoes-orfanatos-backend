package br.pucminas.doacoes.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pucminas.doacoes.domain.Orfanato;
import br.pucminas.doacoes.domain.Usuario;
import br.pucminas.doacoes.dtos.UsuarioDTO;
import br.pucminas.doacoes.resources.exceptions.RegisteredUserException;
import br.pucminas.doacoes.services.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService service;
    
    /*@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid Usuario appUser){
    	System.out.println(appUser);
        try {
            service.save(appUser);
        } catch (RegisteredUserException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> insert(@Validated @RequestBody UsuarioDTO objDto) throws Exception {
    	if(objDto.getOrfanato() == null) {
    		objDto.setOrfanato(new Orfanato());
    	}
    	System.out.println("Usuário: " + objDto);
        var tipo = service.insert(objDto);

        var uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(tipo.getId())
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    /*@GetMapping
    public List<Usuario> getAll(){
        return service.findAllOrderByUsernameAsc();
    }*/

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
    
    @GetMapping
	public ResponseEntity<List<UsuarioDTO>> findByFiltros(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "perfil", required = false) Integer perfil) {
		List<Usuario> lista = service.findByFiltros(nome, email, perfil);
		List<UsuarioDTO> listaDTO = lista.stream().map(UsuarioDTO::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);
	}

}
