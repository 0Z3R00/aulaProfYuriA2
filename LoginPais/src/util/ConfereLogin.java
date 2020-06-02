package util;



import java.io.IOException;
import java.util.Calendar;

import model.Usuario;
import service.UserService;

public class ConfereLogin {
	private String login, senha;
	private UserService us;
	private Usuario user;
	private Log log;
	private String arquivo = "/home/mmoura/eclipse-workspace/LoginPais/WebContent/log/RegistroDeAcesso.txt";
    

	public ConfereLogin(String login, String senha) {
		setLogin(login);
		setSenha(senha);
		us = new UserService();
		log = new Log();

	}

	public Usuario Confere() {
		if(Busca(getLogin()) == true) {
	    
			if(user.getSenha().equals(senha)) {
				Calendar c = Calendar.getInstance();
				try {
					log.abrir(arquivo);
					log.escrever("************************************* \nUsuario: "+getLogin()+" Realizou Acessou : "+c.getTime()+"\n*************************************\n");
					log.fechar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return user;
			}else {
				Calendar c = Calendar.getInstance();
				try {
					log.abrir(arquivo);
					log.escrever("************************************* \nUsuario: "+getLogin()+" Acessou com a senha errada:"+getSenha()+"\nRealizou acesso : "+c.getTime()+"\n*************************************\n");
					log.fechar();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		}
		return null;
	}

	public boolean Busca(String login){
		user = us.carregarLogin(login);
		if(user != null) {
			return true;
		}else {
			try {
				Calendar c = Calendar.getInstance();
				log.abrir(arquivo);
				log.escrever("************************************* \nUsuario: "+login+" Não existe no sistema \n Tentou acesso : "+c.getTime()+"\n*************************************\n");
				log.fechar();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}
	
	
	
	public String getLogin() {
		return login;
	}

	public String getSenha(){
		return senha;
	}
	
	
	public void setLogin(String login) {
		this.login = login;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

}
