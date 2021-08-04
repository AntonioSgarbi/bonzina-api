package com.antoniosgarbi.dto;

import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;

public class DoctorDTO {
	
	private Integer id;
	private String name;
	private String phone;
	private String email;
	private Integer register;
	private String speciality;
	private Clinic clinic;
	private Period period;
	
	public DoctorDTO() {
	}

	public DoctorDTO(Integer id, String name, String phone, 
			String email, Integer register, String speciality,
			Clinic clinic, Period period) {
		
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.register = register;
		this.speciality = speciality;
		this.clinic = clinic;
		this.period = period;
	}
	
	public DoctorDTO(Doctor entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.register = entity.getRegister();
		this.speciality = entity.getSpeciality();
		this.clinic = entity.getClinic();
		this.period = entity.getPeriod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRegister() {
		return register;
	}

	public void setRegister(Integer register) {
		this.register = register;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}
		
}
