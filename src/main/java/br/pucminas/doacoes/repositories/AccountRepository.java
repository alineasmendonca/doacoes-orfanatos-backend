package br.pucminas.doacoes.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.pucminas.doacoes.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(" select a from Account a " +
            " join a.appUser u " +
            " where u.username like ( :username )" +
            " order by a.name ")
    List<Account> findAllByUsernameOrderByNameAsc(@Param("username") String username);
}

