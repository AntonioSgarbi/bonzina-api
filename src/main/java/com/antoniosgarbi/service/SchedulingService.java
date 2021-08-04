package com.antoniosgarbi.service;

import com.antoniosgarbi.dto.SchedulingDTO;
import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.entities.Scheduling;
import com.antoniosgarbi.repository.SchedulingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SchedulingService {

    private final SchedulingRepository repository;

    public SchedulingService(SchedulingRepository repository) {
        this.repository = repository;
    }

    public Page<SchedulingDTO> findAll(Pageable pageable) {
        Page<Scheduling> page = repository.findAll(pageable);
        return page.map(SchedulingDTO::new);
    }

    public SchedulingDTO insert(SchedulingDTO dto) {
        Scheduling entity =  new Scheduling(
                null, dto.getDate(), dto.getScheduled(),
                new Doctor(dto.getDoctor()), new Patient(dto.getPatient()));
        entity = repository.save(entity);
        return new SchedulingDTO(entity);
    }

    public SchedulingDTO update(SchedulingDTO dto) {
        Doctor doctor =  new Doctor(dto.getDoctor());
        Patient patient = new Patient(dto.getPatient());
        Scheduling entity =  new Scheduling(
                dto.getId(), dto.getDate(), dto.getScheduled(),
                doctor, patient);
        repository.update(dto.getId(), dto.getDate(), dto.getScheduled(),
                doctor, patient);
        return new SchedulingDTO(entity);
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
