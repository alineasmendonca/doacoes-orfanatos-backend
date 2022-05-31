package br.pucminas.doacoes.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import br.pucminas.doacoes.domain.Orfanato;
import br.pucminas.doacoes.dtos.OrfanatoDTO;
import br.pucminas.doacoes.services.OrfanatoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/orfanatos")
public class OrfanatoResource {
	
	@Autowired
	private OrfanatoService service;
	
	
	@GetMapping("/{id}")
    public ResponseEntity<OrfanatoDTO> find(@PathVariable Integer id) throws Exception {
        Orfanato orfanato = service.findById(id);

        return ResponseEntity.ok(new OrfanatoDTO(orfanato));
    }
	
	@PostMapping
    public ResponseEntity<Orfanato> insert(@Validated @RequestBody OrfanatoDTO objDto) throws Exception {
        var tipo = service.insert(objDto);

        var uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(tipo.getId())
            .toUri();

        return ResponseEntity.created(uri).build();
    }
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OrfanatoDTO> update(@PathVariable Integer id, @Valid @RequestBody Orfanato obj) throws Exception{
		Orfanato newObj = service.update(id, obj);
		return ResponseEntity.ok().body(new OrfanatoDTO(newObj));
		
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<OrfanatoDTO> updatePatch(@PathVariable Integer id, @Valid  @RequestBody Orfanato obj) throws Exception{
		Orfanato newObj = service.update(id, obj);
		return ResponseEntity.ok().body(new OrfanatoDTO(newObj));
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value= "/todas")
	public ResponseEntity<List<OrfanatoDTO>> findAll(){
		List<Orfanato> orfanatos = service.findAll();
		List<OrfanatoDTO> orfanatosDto = orfanatos.stream().map(obj -> new OrfanatoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(orfanatosDto);
	}
	
	
	@PostMapping(value= "/incluir")
	public ResponseEntity<Orfanato> create(@Valid @RequestBody Orfanato obj){
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<OrfanatoDTO>> findByFiltros(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "quantidadeCriancas", required = false) Integer quantidadeCriancas,
			@RequestParam(value = "historia", required = false) String historia,
			@RequestParam(value = "endereco", required = false) String endereco) {
		List<Orfanato> lista = service.findByFiltros(nome, quantidadeCriancas, historia, endereco);
		List<OrfanatoDTO> listaDTO = lista.stream().map(OrfanatoDTO::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);
	}
	
	@GetMapping("/interessados")
	public ResponseEntity<List<OrfanatoDTO>> buscarOrfanatosInteressadosPorUmaDoacao(@RequestParam(value = "idDoacao", required = false) Integer idDoacao) {
		List<Orfanato> lista = service.buscarOrfanatosInteressadosPorUmaDoacao(idDoacao);
		List<OrfanatoDTO> listaDTO = lista.stream().map(OrfanatoDTO::new).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaDTO);
	}
	
	@GetMapping("/orfanatocontemplado")
	public ResponseEntity<List<OrfanatoDTO>> recuperarOrfanatoContemplado(@RequestParam(value = "idDoacao", required = false) Integer idDoacao) {
		List<Orfanato> lista = service.recuperarOrfanatoContemplado(idDoacao);
		List<OrfanatoDTO> listaDTO = lista.stream().map(OrfanatoDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}


}
