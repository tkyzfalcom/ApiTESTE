package teste.info4.prova.jwt.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import teste.info4.prova.jwt.data.DetalheUserData;
import teste.info4.prova.model.UserModel;

public class jwtAuthenticarFilter extends UsernamePasswordAuthenticationFilter {
	public static final int TOKEN_EXPIRACAO = 600_000;
	public static final String TOKEN_SENHA = "c5a73aa0-fdc6-4f8e-a277-42096794246c";

	private final AuthenticationManager authenticationManager;

	public jwtAuthenticarFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub

		try {
			UserModel usuario = new ObjectMapper().readValue(request.getInputStream(), UserModel.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException("Falha ao autenticar usuario", e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		DetalheUserData userData=(DetalheUserData) authResult.getPrincipal();
		
		String token = JWT.create().withSubject(userData.getUsername()).withExpiresAt(new Date(System.currentTimeMillis()+TOKEN_EXPIRACAO))
				.sign(Algorithm.HMAC512(TOKEN_SENHA));
		
		response.getWriter().write(token);
		response.getWriter().flush();
	}
	

}
