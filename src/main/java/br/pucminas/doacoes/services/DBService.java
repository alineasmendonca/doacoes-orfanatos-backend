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
import br.pucminas.doacoes.repositories.UsuarioRepository;

@Service
public class DBService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void instanciaBaseDeDados(){
		Categoria cat1 = new Categoria(null, "Alimento", "Categoria de Alimentos");
		Categoria cat2 = new Categoria(null, "Roupa", "Categoria de Roupas");
		Categoria cat3 = new Categoria(null, "Dinheiro", "Categoria de Dinheiro");
		Categoria cat4 = new Categoria(null, "Utensílios", "Categoria de Utensílios");
		Categoria cat5 = new Categoria(null, "Material Escolar", "Categoria de Materiais Escolares");
	
        Doacao doacao1 = new Doacao(null, 2, "Lata de Leitem NAM 1", cat1, null, null, null, null, null);
		Doacao doacao2 = new Doacao(null, 5, "Bodys tamanho RN", cat2, null, null, null, null, null);
		Doacao doacao3 = new Doacao(null, 100, "Doação em dinheiro", cat3, null, null, null, null, null);
		Doacao doacao4 = new Doacao(null, 3, "Mamadeiras", cat4, null, null, null, null, null);
		Doacao doacao5 = new Doacao(null, 7, "Lápis", cat5, null, null, null, null, null);
		
		Usuario usuario1 = new Usuario(1, "alineas", "Aline Alves da Silva Mendonça", "(85)999999999", "aline@gmail.com", "12345", true);
		
		cat1.getDoacoes().addAll(Arrays.asList(doacao1));
		cat2.getDoacoes().addAll(Arrays.asList(doacao2));
		cat3.getDoacoes().addAll(Arrays.asList(doacao3));
		cat4.getDoacoes().addAll(Arrays.asList(doacao4));
		cat5.getDoacoes().addAll(Arrays.asList(doacao5));
		
        this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
        this.doacaoRepository.saveAll(Arrays.asList(doacao1, doacao2, doacao3, doacao4, doacao5));	
        this.usuarioRepository.saveAll(Arrays.asList(usuario1));
		
	}

}
