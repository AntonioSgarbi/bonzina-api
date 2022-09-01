package com.antoniosgarbi.service;

import com.antoniosgarbi.dto.SchedulingCounter;
import com.antoniosgarbi.dto.SchedulingDTO;
import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.entities.Scheduling;
import com.antoniosgarbi.exception.BadRequestException;
import com.antoniosgarbi.repository.SchedulingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SchedulingService {

    private final SchedulingRepository repository;

    public SchedulingService(SchedulingRepository repository) {
        this.repository = repository;
    }

    public Page<SchedulingDTO> findAll(Pageable pageable) {
        Page<Scheduling> pageEntity = repository.findAll(pageable);
        return pageEntity
                .map(SchedulingDTO::new);
    }

    public Page<SchedulingDTO> findByPatientName(String name, Pageable pageable) {
        Page<Scheduling> pageEntity = repository.findAllByPatientNameContainingIgnoreCase(name, pageable);
        return pageEntity.map(SchedulingDTO::new);
    }

    public Page<SchedulingDTO> findByDate(String dateString, Pageable pageable) {
        LocalDate date = LocalDate.parse(dateString);
        Page<Scheduling> pageEntity = repository.findAllByDate(date,pageable);
        return pageEntity.map(SchedulingDTO::new);
    }

    public Page<SchedulingDTO> findByDateToday(Pageable pageable) {
        Page<Scheduling> pageEntity = repository
                .findAllByDate(
                        LocalDate.now(),
                        pageable
                );
        return pageEntity.map(SchedulingDTO::new);
    }

    public Page<SchedulingDTO> findByDateThisWeek(Pageable pageable) {
        CalcDate date = getCalcDate();
        Page<Scheduling> pageEntity = repository
                .findAllByDateGreaterThanEqualAndDateLessThanEqual(
                        date.getDateWeekStarts(),
                        date.getDateWeekEnds(),
                        pageable
                );
        return pageEntity
                .map(SchedulingDTO::new);
    }

    public Page<SchedulingDTO> findByDateThisMonth(Pageable pageable) {
        CalcDate date = getCalcDate();
        Page<Scheduling> pageEntity = repository
                .findAllByDateGreaterThanEqualAndDateLessThanEqual(
                        LocalDate.of(date.getYear(), date.getMonth(), 1),
                        LocalDate.of(date.getYear(), date.getMonth(), date.getLastDayOfMonth()),
                        pageable
                );
        return pageEntity
                .map(SchedulingDTO::new);
    }

    @Transactional
    public SchedulingDTO insert(SchedulingDTO dto) {
        Scheduling entity =  new Scheduling(
                null,
                dto.getDate(),
                dto.getScheduled(),
                new Doctor(dto.getDoctor()),
                new Patient(dto.getPatient())
        );
        entity = repository.save(entity);
        return new SchedulingDTO(entity);
    }

    @Transactional
    public SchedulingDTO update(SchedulingDTO dto) {
        Doctor doctor =  new Doctor(dto.getDoctor());
        Patient patient = new Patient(dto.getPatient());

        Scheduling entity =  new Scheduling(
                dto.getId(),
                dto.getDate(),
                dto.getScheduled(),
                doctor,
                patient
        );
        repository.update(
                dto.getId(),
                dto.getDate(),
                dto.getScheduled(),
                doctor,
                patient);
        return new SchedulingDTO(entity);
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

    public SchedulingCounter countTotal() {
        CalcDate date = getCalcDate();
        Long fromDay = repository.countAllByDate(date.getDate());
        Long fromWeek = repository.countAllByDateBetween(date.getDateWeekStarts(), date.getDateWeekEnds());
        Long fromMonth = repository.countAllByDateBetween(
                LocalDate.of(date.getYear(), date.getMonth(), 1),
                LocalDate.of(date.getYear(), date.getMonth(), date.getLastDayOfMonth()));
        return new SchedulingCounter(fromDay, fromWeek, fromMonth);
    }

    private CalcDate getCalcDate() {
     return new CalcDate(LocalDate.now());
    }
}
