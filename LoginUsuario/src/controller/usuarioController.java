package controller;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import model.Usuario;
import service.UserService;
import util.ConfereLogin;
import util.CryptoException;
import util.CryptoUtils;
import service.PaisService;

@WebServlet("/ManterUser.do")
public class usuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String login = null;
	String senha = null;
	String nome = null;
	byte[] senhaCrypto = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");

		if(request.getParameter("nome") != null) {
			nome = request.getParameter("nome");
		}else {
			nome = "";
		}

		if(request.getParameter("login") != null) {
			login = request.getParameter("login");
		}else {
			login = "";
		}
		if(request.getParameter("senha") != null) {
			senha =request.getParameter("senha");

		}else {
			senha = null;
		}


		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(login);
		
		String key = "PratProg7ºENG@2";
		byte[] bTexto = senha.getBytes("UTF-8");
		try {
			senhaCrypto = CryptoUtils.encrypt(key, bTexto);
		} catch (CryptoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usuario.setSenha(senhaCrypto);

		ConfereLogin cl;
		UserService us = new UserService();
		PaisService ps = new PaisService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();


		if(acao.equals("Incluir")) {
  
			usuario = us.carregar(us.criar(usuario));
			request.setAttribute("usuario", usuario);
			view = request.getRequestDispatcher("Usuario.jsp");	

		}else if(acao.equals("Login")) {
			cl = new ConfereLogin(usuario.getLogin(), senha);
			usuario = cl.Confere();
			if(usuario != null) {
				/*
			usuario = us.carregarLogin(usuario.getLogin());

				request.setAttribute("usuario", usuario);
				view = request.getRequestDispatcher("Usuario.jsp");
				 */
				ArrayList<Pais>lista = new ArrayList<>();
				lista = ps.listarTodos();
				session.setAttribute("lista", lista);
				view = request.getRequestDispatcher("index.jsp");
			}else {
				view = request.getRequestDispatcher("Login.jsp");	
			}
		}
		view.forward(request, response);
	}
}







