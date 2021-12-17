package io.github.diegomardu.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.diegomardu.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Optional<Usuario> findByUsername(String username);

}
