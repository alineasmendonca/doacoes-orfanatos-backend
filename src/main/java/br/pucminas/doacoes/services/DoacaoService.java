package br.pucminas.doacoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.doacoes.domain.Doacao;
import br.pucminas.doacoes.dtos.DoacaoDTO;
import br.pucminas.doacoes.repositories.DoacaoRepository;
import br.pucminas.doacoes.services.exceptions.ObjectNotFoundException;

@Service
public class DoacaoService {
	
	@Autowired
	private DoacaoRepository repository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public Doacao findById(Integer id) {
		Optional<Doacao> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Doacao.class.getName()));
	}
	
	public List<Doacao> findAll(){
		return repository.findAll();
	}
	
	public Doacao create(Doacao obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Doacao update(Integer id, DoacaoDTO objDto) {
		Doacao obj = findById(id);
		obj.setCategoria(objDto.getCategoria());
		obj.setDescricao(objDto.getDescricao());
		obj.setDataAutorizacao(objDto.getDataAutorizacao());
		obj.setDataLiberacao(objDto.getDataLiberacao());
		obj.setDataCadastro(objDto.getDataCadastro());
		obj.setIdDoador(objDto.getIdDoador());
		obj.setIdOrfanatoContemplado(objDto.getIdOrfanatoContemplado());
		return repository.save(obj);
	}

	public void delete(Integer id) {
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

}
