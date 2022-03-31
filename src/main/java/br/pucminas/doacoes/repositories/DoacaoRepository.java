package br.pucminas.doacoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.pucminas.doacoes.domain.Categoria;
import br.pucminas.doacoes.domain.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

	@Query("SELECT obj from Doacao obj WHERE obj.categoria.id = :categoria_id ORDER BY descricao")
	List<Doacao> findAllByCategoria(@Param(value="categoria_id")Integer categoria_id);
	
	/*@Query("select d from Doacao d where (:descricao is null or lower(d.descricao) like %:descricao%) "
	        + " and (:quantidade is null or d.quandidade = :quantidade) "
	        + " order by lower(d.descricao)")
	@Transactional(readOnly = true)
	List<Doacao> findByFiltros(@Param("descricao") String descricao, @Param("quantidade") Integer quantidade);*/
	
	/*@Query("select d from Doacao d where (:descricao is null or lower(d.descricao) like %:descricao%) "
	        + " order by lower(d.descricao)")
	@Transactional(readOnly = true)
	List<Doacao> findByFiltros(@Param("descricao") String descricao);*/
	
	@Query("select d from Doacao d where (:descricao is null or lower(d.descricao) like %:descricao%) "
			+ " and (:quantidade is null or d.quantidade = :quantidade) "
			+ " and (:idCategoria is null or d.categoria.id = :idCategoria) "
	        + " order by lower(d.descricao)")
	@Transactional(readOnly = true)
	List<Doacao> findByFiltros(@Param("descricao") String descricao, @Param("quantidade") Integer quantidade, @Param("idCategoria") Integer idCategoria);
	
	Doacao findByDescricaoIgnoreCase(String descricao);

}
