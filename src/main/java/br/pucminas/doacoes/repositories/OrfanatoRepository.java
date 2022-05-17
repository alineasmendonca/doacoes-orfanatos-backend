package br.pucminas.doacoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.doacoes.domain.Orfanato;

@Repository
public interface OrfanatoRepository extends JpaRepository<Orfanato, Integer> {

	@Query("select o from Orfanato o where (:nome is null or lower(o.nome) like %:nome%) "
			+ " and (:quantidadeCriancas is null or o.quantidadeCriancas = :quantidadeCriancas) "
			+ " and (:historia is null or lower(o.historia) like %:historia%) "
			+ " and (:endereco is null or lower(o.endereco) like %:endereco%) "
	        + " order by lower(o.nome)")
	@Transactional(readOnly = true)
	List<Orfanato> findByFiltros(@Param("nome") String nome, @Param("quantidadeCriancas") Integer quantidadeCriancas, @Param("historia") String historia, @Param("endereco") String endereco);
	
	Orfanato findByNomeIgnoreCase(String nome);

}
