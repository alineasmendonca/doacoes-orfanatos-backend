package br.pucminas.doacoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.doacoes.domain.Doacao;
import br.pucminas.doacoes.domain.Interesse;

@Repository
public interface InteresseRepository extends JpaRepository<Interesse, Integer> {

	/*@Query("SELECT obj from Doacao obj WHERE obj.categoria.id = :categoria_id ORDER BY descricao")
	List<Doacao> findAllByCategoria(@Param(value="categoria_id")Integer categoria_id);
	
	@Query("select d from Doacao d where (:descricao is null or lower(d.descricao) like %:descricao%) "
			+ " and (:localRetirada is null or lower(d.localRetirada) like %:localRetirada%) "
			+ " and (:quantidade is null or d.quantidade = :quantidade) "
			+ " and (:idCategoria is null or d.categoria.id = :idCategoria) "
			+ " and (:situacao is null or d.situacao = :situacao) "
	        + " order by lower(d.descricao)")
	@Transactional(readOnly = true)
	List<Interesse> findByFiltros(@Param("descricao") String descricao, @Param("localRetirada") String localRetirada, @Param("quantidade") Integer quantidade, @Param("idCategoria") Integer idCategoria, @Param("situacao") Integer situacao);
	
	Interesse findByDescricaoIgnoreCase(String descricao);*/

}
