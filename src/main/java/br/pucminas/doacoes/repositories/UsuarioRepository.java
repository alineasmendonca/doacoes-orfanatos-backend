package br.pucminas.doacoes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.doacoes.domain.Orfanato;
import br.pucminas.doacoes.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(" select a from Usuario a " +
            " where a.username like ( :username )")
    Optional<Usuario> findByUsername(@Param("username") String username);

    // Query method. Mesmo que -> select count(*) > 0 from user where username = :login
    boolean existsByUsername(String username);

    @Query(" select a from Usuario a " +
            " order by a.username ")
    List<Usuario> findAllOrderByUsernameAsc();
    
    
    @Query("select u from Usuario u where (:nome is null or lower(u.nome) like %:nome%) "
			+ " and (:perfil is null or u.perfil = :perfil) "
			+ " and (:email is null or lower(u.email) like %:email%) "
			+ " order by lower(u.nome)")
	@Transactional(readOnly = true)
	List<Usuario> findByFiltros(@Param("nome") String nome, @Param("email") String email, @Param("perfil") Integer perfil);
	
	Orfanato findByNomeIgnoreCase(String nome);
}
