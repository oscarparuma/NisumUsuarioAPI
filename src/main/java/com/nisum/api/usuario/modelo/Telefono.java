package com.nisum.api.usuario.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.api.usuario.modelo.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "telefono")
public class Telefono {

	@Id
    @GeneratedValue
	@Column(updatable = false, nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long id;
	
	@Column
    String number;
	
	@Column
    String citycode;
	
	@Column
    String contrycode;

	@ManyToOne
    @JoinColumn(name = "usuario_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Usuario usuario;
	
	public Telefono() {}
		
	public Telefono(Long id, String number, String citycode, String contrycode, Usuario usuario) {
		this.id = id;
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Telefono [id=" + id + ", number=" + number + ", citycode=" + citycode + ", contrycode=" + contrycode
				+ ", usuario=" + usuario + "]";
	}

}
