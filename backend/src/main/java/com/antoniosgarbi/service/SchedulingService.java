package com.antoniosgarbi.service;

import com.antoniosgarbi.dto.CounterScheduleDTO;
import com.antoniosgarbi.dto.SchedulingDTO;
import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.entities.Scheduling;
import com.antoniosgarbi.exception.BadRequestException;
import com.antoniosgarbi.repository.SchedulingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SchedulingService {

    private final SchedulingRepository repository;

    public SchedulingService(SchedulingRepository repository) {
        this.repository = repository;
    }

    public Page<SchedulingDTO> findAll(Pageable pageable) {
        Page<Scheduling> pageEntity = repository.findAll(pageable);
        Page<SchedulingDTO> pageDTO = pageEntity.map(x -> new SchedulingDTO(x, true));
        return pageDTO;
    }

    public SchedulingDTO insert(SchedulingDTO dto) {
        Scheduling entity =  new Scheduling(
                null, dto.getDate(), dto.getScheduled(),
                new Doctor(dto.getDoctor()), new Patient(dto.getPatient()));
        entity = repository.save(entity);
        return new SchedulingDTO(entity, true);
    }

    public SchedulingDTO update(SchedulingDTO dto) {
        Doctor doctor =  new Doctor(dto.getDoctor());
        Patient patient = new Patient(dto.getPatient());
        Scheduling entity =  new Scheduling(
                dto.getId(), dto.getDate(), dto.getScheduled(),
                doctor, patient);
        repository.update(dto.getId(), dto.getDate(), dto.getScheduled(),
                doctor, patient);
        return new SchedulingDTO(entity, true);
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

    public CounterScheduleDTO countTotal() {
        LocalDate today = LocalDate.now();
        Long fromDay = repository.countAllByDate(today);
        CalcDate calcDate = new CalcDate(today);
        Long fromWeek = repository.countAllByDateBetween(calcDate.getDateWeekStarts(), calcDate.getDateWeekEnds());
        Long fromMonth = repository.countAllByDateBetween(
                LocalDate.of(calcDate.getYear(), calcDate.getMonth(), 1),
                LocalDate.of(calcDate.getYear(), calcDate.getMonth(), calcDate.getLastDayOfMonth()));

        return new CounterScheduleDTO(fromDay, fromWeek, fromMonth);
    }

}
