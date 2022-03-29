package br.pucminas.doacoes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public void createUserAdmin(){

        Optional<Usuario> appUser = repository.findByUsername("admin");

        if (appUser == null || !appUser.isPresent()) {
        	Usuario userAdmin = new Usuario();
            userAdmin.setUsername("admin");
            userAdmin.setName("Admin");
            userAdmin.setPassword("12345");
            userAdmin.setPhone("(31) 99245-4848");
            userAdmin.setEmail("juquisilva@gmail.com");
            userAdmin.setAdmin(true);
            repository.save(userAdmin);
        }
    }

    public Usuario save (Usuario appUser){

        if (appUser.getUsername().contains(" ")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome de usuário não deve conter espaços em branco!");
        }
        boolean exists = repository.existsByUsername(appUser.getUsername());
        if (exists){
            throw new RegisteredUserException(appUser.getUsername());
        }
        return repository.save(appUser);
    }

    public void delete (String username){

    	Usuario appUser = findByUsername(username);
        repository.delete(appUser);
    }

    public Usuario findByUsername(String username){
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));
    }

    public Usuario findById(Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }

    public List<Usuario> findAllOrderByUsernameAsc(){
        return repository
                .findAllOrderByUsernameAsc();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Usuario appUser = repository
                            .findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles("USER")
                .build()
                ;
    }

    public void update(UsuarioDTO updatedAppUserDTO) {
    	Usuario appUser = findByUsername(updatedAppUserDTO.getUsername());

        appUser.setName(updatedAppUserDTO.getName());
        appUser.setEmail(updatedAppUserDTO.getEmail());
        appUser.setPhone(updatedAppUserDTO.getPhone());
        appUser.setPassword(updatedAppUserDTO.getPassword());
        appUser.setAdmin(updatedAppUserDTO.isAdmin());

        this.repository.save(appUser);
    }
}
