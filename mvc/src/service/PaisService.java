package service;

import java.util.ArrayList;
import dao.PaisDAO;
import model.Pais;

public class PaisService {
	PaisDAO dao = new PaisDAO();

	public int criar(Pais pais) {
		return dao.criar(pais);
	}

	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}

	public void excluir(int Id) {
		dao.excluir(Id);
	}

	public Pais carregar(int Id) {
		return dao.carregar(Id);
	}


	public Pais maiorPopulacao() {
		return dao.MaiorPopulacao();
	}

	public Pais menorArea() {
		return dao.MenorArea();
	}

	public ArrayList<Pais> CriaVetor() {
		return dao.CriaVetor();
	}

	public ArrayList<Pais> listarTodos() {
		return dao.listarTodos();
		
	}
	public ArrayList<Pais> carregarNome(String nome) {
		return dao.carregarNome(nome);
	}


}
