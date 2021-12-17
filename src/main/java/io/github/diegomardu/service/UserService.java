package io.github.diegomardu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.diegomardu.model.entity.Usuario;
import io.github.diegomardu.model.repository.UsuarioRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = repository.findByUsername(username)
				.orElseThrow( ()-> new UsernameNotFoundException("Login não encontrado"));
		
		return User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles("USER")
				.build();
	}
}
