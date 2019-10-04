package com.swiftaireng.backend.apirest.models.entity;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements Serializable {

	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotEmpty
    private String nombre;
    
	@NotEmpty	
    @Column(name="primer_apellido")
    private String primerApellido;
    
    @Column(name="segundo_apellido")
    private String segundoApellido;

    @ManyToMany
    @JoinTable(name="usuario_departamento", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "departamento_id"))
    private Set<Departamento> departamentos = new HashSet<>();
    
	@NotEmpty
    @Column(unique = true)
    private String nombreUsuario;

	@NotEmpty
	@Email
    @Column(unique = true)
    private String email;

    private String password;

    @NotNull
    @ManyToMany
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(@NotNull String nombre, String primerApellido, String segundoApellido, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
}
