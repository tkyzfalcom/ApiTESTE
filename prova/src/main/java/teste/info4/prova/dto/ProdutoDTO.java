package teste.info4.prova.dto;

import teste.info4.prova.model.ProdutoModel;

public class ProdutoDTO {

	private Integer id;
	
	private String nome;

	private String tipo;

	private String descricao;

	private String ingredientes;

	
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}



	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(ProdutoModel prod) {
		super();
		this.id = prod.getId();
		this.nome = prod.getNome();
		this.tipo = prod.getTipo();
		this.descricao = prod.getDescricao();
		this.ingredientes = prod.getIngredientes();
	}
	
}
