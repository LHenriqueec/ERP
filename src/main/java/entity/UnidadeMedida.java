package entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class UnidadeMedida {

	private Integer id;
	private String unidade;
	private Integer quantidade;
	private Set<Produto> produtos;
	
	public UnidadeMedida() {
		produtos = new LinkedHashSet<>();
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getUnidade() {
		return unidade;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Set<Produto> getProdutos() {
		return produtos;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("Unidade: ").append(unidade).append("\t")
			.append("Quantidade: ").append(quantidade);
		
		return buff.toString();
	}
}
