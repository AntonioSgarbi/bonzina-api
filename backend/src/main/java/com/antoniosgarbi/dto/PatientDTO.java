package com.antoniosgarbi.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.antoniosgarbi.entities.Patient;

public class PatientDTO {
	
	private Integer id;
	private String name;
	private String phone;
	private String email;
	private String address;
	private LocalDate birthdate;

	public PatientDTO() {
	}

	public PatientDTO(Integer id, String name, String phone, 
			String email, String address, LocalDate birthdate) {

		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
	}
	
	public PatientDTO(Patient entity) {
	
		this.id = entity.getId();
		this.name = entity.getName();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.address = entity.getAddress();
		this.birthdate = entity.getBirthdate();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

}
