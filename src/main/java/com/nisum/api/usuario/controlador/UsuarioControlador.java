package com.nisum.api.usuario.controlador;

import com.nisum.api.usuario.error.DataFormatException;
import com.nisum.api.usuario.error.EmailExisteException;
import com.nisum.api.usuario.error.ExceptionResponse;
import com.nisum.api.usuario.modelo.Usuario;
import com.nisum.api.usuario.servicio.UsuarioServicio;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/api/v1")
public class UsuarioControlador {
	
    UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
    	String token = getJWTToken(usuario.getEmail());
    	usuario.setToken(token);
        Usuario newUsuario = usuarioServicio.insert(usuario);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("registro", "/api/v1/" + newUsuario.getId().toString());
        return new ResponseEntity<>(newUsuario, httpHeaders, HttpStatus.CREATED);
    }
    
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioServicio.getUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    
    private String getJWTToken(String email) {
		String secretKey = "nisumapi";
		
		String token = Jwts
				.builder()
				.setId("nisumJWT")
				.setSubject(email)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return token;
	}
    
    @ExceptionHandler(EmailExisteException.class)
    public ResponseEntity<ExceptionResponse> handleEmailExisteException(EmailExisteException emailExisteException) {
    	ExceptionResponse response = new ExceptionResponse();
        response.setMensaje(emailExisteException.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, emailExisteException.getStatus());
    }
    
    @ExceptionHandler(DataFormatException.class)
    public ResponseEntity<ExceptionResponse> handleDataFormatException(DataFormatException dataFormatException) {
    	ExceptionResponse response = new ExceptionResponse();
        response.setMensaje(dataFormatException.getMessage());
        return new ResponseEntity<ExceptionResponse>(response, dataFormatException.getStatus());
    }
}
