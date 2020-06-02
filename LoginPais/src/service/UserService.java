package service;

import java.util.ArrayList;
import dao.UserDAO;
import model.Usuario;


public class UserService {
	UserDAO dao = new UserDAO();

	public int criar(Usuario usuario) {
		return dao.criar(usuario);
	}

	public void atualizar(Usuario usuario) {
		dao.atualizar(usuario);
	}

	public void excluir(int Id) {
		dao.excluir(Id);
	}

	public Usuario carregar(int Id) {
		return dao.carregar(Id);
	}


	public ArrayList<Usuario> listarTodos() {
		return dao.listarTodos();
		
	}
	public Usuario carregarLogin(String login) {
		return dao.carregarLogin(login);
	}



}
