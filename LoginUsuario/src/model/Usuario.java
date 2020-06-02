package model;

public class Usuario {
	private int id;
	private String login, nome;
	private byte[] senha;

	public Usuario() {}
	
	public Usuario(int id, String login , String nome, byte[] senha ) {
		setId(id);
		setNome(nome);
		setLogin(login);
		setSenha(senha);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getSenha() {
		return senha;
	}

	public void setSenha(byte[] senha) {
		this.senha = senha;
	}

}
