package br.pucminas.doacoes.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.pucminas.doacoes.domain.Categoria;
import br.pucminas.doacoes.domain.Doacao;
import br.pucminas.doacoes.domain.Orfanato;
import br.pucminas.doacoes.dtos.DoacaoDTO;
import br.pucminas.doacoes.enums.SituacaoDoacao;
import br.pucminas.doacoes.repositories.DoacaoRepository;

@Service
public class DoacaoService {
	
	@Autowired
	private DoacaoRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OrfanatoService orfanatoService;
	
	/*public Doacao findById(Integer id) {
		Optional<Doacao> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Doacao.class.getName()));
	}*/

	public Doacao findById(Integer id) throws Exception {
        var doacaoOpt = repository.findById(id);

        return doacaoOpt
            .orElseThrow(() -> new Exception());
    }
	
	public List<Doacao> findAll(){
		return repository.findAll();
	}
	
	public Doacao create(Doacao obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Doacao update(Integer id, Doacao obj) throws Exception {
		Doacao newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Doacao newObj, Doacao obj) {
		var categoria = categoriaService.findById(obj.getIdCategoria());
		
		newObj.setIdCategoria(obj.getIdCategoria());
		newObj.setCategoria(categoria);
		newObj.setQuantidade(obj.getQuantidade());
		newObj.setDescricao(obj.getDescricao());
		newObj.setDataAutorizacao(obj.getDataAutorizacao());
		newObj.setDataLiberacao(obj.getDataLiberacao());
		// newObj.setIdDoador(obj.getIdDoador());
		newObj.setIdOrfanatoContemplado(obj.getIdOrfanatoContemplado());
		newObj.setLocalRetirada(obj.getLocalRetirada());
	}

	public void delete(Integer id) throws Exception {
		findById(id);
		repository.deleteById(id);	
//		try {
//			repository.deleteById(id);	
//		} catch (DataIntegrityViolationException e) {
//			throw new DataIntegrityViolationException("Categoria não pode ser deletada. Ela possui doações associadas.");
//		}
		
	}

	public List<Doacao> findAllByCategoria(Integer categoria_id) {
		categoriaService.findById(categoria_id);
		return repository.findAllByCategoria(categoria_id);
	}

	public Doacao create(Integer categoria_id, Doacao obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(categoria_id);
		obj.setCategoria(cat);
		return repository.save(obj);
	}
	
	public List<Doacao> findByFiltros(String descricao, String localRetirada, Integer quantidade, Integer idCategoria, Integer situacao) {

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
    }
	
	@Transactional
    public Doacao insert(DoacaoDTO doacaoDto) throws Exception {
        // verificarExistencia(null, doacaoDto);

        var doacao = new Doacao();

        complementarDados(doacao, doacaoDto);

        repository.save(doacao);

        return doacao;
    }
	
	private Doacao complementarDados(Doacao doacao, DoacaoDTO doacaoDto) {
		var categoria = categoriaService.findById(doacaoDto.getIdCategoria());
		var doador = usuarioService.findById(doacaoDto.getIdDoador());
		
		doacao.setIdCategoria(categoria.getId());
		doacao.setCategoria(categoria);
		doacao.setDescricao(doacaoDto.getDescricao());
		doacao.setQuantidade(doacaoDto.getQuantidade());
		doacao.setLocalRetirada(doacaoDto.getLocalRetirada());
		doacao.setDataCadastro(LocalDateTime.now());
		doacao.setSituacao(SituacaoDoacao.CRIADA.getCod());
		
		doacao.setDoador(doador);

        return doacao;
    }

	
	private void verificarExistencia(Integer idDoacaoAlterada, DoacaoDTO objDto) throws Exception {
        var doacao = repository.findByDescricaoIgnoreCase(objDto.getDescricao());
        if (doacao != null
            && jaExiste(idDoacaoAlterada, doacao.getId())) {
            throw new Exception("Doação já cadastrada");
        }
    }
	
	public static boolean jaExiste(Integer idEntidadeAlterada, Integer idRecuperadoBanco) {
        return idEntidadeAlterada == null || !idRecuperadoBanco.equals(idEntidadeAlterada);
    }
	
	
	public Doacao liberar(Integer id, Doacao obj) throws Exception {
		Doacao newObj = findById(id);
		// updateData(newObj, obj);
		newObj.setSituacao(SituacaoDoacao.LIBERADA.getCod());
		newObj.setDataLiberacao(LocalDateTime.now());
		return repository.save(newObj);
	}
	
	public Doacao autorizar(Integer id, Doacao obj) throws Exception {
		Doacao newObj = findById(id);
		// updateData(newObj, obj);
		newObj.setSituacao(SituacaoDoacao.AUTORIZADA.getCod());
		newObj.setDataAutorizacao(LocalDateTime.now());
		
		List<Orfanato> orfanatos = orfanatoService.recuperarOrfanatoContemplado(id);
		newObj.setIdOrfanatoContemplado(orfanatos.get(0).getId());
		return repository.save(newObj);
	}
	

	
	/*public Doacao create(Integer categoria_id, DoacaoDTO objDto) {
		// TODO Auto-generated method stub
		Categoria cat = categoriaService.findById(categoria_id);
		Doacao doacao = new Doacao(null, objDto.getQuantidade(), objDto.getDescricao(), cat, new Date(),
				null, null, null, null);
		
		return repository.save(doacao);
	}*/

}
