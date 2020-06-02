package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import model.Pais;

public class PaisDAO {


	public int criar(Pais pais) {
		String sqlInsert = "INSERT INTO Pais (nome, populacao, area) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pais.getId();
	}


	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE Pais SET nome=?, populacao=?, area= ? WHERE id = ? ";

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int Id) {

		String sqlDelete = "DELETE FROM Pais WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, Id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pais carregar(int Id) {
		Pais pais = new Pais();
		pais.setId(Id);
		String sqlSelect = "SELECT nome, populacao, area FROM Pais WHERE Pais.id =?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, Id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {
					pais.setId(-1);
					pais.setNome(rs.getString(""));
					pais.setPopulacao(rs.getLong(0));
					pais.setArea(rs.getDouble(0));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}





	public Pais MaiorPopulacao() {
		Pais pais = new Pais();
		String sqlSelect = "SELECT  id, nome, populacao, area, max(populacao) FROM Pais group by id order by populacao desc;";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}

	public Pais MenorArea() {
		Pais pais = new Pais();
		String sqlSelect = "SELECT  id, nome, populacao, area, MIN(area) FROM Pais GROUP BY id ORDER BY area ASC;";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}


	public ArrayList<Pais> CriaVetor() {
		ArrayList<Pais> retorno = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao , area FROM Pais ;";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				for(int i = 0; i < 3; i++) {
					if (rs.next()) {
						Pais pais = new Pais();
						pais.setId(rs.getInt("id"));
						pais.setNome(rs.getString("nome"));
						pais.setPopulacao(rs.getLong("populacao"));
						pais.setArea(rs.getDouble("area"));
						retorno.add(pais);
					} 

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return retorno;
	}

	public ArrayList<Pais> listarTodos() {
		ArrayList<Pais> paises = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area FROM Pais";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();){ 
			while (rs.next()) {
				Pais pais = new Pais();
				pais.setId(rs.getInt("id"));
				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setArea(rs.getDouble("area"));
				paises.add(pais);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paises;
	}

	public ArrayList<Pais> carregarNome(String chave) {
		Pais pais;
		ArrayList<Pais> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, populacao, area FROM Pais where upper(nome) like ?";
		// usando o try with resources do Java 7, quefecha o queabriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + chave.toUpperCase() + "%");
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					pais = new Pais();
					pais.setId(rs.getInt("id"));
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
					lista.add(pais);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
}

