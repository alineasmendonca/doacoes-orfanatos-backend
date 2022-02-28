package br.pucminas.doacoes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.pucminas.doacoes.domain.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Integer> {

	@Query("SELECT obj from Doacao obj WHERE obj.categoria.id = :categoria_id ORDER BY descricao")
	List<Doacao> findAllByCategoria(@Param(value="categoria_id")Integer categoria_id);

}
