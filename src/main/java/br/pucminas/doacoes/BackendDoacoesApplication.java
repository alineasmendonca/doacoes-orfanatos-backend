package br.pucminas.doacoes;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import br.pucminas.doacoes.domain.Categoria;
import br.pucminas.doacoes.domain.Doacao;
import br.pucminas.doacoes.repositories.CategoriaRepository;
import br.pucminas.doacoes.repositories.DoacaoRepository;

@SpringBootApplication
@RestController
public class BackendDoacoesApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BackendDoacoesApplication.class, args);
	}
}


/*@SpringBootApplication
public class BackendDoacoesApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private DoacaoRepository doacaoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(BackendDoacoesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Alimento", "Categoria de Alimentos");
		Categoria cat2 = new Categoria(null, "Roupa", "Categoria de Roupas");
		Categoria cat3 = new Categoria(null, "Dinheiro", "Categoria de Dinheiro");
		
		Doacao doacao1 = new Doacao(null, 2, "Lata de Leitem NAM 1", cat1, null, null, null, null, null);
		Doacao doacao2 = new Doacao(null, 5, "Bodys tamanho RN", cat2, null, null, null, null, null);
		Doacao doacao3 = new Doacao(null, 100, "Doação em dinheiro", cat3, null, null, null, null, null);
		
		cat1.getDoacoes().addAll(Arrays.asList(doacao1));
		cat2.getDoacoes().addAll(Arrays.asList(doacao2));
		cat3.getDoacoes().addAll(Arrays.asList(doacao3));
		
		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		this.doacaoRepository.saveAll(Arrays.asList(doacao1, doacao2, doacao3));
		
		
	}
}*/
