package br.pucminas.doacoes.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.doacoes.domain.Interesse;
import br.pucminas.doacoes.dtos.InteresseDTO;
import br.pucminas.doacoes.repositories.InteresseRepository;

@Service
public class InteresseService {
	
	@Autowired
	private InteresseRepository repository;
	
	@Autowired
	private DoacaoService doacaoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OrfanatoService orfanatoService;
	
	public Interesse findById(Integer id) throws Exception {
        var interesseOpt = repository.findById(id);

        return interesseOpt
            .orElseThrow(() -> new Exception());
    }

	public List<Interesse> findAll(){
		return repository.findAll();
	}
	
	public void delete(Integer id) throws Exception {
		findById(id);
		repository.deleteById(id);	
	}
	
	/*public List<Doacao> findByFiltros(String descricao, String localRetirada, Integer quantidade, Integer idCategoria, Integer situacao) {

        List<Doacao> lista = null;

        if (!StringUtils.hasText(descricao) && !StringUtils.hasText(localRetirada) && quantidade == null && idCategoria == null && situacao == null) {
            lista = repository.findAll();
        } else {
            descricao = StringUtils.hasText(descricao) ? descricao.toLowerCase() : null;
            localRetirada = StringUtils.hasText(localRetirada) ? localRetirada.toLowerCase() : null;
            lista = repository.findByFiltros(descricao, localRetirada, quantidade, idCategoria, situacao);
            // lista = repository.findByFiltros(descricao);
        }
        return lista;
    }*/
	
	@Transactional
    public Interesse insert(InteresseDTO interesseDto) throws Exception {
        var interesse = new Interesse();

        // complementarDados(interesse, interesseDto);

        interesse.setIdDoacao(interesseDto.getIdDoacao());
		interesse.setIdOrfanatoInteressado(interesseDto.getIdOrfanatoInteressado());
		interesse.setIdUsuarioInteressado(interesseDto.getIdUsuarioInteressado());
		interesse.setDataDemonstracaoInteresse(LocalDateTime.now());
        repository.save(interesse);

        return interesse;
    }
	
	private Interesse complementarDados(Interesse interesse, InteresseDTO interesseDto) throws Exception {
		interesse.setIdDoacao(interesseDto.getIdDoacao());
		interesse.setIdOrfanatoInteressado(interesseDto.getIdOrfanatoInteressado());
		interesse.setIdUsuarioInteressado(interesseDto.getIdUsuarioInteressado());
//		System.out.println("InteresseDTO:" + interesseDto);
//		var doacao = doacaoService.findById(interesseDto.getIdDoacao());
//		var orfanatoInteressado = orfanatoService.findById(interesseDto.getIdOrfanatoInteressado());
//		var usuarioInteressado = usuarioService.findById(interesseDto.getIdUsuarioInteressado());
//		
//		interesse.setDoacao(doacao);
//		interesse.setOrfanatoInteressado(orfanatoInteressado);
//		interesse.setUsuarioInteressado(usuarioInteressado);
//		interesse.setId(doacao.getId() + usuarioInteressado.getId());
//		
//		System.out.println("Interesse:" + interesse);

        return interesse;
    }


	
	public static boolean jaExiste(Integer idEntidadeAlterada, Integer idRecuperadoBanco) {
        return idEntidadeAlterada == null || !idRecuperadoBanco.equals(idEntidadeAlterada);
    }

}
