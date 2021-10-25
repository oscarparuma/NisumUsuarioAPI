package com.nisum.api.usuario.modelo;

import com.nisum.api.usuario.modelo.Telefono;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario {
	
    @Id
    @Column(updatable = false, nullable = false)
    private String id;
    
    @Column
    String name;
    
    @Column(unique = true)
    String email;
    
    @Column
    String password;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Telefono> phones = new HashSet<>();
    
    @CreationTimestamp
    @Column(updatable = false)
    Timestamp created;
    
    @UpdateTimestamp
    Timestamp modified;
	
    @Column
    Timestamp last_login;
    
    @Column
    String token;
    
    @Column
    boolean isactive;
    
    public Usuario() {
    	this.id = UUID.randomUUID().toString();
    	this.last_login = new Timestamp(System.currentTimeMillis());
    	this.isactive = true;
    }
    
	public Usuario(String id, String name, String email, String password, Set<Telefono> phones, Timestamp created,
			Timestamp modified, Timestamp last_login, boolean isactive) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
		this.created = created;
		this.modified = modified;
		this.last_login = last_login;
		this.isactive = isactive;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Telefono> getPhones() {
		return phones;
	}

	public void setPhones(Set<Telefono> phones) {
		this.phones = phones;
		
		for(Telefono phone : phones) {
            phone.setUsuario(this);
        }
	}
	
	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public Timestamp getLast_login() {
		return last_login;
	}

	public void setLast_login(Timestamp last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phones="
				+ phones + ", created=" + created + ", modified=" + modified + ", last_login=" + last_login
				+ ", isactive=" + isactive + "]";
	}

}

