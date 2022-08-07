package teste.info4.prova.jwt.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import teste.info4.prova.jwt.DetalheUserServiceImpl;

@Configuration
@EnableWebSecurity
public class jwtConfiguracao extends WebSecurityConfigurerAdapter{
	 
	private final DetalheUserServiceImpl impService;
	private final PasswordEncoder passwordEncoder;
	 
	

	public jwtConfiguracao(DetalheUserServiceImpl impService, PasswordEncoder passwordEncoder) {
		
		this.impService = impService;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(impService).passwordEncoder(passwordEncoder);
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,"/login","/criarUsuario").permitAll()
		//.anyRequest().authenticated()
		.and().addFilter(new jwtAuthenticarFilter(authenticationManager())).addFilter(new jwtValidarFilter(authenticationManager())).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
//	@Bean
//	public CorsConfigurationSource corsConfiguration() {
//		final UrlBasedCorsConfigurationSource source =new UrlBasedCorsConfigurationSource();
//		
//		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		
//		return source;
//	}

	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8080/"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
	// 	source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}
	

}
