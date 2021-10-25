package com.nisum.api.usuario.servicio;

import com.nisum.api.usuario.error.DataFormatException;
import com.nisum.api.usuario.error.EmailExisteException;
import com.nisum.api.usuario.modelo.Usuario;
import com.nisum.api.usuario.repositorio.UsuarioRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Usuario insert(Usuario usuario) throws EmailExisteException {
    	if (emailExiste(usuario.getEmail()).isPresent()) {
            throw new EmailExisteException(HttpStatus.CONFLICT,
        		"El correo ya se encuentra registrado: " 
                + usuario.getEmail());
        } else if (!isValidEmail(usuario.getEmail())) {
        	throw new DataFormatException(HttpStatus.BAD_REQUEST,
    			"El correo no tiene el formato correcto: "
    			+ usuario.getEmail());
        } else if (!isValidPassword(usuario.getPassword())) {
        	throw new DataFormatException(HttpStatus.BAD_REQUEST,
    			"El password no tiene el formato correcto: "
    			+ usuario.getPassword());
    	}
    	
        return usuarioRepositorio.save(usuario);
    }
    
    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarioRepositorio.findAll().forEach(usuarios::add);
        return usuarios;
    }

    private Optional<Usuario> emailExiste(String email) {
        return usuarioRepositorio.findByEmail(email);
    }
    
    private boolean isValidEmail(String email) {
    	String regex = "^.*@[a-zA-Z]+\\.[a-zA-Z]+$";
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(email);
    	
    	return matcher.matches();
    }
    
    private boolean isValidPassword(String password) {
    	String regex = "^(?=(?:.*?[A-Z]){1})(?=.*?[a-z])(?=(?:.*?[0-9]){2}).*$";
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(password);
    	
    	return matcher.matches();
    }
}
