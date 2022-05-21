package br.pucminas.doacoes.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import br.pucminas.doacoes.domain.Orfanato;
import br.pucminas.doacoes.domain.Usuario;
import br.pucminas.doacoes.dtos.UsuarioDTO;
import br.pucminas.doacoes.repositories.UsuarioRepository;
import br.pucminas.doacoes.resources.exceptions.RegisteredUserException;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
	private OrfanatoService orfanatoService;

    public void createUserAdmin(){

        Optional<Usuario> appUser = repository.findByUsername("admin");

        if (appUser == null || !appUser.isPresent()) {
        	Usuario userAdmin = new Usuario();
            userAdmin.setUsername("12345");
            userAdmin.setNome("Admin");
            userAdmin.setSenha("12345");
            userAdmin.setTelefoneCelular("(85) 999999999");
            userAdmin.setTelefoneFixo("(85) 999999999");
            userAdmin.setEmail("aline@gmail.com");
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
                .password(appUser.getSenha())
                .roles("USER")
                .build()
                ;
    }

    public void update(UsuarioDTO updatedAppUserDTO) {
    	Usuario appUser = findByUsername(updatedAppUserDTO.getUsername());

        appUser.setNome(updatedAppUserDTO.getNome());
        appUser.setEmail(updatedAppUserDTO.getEmail());
        appUser.setTelefoneCelular(updatedAppUserDTO.getTelefoneCelular());
        appUser.setSenha(updatedAppUserDTO.getSenha());
        appUser.setTelefoneFixo(updatedAppUserDTO.getTelefoneFixo());
        appUser.setAdmin(updatedAppUserDTO.isAdmin());

        this.repository.save(appUser);
    }
    
    public List<Usuario> findByFiltros(String nome, String email, Integer perfil) {

        List<Usuario> lista = null;

        if (!StringUtils.hasText(nome) && perfil == null && !StringUtils.hasText(email)) {
            lista = repository.findAll();
        } else {
            nome = StringUtils.hasText(nome) ? nome.toLowerCase() : null;
            email = StringUtils.hasText(email) ? email.toLowerCase() : null;
            lista = repository.findByFiltros(nome, email, perfil);
        }
        return lista;
    }
    
    @Transactional
    public Usuario insert(UsuarioDTO usuarioDto) throws Exception {
        verificarExistencia(null, usuarioDto);

        var usuario = new Usuario();

        complementarDados(usuario, usuarioDto);

        repository.save(usuario);

        return usuario;
    }
    
    private Usuario complementarDados(Usuario usuario, UsuarioDTO usuarioDto) {
		Orfanato orfanato = null;
		try {
			if(usuarioDto.getIdOrfanato() != null) {
				orfanato = orfanatoService.findById(usuarioDto.getIdOrfanato());	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		usuario.setAdmin(usuarioDto.isAdmin());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setEndereco(usuarioDto.getEndereco());
		usuario.setNome(usuarioDto.getNome());
		usuario.setPerfil(usuarioDto.getPerfil());
		usuario.setSenha(usuarioDto.getSenha());
		usuario.setTelefoneCelular(usuarioDto.getTelefoneCelular());
		usuario.setTelefoneFixo(usuarioDto.getTelefoneFixo());
		usuario.setUsername(usuarioDto.getUsername());
		usuario.setOrfanato(orfanato);
	

        return usuario;
    }
    
    private void verificarExistencia(Integer idUsuarioAlterado, UsuarioDTO objDto) throws Exception {
        var usuario = repository.findByUsernameIgnoreCase(objDto.getUsername());
        if (usuario != null
            && jaExiste(idUsuarioAlterado, usuario.getId())) {
            throw new Exception("Doação já cadastrada");
        }
    }
    
    public static boolean jaExiste(Integer idEntidadeAlterada, Integer idRecuperadoBanco) {
        return idEntidadeAlterada == null || !idRecuperadoBanco.equals(idEntidadeAlterada);
    }
    
    
}
