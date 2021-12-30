package io.github.diegomardu.exception;


public class UsuarioCadastradoException extends RuntimeException{
	
	public UsuarioCadastradoException(String login) {
		// TODO Auto-generated constructor stub
		super("Usuário cadastrado já existe para o login " + login);
	}

}
