package com.swiftaireng.backend.apirest.models.entity.partcontrol;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.swiftaireng.backend.apirest.models.entity.Usuario;

@Entity
@Table(name = "vehicles_use")
public class VehicleUse implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@NotNull(message = "vehicle cannot be empty")
	private Vehicle vehiculo;

	@NotNull(message = "Service cannot be empty")
	private String service;

	@Column(name = "pickup_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	@NotNull(message = "Pick up date cannot be empty")
	private Date pickupDate;

	@Column(name = "return_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date returnDate;

	@Column(name = "driver_name")
	@NotNull(message = "Driver Name cannot be empty")
	private String driverName;

	@Column(name = "estimated_use")
	@NotNull(message = "Estimated Use cannot be empty")
	private String estimatedUse;

	@Lob
	private byte[] signature;

	@NotNull(message = "Shift cannot be empty")
	private String shift;

	@Column(name = "shift_chief")
	@NotNull(message = "Shift Chief cannot be empty")
	private String shiftChief;

	@NotNull(message = "Task cannot be empty")
	private String task;

//	private String incidents;

	@ManyToOne
	@JoinColumn(name = "user_checkin_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario userCheckin;

	@ManyToOne
	@JoinColumn(name = "user_checkout_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario userCheckout;

//	@Column(name = "significant_blow")
//	private String significantBlow;
//
//	@Column(name = "inner_defect")
//	private String innerDefect;
//
//	@Column(name = "aux_mech_system")
//	private String auxMechSystem;
//
//	@Column(name = "spill_order")
//	private String spillOrder;

	@OneToOne
	@JoinColumn(name = "states_vehicle_id")
	private StatesVehicle statesVehicle;
	
	public VehicleUse() {
	}

	/**
	 * Constructor for create VehicleUse whithout return date and whithout signature
	 * 
	 * @param vehiculo
	 * @param service
	 * @param pickupDate
	 * @param driverName
	 * @param estimatedUse
	 * @param shift
	 * @param shiftChief
	 * @param task
	 */
	public VehicleUse(Vehicle vehiculo, @NotNull String service, @NotNull Date pickupDate, String driverName,
			String estimatedUse, String shift, String shiftChief, String task, Usuario userCheckin) {
		super();
		this.vehiculo = vehiculo;
		this.service = service;
		this.pickupDate = pickupDate;
		this.driverName = driverName;
		this.estimatedUse = estimatedUse;
		this.shift = shift;
		this.shiftChief = shiftChief;
		this.task = task;
		this.userCheckin = userCheckin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehicle vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getEstimatedUse() {
		return estimatedUse;
	}

	public void setEstimatedUse(String estimatedUse) {
		this.estimatedUse = estimatedUse;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getShiftChief() {
		return shiftChief;
	}

	public void setShiftChief(String shiftChief) {
		this.shiftChief = shiftChief;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

//	public String getIncidents() {
//		return incidents;
//	}
//
//	public void setIncidents(String incidents) {
//		this.incidents = incidents;
//	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Usuario getUserCheckin() {
		return userCheckin;
	}

	public void setUserCheckin(Usuario userCheckin) {
		this.userCheckin = userCheckin;
	}

	public Usuario getUserCheckout() {
		return userCheckout;
	}

	public void setUserCheckout(Usuario userCheckout) {
		this.userCheckout = userCheckout;
	}

	
//	public String getSignificantBlow() {
//		return significantBlow;
//	}
//
//	public void setSignificantBlow(String significantBlow) {
//		this.significantBlow = significantBlow;
//	}
//
//	public String getInnerDefect() {
//		return innerDefect;
//	}
//
//	public void setInnerDefect(String innerDefect) {
//		this.innerDefect = innerDefect;
//	}
//
//	public String getAuxMechSystem() {
//		return auxMechSystem;
//	}
//
//	public void setAuxMechSystem(String auxMechSystem) {
//		this.auxMechSystem = auxMechSystem;
//	}
//
//	public String getSpillOrder() {
//		return spillOrder;
//	}
//
//	public void setSpillOrder(String spillOrder) {
//		this.spillOrder = spillOrder;
//	}

	public StatesVehicle getStatesVehicle() {
		return statesVehicle;
	}

	public void setStatesVehicle(StatesVehicle statesVehicle) {
		this.statesVehicle = statesVehicle;
	}


	private static final long serialVersionUID = 1L;

}
