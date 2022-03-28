package br.pucminas.doacoes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.pucminas.doacoes.domain.Categoria;
import br.pucminas.doacoes.dtos.CategoriaDTO;
import br.pucminas.doacoes.repositories.CategoriaRepository;
import br.pucminas.doacoes.services.exceptions.DataIntegrityViolationException;
import br.pucminas.doacoes.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll(){
		return repository.findAll();
	}
	
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Integer id, CategoriaDTO objDto) {
		Categoria obj = findById(id);
		obj.setNome(objDto.getNome());
		obj.setDescricao(objDto.getDescricao());
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
	
	public List<Categoria> findByFiltros(String nome, String descricao) {

        List<Categoria> lista = null;

        if (!StringUtils.hasText(nome) && !StringUtils.hasText(descricao)) {
            lista = repository.findAll();
        } else {
            nome = StringUtils.hasText(nome) ? nome.toLowerCase() : null;
            descricao = StringUtils.hasText(descricao) ? descricao.toLowerCase() : null;
            lista = repository.findByFiltros(nome, descricao);
        }
        return lista;
    }

}
