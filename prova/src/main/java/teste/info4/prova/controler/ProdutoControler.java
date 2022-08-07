package teste.info4.prova.controler;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import teste.info4.prova.dto.ProdutoDTO;
import teste.info4.prova.exception.NoSuchElementFoundException;
import teste.info4.prova.model.ProdutoModel;
import teste.info4.prova.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoControler {
	@Autowired
	ProdutoService service;

	@GetMapping
	public ResponseEntity<List<ProdutoModel>> findAll() throws NoSuchElementFoundException {
		List<ProdutoModel> produtosModels = service.findAll();

		if (produtosModels.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum Produto encontrado.");
		} else {

			return new ResponseEntity<>(produtosModels, HttpStatus.OK);
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModel> findInfluencerById(@PathVariable Integer id) throws NoSuchElementFoundException {
		ProdutoModel prod = service.findById(id);
		if (null == prod)
			throw new NoSuchElementFoundException("Não foi possivel encontrar o produto desejado com o codigo " + id);
		else
			return new ResponseEntity<>(prod, HttpStatus.OK);
	}
	
	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProdutoModel> save(@Valid @RequestBody ProdutoModel prod) {
		ProdutoModel novoProd = service.save(prod);
		return new ResponseEntity<>(novoProd, HttpStatus.CREATED);
	}
	
	@GetMapping("/tipo")
	public List<ProdutoDTO> getfiltro(@RequestParam("tipo") String tipo ) throws IOException  {
		
		
		return service.getTipo(tipo);
	} 
	
	public ResponseEntity<ProdutoDTO> findProdutoDTOById1(@PathVariable Integer id) {
		ProdutoDTO ProdutoDTO = service.findProdutoDTOById(id);
		return new ResponseEntity<>(ProdutoDTO, HttpStatus.OK);
	}
	
	
}
