package com.antoniosgarbi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.entities.Scheduling;

public class SchedulingDTO {

	private Integer id;
	private LocalDate date;
	private LocalTime scheduled;
	private Doctor doctor;
	private Patient patient;

	public SchedulingDTO() {
	}
	
	public SchedulingDTO(Integer id, LocalDate date, 
			LocalTime scheduled, Doctor doctor, Patient patient) {
		
		this.id = id;
		this.date = date;
		this.scheduled = scheduled;
		this.doctor = doctor;
		this.patient = patient;
	}
	
	public SchedulingDTO(Scheduling entity) {
		this.id = entity.getId();
		this.date = entity.getDate();
		this.scheduled = entity.getScheduled();
		this.doctor = entity.getDoctor();
		this.patient = entity.getPatient();
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
	
}
