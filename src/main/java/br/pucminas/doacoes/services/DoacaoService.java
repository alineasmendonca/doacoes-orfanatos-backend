package br.pucminas.doacoes.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.doacoes.domain.Categoria;
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

	public Doacao update(Integer id, Doacao obj) {
		Doacao newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Doacao newObj, Doacao obj) {
		newObj.setCategoria(obj.getCategoria());
		newObj.setQuantidade(obj.getQuantidade());
		newObj.setDescricao(obj.getDescricao());
		newObj.setDataAutorizacao(obj.getDataAutorizacao());
		newObj.setDataLiberacao(obj.getDataLiberacao());
		newObj.setDataCadastro(obj.getDataCadastro());
		newObj.setIdDoador(obj.getIdDoador());
		newObj.setIdOrfanatoContemplado(obj.getIdOrfanatoContemplado());
		
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

	public Doacao create(Integer categoria_id, Doacao obj) {
		obj.setId(null);
		Categoria cat = categoriaService.findById(categoria_id);
		obj.setCategoria(cat);
		return repository.save(obj);
	}

	
	/*public Doacao create(Integer categoria_id, DoacaoDTO objDto) {
		// TODO Auto-generated method stub
		Categoria cat = categoriaService.findById(categoria_id);
		Doacao doacao = new Doacao(null, objDto.getQuantidade(), objDto.getDescricao(), cat, new Date(),
				null, null, null, null);
		
		return repository.save(doacao);
	}*/

}
