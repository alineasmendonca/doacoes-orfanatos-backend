package br.pucminas.doacoes.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.pucminas.doacoes.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    @Query("select c from Conta c " +
            " join c.usuario u " +
            " where u.login like ( :login )" +
            " order by c.name ")
    List<Conta> findAllByLoginOrderByNameAsc(@Param("login") String login);
}

