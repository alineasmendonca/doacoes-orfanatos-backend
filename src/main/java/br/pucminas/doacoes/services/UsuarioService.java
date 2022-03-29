package br.pucminas.doacoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.doacoes.domain.Usuario;
import br.pucminas.doacoes.dtos.UsuarioDTO;
import br.pucminas.doacoes.repositories.UsuarioRepository;
import br.pucminas.doacoes.resources.exceptions.RegisteredUserException;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    /*public void createUserAdmin(){

        Optional<Usuario> usuarioOptional = repository.findByLogin("admin");

        if (usuarioOptional == null || !usuarioOptional.isPresent()) {
        	Usuario usuarioAdministrador = new Usuario();
            usuarioAdministrador.setLogin("admin");
            usuarioAdministrador.setNome("Usuário Administrador");
            usuarioAdministrador.setSenha("admin");
            usuarioAdministrador.setTelefoneCelular("(85) 988888888");
            usuarioAdministrador.setEmail("alininfo@gmail.com");
            usuarioAdministrador.setPerfil(1);
            usuarioAdministrador.setAdmin(true);
            repository.save(usuarioAdministrador);
        }
    }*/

    public Usuario save (Usuario appUser){

        if (appUser.getLogin().contains(" ")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome de usuário não deve conter espaços em branco!");
        }
        boolean exists = repository.existsByLogin(appUser.getLogin());
        if (exists){
            throw new RegisteredUserException(appUser.getLogin());
        }
        return repository.save(appUser);
    }

    public void delete (String username){

    	Usuario appUser = findByLogin(username);
        repository.delete(appUser);
    }

    public Usuario findByLogin(String username){
        return repository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));
    }

    public Usuario findById(Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public List<Usuario> findAllOrdernadoPeloLoginAsc(){
        return repository
                .findAllOrderByLoginAsc();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    	Usuario usuario = repository
                            .findByLogin(login)
                            .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles("USER")
                .build()
                ;
    }

    public void update(UsuarioDTO usuarioAtualizadoDTO) {
    	Usuario usuario = findByLogin(usuarioAtualizadoDTO.getLogin());

        usuario.setNome(usuarioAtualizadoDTO.getNome());
        usuario.setEmail(usuarioAtualizadoDTO.getEmail());
        usuario.setTelefoneCelular(usuarioAtualizadoDTO.getTelefoneCelular());
        usuario.setTelefoneFixo(usuarioAtualizadoDTO.getTelefoneFixo());
        usuario.setEndereco(usuarioAtualizadoDTO.getEndereco());
        usuario.setPerfil(usuarioAtualizadoDTO.getPerfil());
        usuario.setSenha(usuarioAtualizadoDTO.getSenha());
        usuario.setAdmin(usuarioAtualizadoDTO.isAdmin());

        this.repository.save(usuario);
    }
}
