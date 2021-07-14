package com.antoniosgarbi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
		Patient entity = new Patient(dto.getId(), dto.getName(), dto.getPhone(),
				dto.getEmail(), dto.getAddress(), dto.getBirthdate());
		entity = repository.save(entity);
		return new PatientDTO(entity);
		
	}
	
	public List<PatientDTO> findAll() {
		List<Patient> list = repository.findAll();
		return list.stream().
				map(x -> new PatientDTO(x)).collect(Collectors.toList());
	}
	
	public Boolean delete(Integer id) {
		Boolean returned = false;
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
