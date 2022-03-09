package br.pucminas.doacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.pucminas.doacoes.services.DBService;
import br.pucminas.doacoes.services.UsuarioService;

@Configuration
@Profile("dev")
public class DevConfig {

    /*@Autowired
    private UsuarioService appUserService;*/
    
    @Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instanciaBaseDeDados() {
		if(strategy.equals("create")) {
			this.dbService.instanciaBaseDeDados();
		}
		return false;
	}

    /*@Bean
    public CommandLineRunner executar(){

        appUserService.createUserAdmin();

        return args -> {
            System.out.println("Rodando a aplicação em ambiente produção");
        };
    }*/


}
