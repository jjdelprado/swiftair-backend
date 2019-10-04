package com.swiftaireng.backend.apirest.models.entity.partcontrol;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name="states_vehicles")
@Entity
public class StatesVehicle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	@OneToOne
	@JoinColumn(name="vehicleuse_id")
	private VehicleUse vehicleuse;

	private String incidents;

	@Column(name = "significant_blow")
	private String significantBlow;

	@Column(name = "inner_defect")
	private String innerDefect;

	@Column(name = "aux_mech_system")
	private String auxMechSystem;

	@Column(name = "spill_order")
	private String spillOrder;

	// Orders are bloqueant of vehicle
	@Column(name = "incidents_bloq")
	private Boolean incidentsBloq;

	@Column(name = "significant_blow_bloq")
	private Boolean significantBlowBloq;

	@Column(name = "inner_defect_bloq")
	private Boolean innerDefectBloq;

	@Column(name = "aux_mech_system_bloq")
	private Boolean auxMechSystemBloq;

	@Column(name = "spill_order_bloq")
	private Boolean spillOrderBloq;

	// Orders that have been repairs and the date
	@Column(name = "incidents_repair")
	private String incidentsRepair;

	@Column(name = "incidents_repair_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date incidentsRepairDate;

	@Column(name = "significant_blow_repair")
	private Boolean significantBlowRepair;

	@Column(name = "significant_blow_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date significantBlowRepairDate;

	@Column(name = "inner_defect_repair")
	private Boolean innerDefectRepair;

	@Column(name = "inner_defect_repair_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date innerDefectRepairDate;

	@Column(name = "aux_mech_system_repair")
	private Boolean auxMechSystemRepair;

	@Column(name = "aux_mech_system_repair_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date auxMechSystemRepairDate;

	@Column(name = "spill_order_repair")
	private Boolean spillOrderRepair;

	@Column(name = "spill_order_repair_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date spillOrderRepairDate;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	@CreatedDate
	private Date createdDate;

	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	@LastModifiedDate
	private Date modifiedDate;

	/**
	 * 
	 */
	public StatesVehicle() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public VehicleUse getVehicleuse() {
		return vehicleuse;
	}

	public void setVehicleuse(VehicleUse vehicleuse) {
		this.vehicleuse = vehicleuse;
	}

	public String getIncidents() {
		return incidents;
	}

	public void setIncidents(String incidents) {
		this.incidents = incidents;
	}

	public String getSignificantBlow() {
		return significantBlow;
	}

	public void setSignificantBlow(String significantBlow) {
		this.significantBlow = significantBlow;
	}

	public String getInnerDefect() {
		return innerDefect;
	}

	public void setInnerDefect(String innerDefect) {
		this.innerDefect = innerDefect;
	}

	public String getAuxMechSystem() {
		return auxMechSystem;
	}

	public void setAuxMechSystem(String auxMechSystem) {
		this.auxMechSystem = auxMechSystem;
	}

	public String getSpillOrder() {
		return spillOrder;
	}

	public void setSpillOrder(String spillOrder) {
		this.spillOrder = spillOrder;
	}

	public Boolean getIncidentsBloq() {
		return incidentsBloq;
	}

	public void setIncidentsBloq(Boolean incidentsBloq) {
		this.incidentsBloq = incidentsBloq;
	}

	public Boolean getSignificantBlowBloq() {
		return significantBlowBloq;
	}

	public void setSignificantBlowBloq(Boolean significantBlowBloq) {
		this.significantBlowBloq = significantBlowBloq;
	}

	public Boolean getInnerDefectBloq() {
		return innerDefectBloq;
	}

	public void setInnerDefectBloq(Boolean innerDefectBloq) {
		this.innerDefectBloq = innerDefectBloq;
	}

	public Boolean getAuxMechSystemBloq() {
		return auxMechSystemBloq;
	}

	public void setAuxMechSystemBloq(Boolean auxMechSystemBloq) {
		this.auxMechSystemBloq = auxMechSystemBloq;
	}

	public Boolean getSpillOrderBloq() {
		return spillOrderBloq;
	}

	public void setSpillOrderBloq(Boolean spillOrderBloq) {
		this.spillOrderBloq = spillOrderBloq;
	}

	public String getIncidentsRepair() {
		return incidentsRepair;
	}

	public void setIncidentsRepair(String incidentsRepair) {
		this.incidentsRepair = incidentsRepair;
	}

	public Date getIncidentsRepairDate() {
		return incidentsRepairDate;
	}

	public void setIncidentsRepairDate(Date incidentsRepairDate) {
		this.incidentsRepairDate = incidentsRepairDate;
	}

	public Boolean getSignificantBlowRepair() {
		return significantBlowRepair;
	}

	public void setSignificantBlowRepair(Boolean significantBlowRepair) {
		this.significantBlowRepair = significantBlowRepair;
	}

	public Date getSignificantBlowRepairDate() {
		return significantBlowRepairDate;
	}

	public void setSignificantBlowRepairDate(Date significantBlowRepairDate) {
		this.significantBlowRepairDate = significantBlowRepairDate;
	}

	public Boolean getInnerDefectRepair() {
		return innerDefectRepair;
	}

	public void setInnerDefectRepair(Boolean innerDefectRepair) {
		this.innerDefectRepair = innerDefectRepair;
	}

	public Date getInnerDefectRepairDate() {
		return innerDefectRepairDate;
	}

	public void setInnerDefectRepairDate(Date innerDefectRepairDate) {
		this.innerDefectRepairDate = innerDefectRepairDate;
	}

	public Boolean getAuxMechSystemRepair() {
		return auxMechSystemRepair;
	}

	public void setAuxMechSystemRepair(Boolean auxMechSystemRepair) {
		this.auxMechSystemRepair = auxMechSystemRepair;
	}

	public Date getAuxMechSystemRepairDate() {
		return auxMechSystemRepairDate;
	}

	public void setAuxMechSystemRepairDate(Date auxMechSystemRepairDate) {
		this.auxMechSystemRepairDate = auxMechSystemRepairDate;
	}

	public Boolean getSpillOrderRepair() {
		return spillOrderRepair;
	}

	public void setSpillOrderRepair(Boolean spillOrderRepair) {
		this.spillOrderRepair = spillOrderRepair;
	}

	public Date getSpillOrderRepairDate() {
		return spillOrderRepairDate;
	}

	public void setSpillOrderRepairDate(Date spillOrderRepairDate) {
		this.spillOrderRepairDate = spillOrderRepairDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
