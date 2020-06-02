package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import service.PaisService;
import model.Pais;




@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class PaisTest {
	Pais pais, copia;
	PaisService paisService;
	static int id = 0;

	@Before
	void setUp() throws Exception {
		System.out.println("Configurando");
		pais = new Pais(id, "Brasil", 210147125, 8515767.00);
		copia = new Pais(id, "Brasil", 210147125, 8515767.00);
		paisService = new PaisService();
		System.out.println("=================");
		System.out.println(id);
		System.out.println(pais);
		System.out.println(copia);
	}

	@Test
	public void test00Carregar() {
		System.out.println("Carregando informações");
		Pais fixture = new Pais(1, "Brasil", 210147125, 8515767.00);
		PaisService novoService = new PaisService();
		Pais novo = novoService.carregar(1);
		assertEquals("Testa ", fixture.toString(),novo.toString());

	}

	@Test
	public void test01Criar() {
		System.out.println("Criar");
		Pais fixture = new Pais(1, "Brasil", 210147125, 8515767.00);
		PaisService novoService = new PaisService();
		int id = novoService.criar(fixture);
		Pais novo = novoService.carregar(id);
		assertEquals("teste Criando pais", fixture.toString(), novo.toString());

	}


	@Test
	public void test02Atualizar() {
		Pais fixture = new Pais(1, "Brasil", 210147125, 8515767.00);
		PaisService novoService = new PaisService();
		int id = novoService.criar(fixture);
		Pais novo = novoService.carregar(id);
		System.out.println("Atualizando");
		novo.setNome("Bolivia");
		fixture.setNome("Bolivia");
		novoService.atualizar(fixture);
		fixture = novoService.carregar(id);
		assertEquals("Testa atualização", novo.toString(),fixture.toString());
	}


	@Test
	public void test03Excluir() {
		Pais pais = new Pais(id, "Brasil", 210147125 ,8515767.00);
		Pais copia = new Pais();
		System.out.println("=======================");
		System.out.println("Excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0);
		PaisService novoService = new PaisService();
		id = novoService.criar(pais);
		novoService.excluir(id);
		pais = novoService.carregar(pais.getId());
		assertEquals("Testa atualização",copia.toString(),pais.toString());

	}

	@Test
	public void test04MaiorPopulacao() {
		Pais pais = new Pais();
		PaisService novoService = new PaisService();

		pais = novoService.maiorPopulacao();
		System.out.println(pais.toString());
		assertEquals("Maior população", "Id 6 Nome China populacao 1379302771 area 9596961.0", pais.toString());

	}



	@Test
	public void test05MenorArea() {
		Pais pais = new Pais();
		PaisService novoService = new PaisService();

		pais = novoService.menorArea();
		System.out.println(pais.toString());
		assertEquals("Maior população", "Id 10 Nome Japão populacao 126440000 area 377975.0", pais.toString());

	}

	@Test
	public void test06Vetor() {

		String confere = "Id 1 Nome Brasil populacao 210147125 area 8515767.0\n" + 
				"Id 2 Nome Argentina populacao 43590368 area 2780400.0\n" + 
				"Id 3 Nome Paraguai populacao 7152703 area 406752.0\n";
		ArrayList<Pais> vetor = new ArrayList<>();
		PaisService novoService = new PaisService();
		vetor = novoService.CriaVetor();
		String msg = "";
		for (Pais vet : vetor) {
			msg = msg+ vet+"\n";
		}

		System.out.println(msg);
		assertEquals("Testa vetor: ",confere, msg );
	}



}

