package com.swiftaireng.backend.apirest.models.entity;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "submenus")
public class SubMenu implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="topmenu_id")
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private TopMenu topmenu;

	private String link;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

		public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

		public Date getCreateAt() {
		return createAt;
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
