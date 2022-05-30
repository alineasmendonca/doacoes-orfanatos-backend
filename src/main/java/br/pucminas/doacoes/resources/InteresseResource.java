package br.pucminas.doacoes.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pucminas.doacoes.domain.Interesse;
import br.pucminas.doacoes.dtos.InteresseDTO;
import br.pucminas.doacoes.services.InteresseService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/interesses")
public class InteresseResource {
	
	@Autowired
	private InteresseService service;
	
	@GetMapping("/{id}")
    public ResponseEntity<InteresseDTO> find(@PathVariable Integer id) throws Exception {
        Interesse interesse = service.findById(id);

        return ResponseEntity.ok(new InteresseDTO(interesse));
    }
	
	@PostMapping
    public ResponseEntity<Interesse> insert(@Validated @RequestBody InteresseDTO objDto) throws Exception {
        var tipo = service.insert(objDto);
        
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tipo.getId())
                .toUri();

            return ResponseEntity.created(uri).build();
    }

	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<InteresseDTO>> findAll(){
		List<Interesse> interesses = service.findAll();
		List<InteresseDTO> interessesDto = interesses.stream().map(obj -> new InteresseDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(interessesDto);
	}
	
	
	/*@GetMapping
	public ResponseEntity<List<DoacaoDTO>> findByFiltros(@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "localRetirada", required = false) String localRetirada,
			@RequestParam(value = "quantidade", required = false) Integer quantidade,
			@RequestParam(value = "idCategoria", required = false) Integer idCategoria,
			@RequestParam(value = "situacao", required = false) Integer situacao) {
		List<Doacao> lista = service.findByFiltros(descricao, localRetirada, quantidade, idCategoria, situacao);
		List<DoacaoDTO> listaDTO = lista.stream().map(DoacaoDTO::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);
	}*/

}
