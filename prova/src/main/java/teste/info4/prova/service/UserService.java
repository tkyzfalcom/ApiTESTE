package teste.info4.prova.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import teste.info4.prova.model.UserModel;
import teste.info4.prova.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	@Autowired
	PasswordEncoder encoder;

	public List<UserModel> listar() {
		List<UserModel> usuarios = repository.findAll();
		return usuarios;

	}

	public ResponseEntity<UserModel> criar(UserModel usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	public ResponseEntity<Boolean>validarSenha(String login,String senha){
		Optional<UserModel> optUsuario = repository.findByLogin(login);
		if (optUsuario.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}

		UserModel usuario = optUsuario.get();
		Boolean valid = encoder.matches(senha, usuario.getSenha());

		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	
	}
}
