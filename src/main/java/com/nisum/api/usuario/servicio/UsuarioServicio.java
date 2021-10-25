package com.nisum.api.usuario.servicio;

import java.util.List;

import com.nisum.api.usuario.error.EmailExisteException;
import com.nisum.api.usuario.modelo.Usuario;

public interface UsuarioServicio {

    Usuario insert(Usuario usuario) throws EmailExisteException;
    List<Usuario> getUsuarios();
}

