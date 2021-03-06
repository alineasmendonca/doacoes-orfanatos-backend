package br.pucminas.doacoes.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pucminas.doacoes.domain.Categoria;
import br.pucminas.doacoes.dtos.CategoriaDTO;
import br.pucminas.doacoes.services.CategoriaService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id){
		Categoria obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> categorias = service.findAll();
		List<CategoriaDTO> categoriasDto = categorias.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriasDto);
	}*/
	
	@PostMapping
	public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria obj){
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO objDto){
		Categoria newObj = service.update(id, objDto);
		return ResponseEntity.ok().body(new CategoriaDTO(newObj));
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findByFiltros(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "descricao", required = false) String descricao) {
		List<Categoria> lista = service.findByFiltros(nome, descricao);
		List<CategoriaDTO> listaDTO = lista.stream().map(CategoriaDTO::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);
	}

}
