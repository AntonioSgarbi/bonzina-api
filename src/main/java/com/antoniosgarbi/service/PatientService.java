package com.antoniosgarbi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.repository.PatientRepository;


@Service
public class PatientService {

	@Autowired
	private PatientRepository repository;
	
	public PatientDTO insert(PatientDTO dto) {
		Patient entity = new Patient(null, dto.getName(), dto.getPhone(),
				dto.getEmail(), dto.getAddress(), dto.getBirthdate());
		entity = repository.save(entity);
		return new PatientDTO(entity);
	}
	
	public PatientDTO update(PatientDTO dto) {
		Patient entity = new Patient(dto.getId() , dto.getName(), dto.getPhone(),
				dto.getEmail(), dto.getAddress(), dto.getBirthdate());
		repository.update(dto.getName(), dto.getPhone(),
				dto.getEmail(), dto.getAddress(), dto.getBirthdate(), dto.getId());
		return new PatientDTO(entity);
	}
	
	public Page<PatientDTO> findAll(Pageable pageable) {
		Page<Patient> page = repository.findAll(pageable);
		return page.map(PatientDTO::new);
	}

	public Page<PatientDTO> findByName(String name, Pageable pageable) {
		Page<Patient> page = repository.findByNameContaining(name, pageable);
		return page.map(PatientDTO::new);
	}
	
	public Boolean delete(Integer id) {
		boolean returned = false;
		try {
			repository.deleteById(id); 
			returned = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return returned;
	}
	
	public Long count() {
		return repository.count();
	}
	
}
