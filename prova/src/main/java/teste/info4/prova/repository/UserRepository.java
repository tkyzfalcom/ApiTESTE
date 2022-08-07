package teste.info4.prova.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import teste.info4.prova.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
	Optional<UserModel> findByLogin(String login);

}
