package teste.info4.prova.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import teste.info4.prova.model.UserModel;
import teste.info4.prova.repository.UserRepository;
import teste.info4.prova.service.UserService;

@RestController
@RequestMapping("/user")
public class UserControler {

	private final UserRepository repository;
	private final PasswordEncoder encoder;

	public UserControler(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@Autowired
	private UserService service;

	@GetMapping

	public List<UserModel> listar() {
		return service.listar();
	}

	@PostMapping("/criarUsuario")

	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserModel> criar(@RequestBody UserModel usuario) {
		return service.criar(usuario);

	}
	
	@GetMapping("/validarSenha")
	public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String senha){
		return service.validarSenha(login, senha);
	}

}
