package com.antoniosgarbi.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_scheduling")
public class Scheduling implements Serializable {
	@Serial
	private static final long serialVersionUID = -1579027048833977086L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;


	@NotNull(message = "The date must be informed")
	@FutureOrPresent(message = "It is not possible to schedule for previous dates")
	private LocalDate date;
	@NotNull(message = "The time must be informed")
	private LocalTime scheduled;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	public Scheduling() {
	}
	
	public Scheduling(Integer id, LocalDate date, 
			LocalTime scheduled, Doctor doctor, Patient patient) {
		this.id = id;
		this.date = date;
		this.scheduled = scheduled;
		this.doctor = doctor;
		this.patient = patient;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getScheduled() {
		return scheduled;
	}

	public void setScheduled(LocalTime scheduled) {
		this.scheduled = scheduled;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scheduling other = (Scheduling) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
