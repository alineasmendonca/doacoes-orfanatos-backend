package br.pucminas.doacoes.services;

import java.util.Arrays;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.pucminas.doacoes.domain.Categoria;
import br.pucminas.doacoes.domain.Doacao;
import br.pucminas.doacoes.domain.Usuario;
import br.pucminas.doacoes.repositories.CategoriaRepository;
import br.pucminas.doacoes.repositories.DoacaoRepository;
import br.pucminas.doacoes.repositories.OrfanatoRepository;
import br.pucminas.doacoes.repositories.UsuarioRepository;

@Service
public class DBService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private OrfanatoRepository orfanatoRepository;

	public void instanciaBaseDeDados(){
		Categoria cat1 = new Categoria(null, "Alimento", "Categoria de Alimentos");
		Categoria cat2 = new Categoria(null, "Roupa", "Categoria de Roupas");
		Categoria cat3 = new Categoria(null, "Dinheiro", "Categoria de Dinheiro");
		Categoria cat4 = new Categoria(null, "Utensílios", "Categoria de Utensílios");
		Categoria cat5 = new Categoria(null, "Material Escolar", "Categoria de Materiais Escolares");
	
        Doacao doacao1 = new Doacao(null, 2, "Lata de Leitem NAM 1", null, cat1, "Avenida Pontes Vieira 832", null, null, null, null, null);
		Doacao doacao2 = new Doacao(null, 5, "Bodys tamanho RN", null, cat2, "Avenida Pontes Vieira 832", null, null, null, null, null);
		Doacao doacao3 = new Doacao(null, 100, "Doação em dinheiro", null, cat3, "Avenida Pontes Vieira 832", null, null, null, null, null);
		Doacao doacao4 = new Doacao(null, 3, "Mamadeiras", null, cat4, "Avenida Pontes Vieira 832", null, null, null, null, null);
		Doacao doacao5 = new Doacao(null, 7, "Lápis", null, cat5, "Avenida Pontes Vieira 832", null, null, null, null, null);
		
//		Usuario usuario1 = new Usuario(1, "aline", "Aline Alves da Silva Mendonça", "(85)999999999", "aline@gmail.com", "aline", true, "(85)999999999", "Avenida Pontes Vieira 100", 1);
//		Usuario usuario2 = new Usuario(2, "administrador", "Usuário Administrador do Sistema", "(85)988888888", "administrador@gmail.com", "administrador", true, "(85)999999999",  "Avenida Pontes Vieira 200", 1);
//		Usuario usuario3 = new Usuario(3, "doador", "Usuário Doador", "(85)977777777", "doador@gmail.com", "doador", false, "(85)999999999",  "Avenida Pontes Vieira 300", 2);
//		Usuario usuario4 = new Usuario(4, "orfanato", "Usuário Representante de Orfanato", "(85)966666666", "orfanato@gmail.com", "orfanato", false, "(85)999999999",  "Avenida Pontes Vieira 400", 3);
//		Usuario usuario5 = new Usuario(2, "admin", "Usuário Administrador do Sistema", "(85)988888888", "admin@gmail.com", "admin", true, "(85)999999999",  "Avenida Pontes Vieira 500", 1);
		
		cat1.getDoacoes().addAll(Arrays.asList(doacao1));
		cat2.getDoacoes().addAll(Arrays.asList(doacao2));
		cat3.getDoacoes().addAll(Arrays.asList(doacao3));
		cat4.getDoacoes().addAll(Arrays.asList(doacao4));
		cat5.getDoacoes().addAll(Arrays.asList(doacao5));
		
        this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
        this.doacaoRepository.saveAll(Arrays.asList(doacao1, doacao2, doacao3, doacao4, doacao5));	
        // this.usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3, usuario4, usuario5));
		
	}

}
