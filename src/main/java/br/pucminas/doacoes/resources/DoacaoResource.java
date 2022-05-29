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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.pucminas.doacoes.domain.Categoria;
import br.pucminas.doacoes.domain.Doacao;
import br.pucminas.doacoes.dtos.CategoriaDTO;
import br.pucminas.doacoes.dtos.DoacaoDTO;
import br.pucminas.doacoes.services.DoacaoService;

import org.springframework.validation.annotation.Validated;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/doacoes")
public class DoacaoResource {
	
	@Autowired
	private DoacaoService service;
	
	/*@GetMapping(value = "/{id}")
	public ResponseEntity<Doacao> findById(@PathVariable Integer id){
		Doacao obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}*/
	
	@GetMapping("/{id}")
    public ResponseEntity<DoacaoDTO> find(@PathVariable Integer id) throws Exception {
        Doacao doacao = service.findById(id);

        return ResponseEntity.ok(new DoacaoDTO(doacao));
    }
	
	
	/*@GetMapping
	public ResponseEntity<List<DoacaoDTO>> findAllByCategoria(@RequestParam(value="categoria", defaultValue = "0") Integer idCategoria){
		List<Doacao> doacoes = service.findAllByCategoria(idCategoria);
		List<DoacaoDTO> doacoesDto = doacoes.stream().map(obj -> new DoacaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(doacoesDto);
	}*/
	
	// Antes
	/*@PostMapping
	public ResponseEntity<Doacao> create(@RequestParam(value = "categoria", defaultValue =  "0") Integer categoria_id, 
			@RequestBody Doacao obj){
		Doacao newObj = service.create(categoria_id, obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/doacoes/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}*/
	
	@PostMapping
    public ResponseEntity<Doacao> insert(@Validated @RequestBody DoacaoDTO objDto) throws Exception {
        var tipo = service.insert(objDto);

        var uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(tipo.getId())
            .toUri();

        return ResponseEntity.created(uri).build();
    }
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<DoacaoDTO> update(@PathVariable Integer id, @Valid @RequestBody Doacao obj) throws Exception{
		Doacao newObj = service.update(id, obj);
		return ResponseEntity.ok().body(new DoacaoDTO(newObj));
		
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<DoacaoDTO> updatePatch(@PathVariable Integer id, @Valid  @RequestBody Doacao obj) throws Exception{
		Doacao newObj = service.update(id, obj);
		return ResponseEntity.ok().body(new DoacaoDTO(newObj));
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value= "/todas")
	public ResponseEntity<List<DoacaoDTO>> findAll(){
		List<Doacao> doacoes = service.findAll();
		List<DoacaoDTO> doacoesDto = doacoes.stream().map(obj -> new DoacaoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(doacoesDto);
	}
	
	
	@PostMapping(value= "/incluir")
	public ResponseEntity<Doacao> create(@Valid @RequestBody Doacao obj){
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<DoacaoDTO>> findByFiltros(@RequestParam(value = "descricao", required = false) String descricao,
			@RequestParam(value = "localRetirada", required = false) String localRetirada,
			@RequestParam(value = "quantidade", required = false) Integer quantidade,
			@RequestParam(value = "idCategoria", required = false) Integer idCategoria,
			@RequestParam(value = "situacao", required = false) Integer situacao) {
		List<Doacao> lista = service.findByFiltros(descricao, localRetirada, quantidade, idCategoria, situacao);
		List<DoacaoDTO> listaDTO = lista.stream().map(DoacaoDTO::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);
	}
	
	@PutMapping(value = "/{id}/liberar")
	public ResponseEntity<DoacaoDTO> liberar(@PathVariable Integer id, @Valid @RequestBody Doacao obj) throws Exception{
		Doacao newObj = service.liberar(id, obj);
		return ResponseEntity.ok().body(new DoacaoDTO(newObj));
		
	}
	
	@PutMapping(value = "/{id}/autorizar")
	public ResponseEntity<DoacaoDTO> autorizar(@PathVariable Integer id, @Valid @RequestBody Doacao obj) throws Exception{
		Doacao newObj = service.autorizar(id, obj);
		return ResponseEntity.ok().body(new DoacaoDTO(newObj));
		
	}


}
