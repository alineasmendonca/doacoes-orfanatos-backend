package br.pucminas.doacoes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.pucminas.doacoes.services.UsuarioService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService appUserService;
    
    // Tentativa 2
    /*@Autowired
	AuthenticationSuccessHandler successHandler;*/

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(appUserService)
                .passwordEncoder(passwordEncoder());

        /*auth
                .inMemoryAuthentication()
                .withUser("fulano")
                .password("123")
                .roles("USER");*/
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// Original
    	http
        .csrf().disable()
        .cors()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	// Tentativa 3
    	/* http.cors().and().csrf().disable();*/
    	
    	// Tentativa 2
    	/*http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin/dashboard").hasAnyRole("ADMIN")
		.antMatchers("/user/dashboard").hasAnyRole("USER")
		.and().formLogin().loginPage("/login")
			.successHandler(successHandler)
		.permitAll()
		.and().logout()
		.and().exceptionHandling().accessDeniedPage("/accessdenied");*/
    }

   @Bean
   public PasswordEncoder passwordEncoder(){
       return NoOpPasswordEncoder.getInstance();
   }
    
   
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }*/
    
}
