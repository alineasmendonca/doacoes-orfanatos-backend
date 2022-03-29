package br.pucminas.doacoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.pucminas.doacoes.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(" select a from Usuario a " +
            " where a.username like ( :username )")
    Optional<Usuario> findByUsername(@Param("username") String username);

    // Query method. Mesmo que -> select count(*) > 0 from user where username = :login
    boolean existsByUsername(String username);

    @Query(" select a from Usuario a " +
            " order by a.username ")
    List<Usuario> findAllOrderByUsernameAsc();
}
