package com.swiftaireng.backend.apirest.models.entity;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiftaireng.backend.apirest.enums.RolNombre;

@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull
	private RolNombre rolNombre;
	
	private Long weight;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departamento_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Departamento departamento;
	
	public Rol() {
	}

	public Rol(@NotNull RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
