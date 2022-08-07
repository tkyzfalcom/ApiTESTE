package teste.info4.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import teste.info4.prova.model.ProdutoModel;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {

	@Query(value = "select * from produtos_model im where tipo=:tipo",nativeQuery = true)
	List<ProdutoModel> tipo (String tipo);
	
}
