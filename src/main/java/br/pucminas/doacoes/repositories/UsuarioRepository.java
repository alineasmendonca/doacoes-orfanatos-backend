package br.pucminas.doacoes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucminas.doacoes.domain.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u " +
            " where u.login like ( :login )")
    Optional<Usuario> findByLogin(@Param("login") String login);

    // Query method. Mesmo que -> select count(*) > 0 from user where username = :login
    boolean existsByLogin(String login);

    @Query("select u from Usuario u " +
            " order by u.login ")
    List<Usuario> findAllOrderByLoginAsc();
}
