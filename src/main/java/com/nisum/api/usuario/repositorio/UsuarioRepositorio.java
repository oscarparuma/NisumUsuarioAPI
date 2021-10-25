package com.nisum.api.usuario.repositorio;

import com.nisum.api.usuario.modelo.Usuario;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
	public Optional<Usuario> findByEmail(String email);
}
