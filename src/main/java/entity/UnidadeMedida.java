package entity;


public class UnidadeMedida {

	private Integer id;
	private String unidade;
	private Integer quantidade;
	
	public UnidadeMedida() {
	}
	
	public UnidadeMedida(String unidade, Integer quantidade) {
		this();
		this.unidade = unidade;
		this.quantidade = quantidade;
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
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		buff.append("Unidade: ").append(unidade).append("\t")
			.append("Quantidade: ").append(quantidade);
		
		return buff.toString();
	}
}
