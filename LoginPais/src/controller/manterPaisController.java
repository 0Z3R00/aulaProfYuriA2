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
import service.PaisService;

@WebServlet("/ManterPais.do")
public class manterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	long populacao = 0;
	double area =  0;
	int id = -1;
	String nome =  "";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		String Id = request.getParameter("id");
		try {
			id = Integer.parseInt(Id);
		} catch (NumberFormatException e) {
		}

		if(request.getParameter("nome") != null) {
			nome = request.getParameter("nome");
		}else {
			nome = "";
		}
		if(request.getParameter("populacao") != null) {
			populacao = Long.parseLong(request.getParameter("populacao"));
		}else {
			populacao = 0;
		}
		if(request.getParameter("area") != null) {
			area = Double.parseDouble(request.getParameter("area"));
		}else {
			area = 0;
		}

		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome(nome);
		pais.setPopulacao(populacao);
		pais.setArea(area);

		PaisService  ps = new PaisService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		if (acao.equals("Criar")) {
			ps.criar(pais);
			ArrayList<Pais>lista = new ArrayList<>();
			lista.add(pais);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("Pais.jsp");

		} else if (acao.equals("Excluir")) {
			ps.excluir(pais.getId());
			ArrayList<Pais>lista = (ArrayList<Pais>)session.getAttribute("lista") ;
			lista.remove(busca(pais, lista));
			session.setAttribute("pais", lista);
			view = request.getRequestDispatcher("Pais.jsp");

		} else if (acao.equals("Alterar")) {
			ps.atualizar(pais);
			ArrayList<Pais>lista = (ArrayList<Pais>)session.getAttribute("lista") ;
			int pos = busca(pais, lista);
			lista.remove(pos);
			lista.add(pos, pais);
			session.setAttribute("lista", lista);
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");

		} else if (acao.equals("Visualizar")) {
			pais = ps.carregar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");

		} else if (acao.equals("Editar")) {
			pais = ps.carregar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("AlterarPais.jsp");

		}
		view.forward(request, response);

	}

	public int busca(Pais pais, ArrayList<Pais>lista) {
		Pais to;
		for(int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if(to.getId() == pais.getId()) {
				return i;
			}
		}
		return -1;
	}

}







