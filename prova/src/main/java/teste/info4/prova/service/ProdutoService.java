package teste.info4.prova.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import teste.info4.prova.dto.ProdutoDTO;
import teste.info4.prova.model.ProdutoModel;
import teste.info4.prova.repository.ProdutoRepository;

@Service
public class ProdutoService {


	@Autowired
	ProdutoRepository repository;

	public List<ProdutoModel> findAll() {
		return repository.findAll();
	}

	public ProdutoModel findById(Integer id) {
		return repository.existsById(id) ? repository.findById(id).get() : null;
	}

	public ProdutoDTO findProdutoDTOById(Integer id) {
		return repository.existsById(id) ? converterEntidadeParaDTO(repository.findById(id).get()) : null;
	}

	public ProdutoModel save(ProdutoModel produto) {
		return repository.save(produto);
	}

	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		return converterEntidadeParaDTO(repository.save(converterDTOParaEntidade(produtoDTO)));
	}

	public ProdutoModel updateProduto(ProdutoModel produto) {
		return repository.save(produto);
	}

	public void deleteProduto(ProdutoModel produto) {
		repository.delete(produto);
	}

	public void deleteProdutoById(Integer id) {
		repository.deleteById(id);
	}

	private ProdutoModel converterDTOParaEntidade(ProdutoDTO produtoDTO) {
		ProdutoModel produto = new ProdutoModel();

		produto.setIngredientes(produtoDTO.getIngredientes());
		produto.setTipo(produtoDTO.getTipo());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setNome(produtoDTO.getNome());
		produto.setId(produtoDTO.getId());

		return produto;
	}

	private ProdutoDTO converterEntidadeParaDTO(ProdutoModel produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();

		produtoDTO.setIngredientes(produto.getIngredientes());
		produtoDTO.setTipo(produtoDTO.getTipo());
		produtoDTO.setDescricao(produtoDTO.getDescricao());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setId(produto.getId());

		return produtoDTO;
	}

	public List<ProdutoDTO> getTipo(String tipo ) throws IOException {
		List<ProdutoModel> optional = repository.tipo(tipo);
		List< ProdutoDTO> dto= new ArrayList<>();
		
		
		return optional.stream().map(prod -> new ProdutoDTO(prod)).collect(Collectors.toList());
	}
	
}
