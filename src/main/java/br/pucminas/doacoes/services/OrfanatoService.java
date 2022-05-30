package br.pucminas.doacoes.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.pucminas.doacoes.domain.Orfanato;
import br.pucminas.doacoes.dtos.OrfanatoDTO;
import br.pucminas.doacoes.repositories.OrfanatoRepository;

@Service
public class OrfanatoService {
	
	@Autowired
	private OrfanatoRepository repository;

	public Orfanato findById(Integer id) throws Exception {
        var orfanatoOpt = repository.findById(id);

        return orfanatoOpt
            .orElseThrow(() -> new Exception());
    }
	
	public List<Orfanato> findAll(){
		return repository.findAll();
	}
	
	public Orfanato create(Orfanato obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Orfanato update(Integer id, Orfanato obj) throws Exception {
		Orfanato newObj = findById(id);
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Orfanato newObj, Orfanato obj) {
		newObj.setDataFundacao(obj.getDataFundacao());
		newObj.setEndereco(obj.getEndereco());
		newObj.setHistoria(obj.getHistoria());
		newObj.setNome(obj.getNome());
		newObj.setQuantidadeCriancas(obj.getQuantidadeCriancas());
		newObj.setTelefone(obj.getTelefone());
		
	}

	public void delete(Integer id) throws Exception {
		findById(id);
		repository.deleteById(id);	
	}

	
	public List<Orfanato> findByFiltros(String nome, Integer quantidadeCriancas, String historia, String endereco) {

        List<Orfanato> lista = null;

        if (!StringUtils.hasText(nome) && quantidadeCriancas == null && !StringUtils.hasText(historia) && !StringUtils.hasText(endereco)) {
            lista = repository.findAll();
        } else {
            nome = StringUtils.hasText(nome) ? nome.toLowerCase() : null;
            historia = StringUtils.hasText(historia) ? historia.toLowerCase() : null;
            endereco = StringUtils.hasText(endereco) ? endereco.toLowerCase() : null;
            lista = repository.findByFiltros(nome, quantidadeCriancas, historia, endereco);
        }
        return lista;
    }
	
	public List<Orfanato> buscarOrfanatosInteressadosPorUmaDoacao(Integer idDoacao) {

        List<Orfanato> lista = null;

        if (idDoacao == null) {
            lista = repository.findAll();
        } else {
            lista = repository.buscarOrfanatosInteressadosPorUmaDoacao(idDoacao);
        }
        return lista;
    }
	
	@Transactional
    public Orfanato insert(OrfanatoDTO orfanatoDTO) throws Exception {
        verificarExistencia(null, orfanatoDTO);

        var orfanato = new Orfanato();

        complementarDados(orfanato, orfanatoDTO);

        repository.save(orfanato);

        return orfanato;
    }
	
	private Orfanato complementarDados(Orfanato orfanato, OrfanatoDTO orfanatoDTO) {
		// var categoria = categoriaService.findById(orfanatoDTO.getIdCategoria());
		
		orfanato.setNome(orfanatoDTO.getNome());
		orfanato.setQuantidadeCriancas(orfanatoDTO.getQuantidadeCriancas());
		orfanato.setEndereco(orfanatoDTO.getEndereco());
		orfanato.setHistoria(orfanatoDTO.getHistoria());
		orfanato.setDataFundacao(orfanatoDTO.getDataFundacao());
		orfanato.setTelefone(orfanatoDTO.getTelefone());

        return orfanato;
    }

	
	private void verificarExistencia(Integer idOrfanatoAlterado, OrfanatoDTO objDto) throws Exception {
        var orfanato = repository.findByNomeIgnoreCase(objDto.getNome());
        if (orfanato != null
            && jaExiste(idOrfanatoAlterado, orfanato.getId())) {
            throw new Exception("Orfanato já cadastrado");
        }
    }
	
	public static boolean jaExiste(Integer idEntidadeAlterada, Integer idRecuperadoBanco) {
        return idEntidadeAlterada == null || !idRecuperadoBanco.equals(idEntidadeAlterada);
    }
}
