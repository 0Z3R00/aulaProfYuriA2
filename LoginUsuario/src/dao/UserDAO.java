package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Usuario;

public class UserDAO {


	public int criar(Usuario usuario) {
		String sqlInsert = "INSERT INTO Usuario (nome, login, senha) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getLogin());
			stm.setBytes(3, usuario.getSenha());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario.getId();
	}


	public void atualizar(Usuario usuario) {
		String sqlUpdate = "UPDATE Usuario SET nome=?, login=?, senha= ? WHERE id = ? ";

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getLogin());
			stm.setBytes(3, usuario.getSenha());
			stm.setInt(4, usuario.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int Id) {

		String sqlDelete = "DELETE FROM Usuario WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, Id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuario carregar(int Id) {
		Usuario usuario = new Usuario();
		usuario.setId(Id);
		String sqlSelect = "SELECT nome, login, senha FROM Usuario WHERE Usuario.id =?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, Id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setNome(rs.getString("nome"));
					usuario.setLogin(rs.getString("login"));
					usuario.setSenha(rs.getBytes("senha"));

				} else {
					usuario.setId(-1);
					usuario.setNome(rs.getString(""));
					usuario.setLogin(rs.getString(""));
					usuario.setSenha(rs.getBytes(null));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return usuario;
	}


	public ArrayList<Usuario> listarTodos() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, login, senha FROM Usuario";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();){ 
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getBytes("senha"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public Usuario carregarLogin(String chave) {
		Usuario usuario = new Usuario();
		usuario.setLogin(chave);
		String sqlSelect = "SELECT id, nome, login, senha FROM Usuario where login = ?";
		// usando o try with resources do Java 7, quefecha o queabriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, usuario.getLogin());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setSenha(rs.getBytes("senha"));
				}else {
					usuario = null;
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return usuario;
	}
}

