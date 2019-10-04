package com.swiftaireng.backend.apirest.models.entity;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "topmenus")
public class TopMenu implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departamento_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Departamento departamento;

	@Column(nullable = false)
	private String Nombre;

	private String link;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)  //Lazy, carga perezosa, solo trae cuando se le llama. 
	   //Todas las operaciones de delete y persist se hacen en cascada con el CascadeType.ALL
		//1 Factura - Muchos Items facturas
	@JoinColumn(name="topmenu_id")  //Importantísimo para generar la FK factura_id en la tabla facturas_items
	private List<SubMenu> submenus;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public List<SubMenu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<SubMenu> submenus) {
		this.submenus = submenus;
	}

	public void addSubmenu(SubMenu submenu) {
		this.submenus.add(submenu);
	}	
	
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	private static final long serialVersionUID = 1L;

}
