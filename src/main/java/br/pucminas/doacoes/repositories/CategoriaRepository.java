package br.pucminas.doacoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.doacoes.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	@Query("select c from Categoria c where (:nome is null or lower(c.nome) like %:nome%) "
	        + " and (:descricao is null or lower(c.descricao) like %:descricao%) "
	        + " order by lower(c.nome)")
	    @Transactional(readOnly = true)
	    List<Categoria> findByFiltros(@Param("nome") String nome, @Param("descricao") String descricao);


}
