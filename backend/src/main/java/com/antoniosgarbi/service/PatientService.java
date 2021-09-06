package com.antoniosgarbi.service;

import com.antoniosgarbi.exception.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.repository.PatientRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PatientService {

	private final PatientRepository repository;

	public PatientService(PatientRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public PatientDTO insert(PatientDTO dto) {
		Patient entity = new Patient(null, dto.getName(), dto.getPhone(),
				dto.getEmail(), dto.getAddress(), dto.getBirthdate());
		entity = repository.save(entity);
		return new PatientDTO(entity, false);
	}

	@Transactional
	public PatientDTO update(PatientDTO dto) {
		Patient entity = new Patient(dto.getId() , dto.getName(), dto.getPhone(),
				dto.getEmail(), dto.getAddress(), dto.getBirthdate());
		repository.update(dto.getName(), dto.getPhone(),
				dto.getEmail(), dto.getAddress(), dto.getBirthdate(), dto.getId());
		return new PatientDTO(entity, false);
	}

	public Page<PatientDTO> findAll(Pageable pageable) {
		Page<Patient> page = repository.findAll(pageable);
		return page.map(x -> new PatientDTO(x, true));
	}

	public Page<PatientDTO> findByName(String name, Pageable pageable) {
		Page<Patient> page = repository.findByNameContainingIgnoreCase(name, pageable);
		return page.map(x -> new PatientDTO(x, true));
	}

	@Transactional
	public void delete(Integer id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException("Id can't be found");
		}
	}
	
	public Long count() {
		return repository.count();
	}
	
}
