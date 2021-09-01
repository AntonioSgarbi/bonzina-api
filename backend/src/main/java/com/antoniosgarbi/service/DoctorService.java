package com.antoniosgarbi.service;

import java.util.List;
import java.util.stream.Collectors;

import com.antoniosgarbi.exception.BadRequestException;
import org.springframework.stereotype.Service;

import com.antoniosgarbi.dto.DoctorDTO;
import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.repository.DoctorRepository;

@Service
public class DoctorService {
	
	private final DoctorRepository repository;

	public DoctorService(DoctorRepository repository) {
		this.repository = repository;
	}

	public DoctorDTO insert(DoctorDTO dto) {
		Doctor entity = new Doctor(null, dto.getName(), dto.getPhone(), 
				dto.getEmail(), dto.getRegister(), dto.getSpeciality(),
				dto.getClinic(), dto.getPeriod());
		entity = repository.save(entity);
		return new DoctorDTO(entity, false);
	}
	
	public List<DoctorDTO> findAll() {
		List<Doctor> list = repository.findAll();
		return list.stream().
				map(x -> new DoctorDTO(x, true)).collect(Collectors.toList());
		
	}
	
	public DoctorDTO update(DoctorDTO dto) {
		Doctor entity = new Doctor(dto.getId(), dto.getName(), dto.getPhone(), 
				dto.getEmail(), dto.getRegister(), dto.getSpeciality(),
				dto.getClinic(), dto.getPeriod());
		repository.update(dto.getName(), dto.getPhone(), 
				dto.getEmail(), dto.getRegister(), dto.getSpeciality(),
				dto.getClinic(), dto.getPeriod(), dto.getId());
		return new DoctorDTO(entity, false);
	}
	
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








