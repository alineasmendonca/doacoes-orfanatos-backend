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
	
	/*@Query("select o from Orfanato o LEFT JOIN Interesse i where (i.idDoacao = :idDoacao) "
			+ " and o.id = i.idOrfanatoInteressado")*/
	@Query("SELECT o FROM Orfanato o LEFT JOIN Interesse i ON (o.id = i.idOrfanatoInteressado) "
			+ " WHERE (i.idDoacao = :idDoacao)")
	@Transactional(readOnly = true)
	List<Orfanato> buscarOrfanatosInteressadosPorUmaDoacao(@Param("idDoacao") Integer idDoacao);
	
	@Query("SELECT b FROM Orfanato b WHERE b.quantidadeCriancas IN (SELECT MAX(quantidadeCriancas) from Orfanato a"
			+ " WHERE a IN (SELECT o FROM Orfanato o LEFT JOIN Interesse i ON (o.id = i.idOrfanatoInteressado) "
			+ " WHERE (i.idDoacao = :idDoacao))) ORDER BY DATE(b.dataFundacao) ASC")
	@Transactional(readOnly = true)
	List<Orfanato> recuperarOrfanatoContemplado(@Param("idDoacao") Integer idDoacao);

	
	Orfanato findByNomeIgnoreCase(String nome);
	
    /*@Query("select new br.gov.serpro.siseco.conformidade.arquivo.ArquivoConformidadeDemandaDTO( "
            + "   a.id, a.nomeArquivoOriginal, a.nomeArquivoFisico, a.identificadorArquivo, a.tipo, "
            + "   a.modeloConformidade.nome, u.id, u.nome, a.favorecido.id, a.favorecido.codigoFavorecido, a.favorecido.nome, "
            + "   a.dataEnvio, a.dataEnvioConformidade, a.codigoReprocessamento, a.motivoFalhaReprocessamento) "
            + "from ArquivoConformidade a "
            + "   LEFT JOIN a.usuarioDesignado u "
            + "where a.id = :idArquivo ")*/

}
