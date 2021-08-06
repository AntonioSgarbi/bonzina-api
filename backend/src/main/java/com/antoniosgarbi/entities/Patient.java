package com.antoniosgarbi.entities;

import com.antoniosgarbi.dto.PatientDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Table(name = "tb_patient")
public class Patient implements Serializable {
	private static final long serialVersionUID = 5719278450261503359L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty(message = "Um nome deve ser informado")
	private String name;
	@NotEmpty(message = "Um telefone deve ser informado")
	private String phone;
	@NotEmpty(message = "Um email deve ser informado")
	private String email;
	@NotEmpty(message = "Um endereço deve ser informmado")
	private String address;
	@NotNull(message = "A data de nascimento deve ser informada")
	@Past(message = "A data de nascimento é inválida")
	private LocalDate birthdate;
	
	@OneToMany(
			mappedBy = "patient",
			cascade = {CascadeType.REMOVE, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	private List<Scheduling> schedulings = new ArrayList<>();

	public Patient() {
	}

	public Patient(Integer id, String name, String phone, String email, String address, LocalDate birthdate) {
		
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.birthdate = birthdate;
	}

	public Patient(PatientDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.phone = dto.getPhone();
		this.email = dto.getEmail();
		this.address = dto.getAddress();
		this.birthdate = dto.getBirthdate();
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

	public List<Scheduling> getSchedulings() {
		return schedulings;
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
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}