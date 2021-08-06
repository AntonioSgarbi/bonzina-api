package com.antoniosgarbi.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.antoniosgarbi.dto.DoctorDTO;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;

@Entity
@Table(name = "tb_doctor")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 5322991030176876251L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty(message = "Um nome deve ser informado")
	private String name;
	@NotEmpty(message = "Um telefone deve ser informado")
	private String phone;
	@NotEmpty(message = "Um email deve ser informado")
	private String email;
	@NotNull(message = "O CRM deve ser informado")
	private Integer register;
	@NotEmpty(message = "A especialidade deve ser informada")
	private String speciality;
	@NotNull(message = "O consultório deve ser informado")
	private Clinic clinic;
	@NotNull(message = "O período deve ser informado")
	private Period period;


	@OneToMany(
			mappedBy = "doctor",
			cascade = {CascadeType.REMOVE, CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	private List<Scheduling> schedulings = new ArrayList<>();
	
	public Doctor() {
	}
	
	public Doctor(Integer id, String name, String phone, String email, 
			Integer register, String speciality, Clinic clinic, Period period) {
		
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.register = register;
		this.speciality = speciality;
		this.clinic = clinic;
		this.period = period;
	}

	public Doctor(DoctorDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.phone = dto.getPhone();
		this.email = dto.getEmail();
		this.register = dto.getRegister();
		this.speciality = dto.getSpeciality();
		this.clinic = dto.getClinic();
		this.period = dto.getPeriod();
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
		Doctor other = (Doctor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
