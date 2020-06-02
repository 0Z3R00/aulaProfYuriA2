package model;

import java.io.Serializable;

public class Pais implements Serializable{
	private static final long serialVersionUID = 1L;
	private int Id;
	private String Nome, maiorPop, menorArea;
	private long populacao;
	private double area;

	public Pais(int Id, String Nome, long populacao, double area) {
		super();
		setId(Id);
		setNome(Nome);
		setPopulacao(populacao);
		setArea(area);
	}
	public Pais() {

	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
			Nome = nome;
}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
			this.populacao = populacao;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
			this.area = area;
	}

	public void setMaiorPop(String pais) {
		maiorPop = pais;
	}
	public String getMaiorPop() {
		return maiorPop;
	}

	public void setMenorArea(String pais) {
		menorArea = pais;
	}
	public String getMenorArea() {
		return menorArea;
	}

	@Override
	public String toString() {
		return "Id " + getId() + " Nome " + getNome() + " populacao " + getPopulacao() + " area " + getArea();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		if (area != other.area) 
			return false;
		if (populacao != other.populacao) 
			return false;
		if (Id != other.Id)
			return false;
		if (Nome == null) {
			if (other.Nome != null)
				return false;
		} else if (!Nome.equals(other.Nome))
			return false;
		return true;
	}


}
