package com.swiftaireng.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@Entity
@Table(name = "workdays")
public class Workday implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional=false)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_workday")
	private Date dateWorkday;
	
	@Column(name="morning_input")
	@Pattern(regexp="(0[0-9]|1[0-2]):([0-5][0-9])( AM| PM)")
	private String morningInput;
	
	@Column(name="morning_output")
	@Pattern(regexp="(0[0-9]|1[0-2]):([0-5][0-9])( AM| PM)")
	private String morningOutput;
	
	@Column(name="food_break")
	@Pattern(regexp="(0[0-9]|1[0-2]):([0-5][0-9])")
	private String foodBreak;
	
	@Column(name="afternoon_input")
	@Pattern(regexp="(0[0-9]|1[0-2]):([0-5][0-9])( AM| PM)")
	private String afternoonInput;
	
	@Column(name="afternoon_output")
	@Pattern(regexp="(0[0-9]|1[0-2]):([0-5][0-9])( AM| PM)")
	private String afternoonOutput;
	
	@Column(name="total_hours")
	@Pattern(regexp="(0[0-9]|1[0-2]):([0-5][0-9])( AM| PM)")
	private String totalHours;
	
	@Temporal(TemporalType.DATE)
	@Column(name="create_at")
	private Date createAt;
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Date getDateWorkday() {
		return dateWorkday;
	}


	public void setDateWorkday(Date date) {
		this.dateWorkday = date;
	}


	public String getMorningInput() {
		return morningInput;
	}


	public void setMorningInput(String morningInput) {
		this.morningInput = morningInput;
	}


	public String getMorningOutput() {
		return morningOutput;
	}


	public void setMorningOutput(String morningOutput) {
		this.morningOutput = morningOutput;
	}


	public String getFoodBreak() {
		return foodBreak;
	}


	public void setFoodBreak(String foodBreak) {
		this.foodBreak = foodBreak;
	}


	public String getAfternoonInput() {
		return afternoonInput;
	}


	public void setAfternoonInput(String afternoonInput) {
		this.afternoonInput = afternoonInput;
	}


	public String getAfternoonOutput() {
		return afternoonOutput;
	}


	public void setAfternoonOutput(String afternoonOutput) {
		this.afternoonOutput = afternoonOutput;
	}


	public String getTotalHours() {
		return totalHours;
	}


	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public static Date GetItemDate(final String date)
//	{
//	    final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
//	    final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
//	    format.setCalendar(cal);
//
//	    try {
//	        return format.parse(date);
//	    } catch (Exception e) {
//	        return null;
//	    }
//	}
//	
//public Double getTotal() {
//		
//		
//	if( earlierDate == null || laterDate == null ) return 0;
//
//    return (int)((laterDate.getTime()/60000) - (earlierDate.getTime()/60000));
//		
//		Double total = 0.0;
//		
//		int size = items.size();
//		for (int i=0; i<size; i++) {
//			total += items.get(i).calcularImporte();
//		}
//		return total;
//	}
//
}
