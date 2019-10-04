package com.swiftaireng.backend.apirest.models.entity.partcontrol;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swiftaireng.backend.apirest.models.entity.Usuario;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique=true)
	@NotEmpty(message = "Registration cannot be empty")
	private String registration;

	@NotEmpty(message = "Brand cannot be empty")
	private String brand;

	@NotEmpty(message = "Model cannot be empty")
	private String model;

	@Column(name = "frame_number", unique=true)
	@NotEmpty(message = "Frame number cannot be empty")
	private String frameNumber;

	private String year;

	private String fuel;

	@Column(name = "load_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	@NotNull(message = "Load date cannot be empty")
	private Date loadDate;

	@Column(name = "removal_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date removalDate;
	
	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date updateDate;

	@OneToOne
	@JoinColumn(name = "user_modify_id")
	private Usuario userModify;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)  
		//1 Vehiculo - Muchas revision3es
	@JoinColumn(name="vehicle_id")
	private List<Revision> revisions;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)  
	//1 Vehiculo - Muchos estados
	@JoinColumn(name="vehiclestate_id")
	private List<StatesVehicle> statesVehicles;
	
	public Vehicle() {
	}

	/**
	 * @param idNumber
	 * @param matricula
	 * @param marca
	 * @param modelo
	 * @param bastidor
	 * @param ano
	 * @param combustible
	 * @param fechaAlta
	 * @param fechaBaja
	 * @param usuModificacion
	 */
	public Vehicle(@UniqueElements @NotNull String registration, String brand, String model, String frameNumber,
			String year, String fuel, @NotNull Date loadDate, Date removalDate, Usuario userModify) {
		super();
		this.registration = registration;
		this.brand = brand;
		this.model = model;
		this.frameNumber = frameNumber;
		this.year = year;
		this.fuel = fuel;
		this.loadDate = loadDate;
		this.removalDate = removalDate;
		this.userModify = userModify;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public Date getRemovalDate() {
		return removalDate;
	}

	public void setRemovalDate(Date removalDate) {
		this.removalDate = removalDate;
	}

	public Usuario getUserModify() {
		return userModify;
	}

	public void setUserModify(Usuario userModify) {
		this.userModify = userModify;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<Revision> getRevisions() {
		return revisions;
	}

	public void setRevisions(List<Revision> revisions) {
		this.revisions = revisions;
	}

	public List<StatesVehicle> getStatesVehicles() {
		return statesVehicles;
	}

	public void setStatesVehicles(List<StatesVehicle> statesVehicles) {
		this.statesVehicles = statesVehicles;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
