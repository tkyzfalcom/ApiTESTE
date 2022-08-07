package teste.info4.prova.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import teste.info4.prova.jwt.data.DetalheUserData;
import teste.info4.prova.model.UserModel;
import teste.info4.prova.repository.UserRepository;

@Component
public class DetalheUserServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserModel> user = repository.findByLogin(username);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("Usuario" +username+" nao encontrado");
		}
		return new DetalheUserData(user);
	}

}
