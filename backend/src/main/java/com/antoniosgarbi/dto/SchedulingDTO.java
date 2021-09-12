package com.antoniosgarbi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.antoniosgarbi.entities.Scheduling;

public class SchedulingDTO {

	private Integer id;
	private LocalDate date;
	private LocalTime scheduled;
	private DoctorDTO doctor;
	private PatientDTO patient;

	public SchedulingDTO() {
	}
	
	public SchedulingDTO(Integer id, LocalDate date, 
			LocalTime scheduled, DoctorDTO doctor, PatientDTO patient) {
		
		this.id = id;
		this.date = date;
		this.scheduled = scheduled;
		this.doctor = doctor;
		this.patient = patient;
	}
	
	public SchedulingDTO(Scheduling entity, boolean request) {
		this.id = entity.getId();
		this.date = entity.getDate();
		this.scheduled = entity.getScheduled();
		if(request) {
			this.doctor = new DoctorDTO(entity.getDoctor(), !request);
			this.patient = new PatientDTO(entity.getPatient(), !request);
		}
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

	public DoctorDTO getDoctor() {
		return doctor;
	}

	public void setDoctor(DoctorDTO doctor) {
		this.doctor = doctor;
	}

	public PatientDTO getPatient() {
		return patient;
	}

	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	
}
