package com.swiftaireng.backend.apirest.models.entity.partcontrol;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "revisions")
@Entity
public class Revision implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "revision_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date revisionDate;

	@Column(name = "performed_revision_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Madrid")
	private Date performedRevisionDate;

	private String observations;

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
	public Revision() {
		super();
	}

	/**
	 * @param revisionDate
	 * @param performedRevisionDate
	 * @param observations
	 * @param createAtDate
	 */
	public Revision(Date revisionDate, Date performedRevisionDate, String observations) {
		super();
		this.revisionDate = revisionDate;
		this.performedRevisionDate = performedRevisionDate;
		this.observations = observations;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Date revisionDate) {
		this.revisionDate = revisionDate;
	}

	public Date getPerformedRevisionDate() {
		return performedRevisionDate;
	}

	public void setPerformedRevisionDate(Date performedRevisionDate) {
		this.performedRevisionDate = performedRevisionDate;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createAtDate) {
		this.createdDate = createAtDate;
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
