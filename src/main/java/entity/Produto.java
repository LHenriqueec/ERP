package entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {

    private StringProperty codigo;
    private StringProperty nome;
    private StringProperty ncm;
    private StringProperty ean;
    private DoubleProperty custo;
    private DoubleProperty peso;
    private Set<UnidadeMedida> unMedida;
    private Grupo grupo;
    private Marca marca;
    
    public Produto() {
    	codigo = new SimpleStringProperty();
    	nome = new SimpleStringProperty();
    	ncm = new SimpleStringProperty();
    	ean = new SimpleStringProperty();
    	custo = new SimpleDoubleProperty();
    	peso = new SimpleDoubleProperty();
    	unMedida = new LinkedHashSet<>();
    }
    
    public String getCodigo() {
		return codigo.get();
	}

    public String getNome() {
		return nome.get();
	}

    public String getNcm() {
		return ncm.get();
	}

    public String getEan() {
		return ean.get();
	}

    public double getCusto() {
		return custo.get();
	}
    

    public double getPeso() {
		return peso.get();
	}
	
    public Set<UnidadeMedida> getUnMedida() {
    	return unMedida;
    }
    
    public Grupo getGrupo() {
		return grupo;
	}

    public Marca getMarca() {
		return marca;
	}

	public void setCodigo(String codigo) {
		this.codigo.set(codigo);;
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}
	
	public void setNcm(String ncm) {
		this.ncm.set(ncm);
	}

	public void setEan(String ean) {
		this.ean.set(ean);
	}

	public void setCusto(double custo) {
		this.custo.set(custo);
	}
	
	public void setPeso(double peso) {
		this.peso.set(peso);
	}

	public void setUnMedida(Set<UnidadeMedida> unMedida) {
		this.unMedida = unMedida;
	}
	
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public StringProperty codigoProperty() {
		return codigo;
	}

	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public StringProperty ncmProperty() {
		return ncm;
	}
	
	public StringProperty eanProperty() {
		return ean;
	}
	
	public DoubleProperty custoProperty() {
		return custo;
	}
	
	public DoubleProperty pesoProperty() {
		return peso;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.get().equals(other.codigo.get()))
			return false;
		return true;
	}

	@Override
    public String toString() {
    	StringBuffer buff = new StringBuffer();
    	return buff.append(codigo)
    			.append("\t")
    			.append(nome)
    			.append("\t\t")
    			.append(custo)
    			.append("\t\t").toString();
    }
}
