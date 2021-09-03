package com.antoniosgarbi.repository;

import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.entities.Scheduling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Integer> {

    Long countAllByDate(LocalDate date);

    Page<Scheduling> findAllByDate(LocalDate date, Pageable pageable);

    Page<Scheduling> findAllByPatientNameContaining(String patient, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Scheduling SET date = :date, scheduled = :scheduled, "
            + "doctor = :doctor, patient = :patient WHERE id = :id")
    void update(
            @Param("id") Integer id,
            @Param("date") LocalDate date, @Param("scheduled") LocalTime scheduled,
            @Param("doctor") Doctor doctor, @Param("patient") Patient patient
    );

    @Query(value = "SELECT COUNT(*) FROM tb_scheduling WHERE date BETWEEN :monthStart AND :monthEnd",
            nativeQuery=true)
    long countAllByDateBetween(
            @Param("monthStart") LocalDate monthStart, @Param("monthEnd") LocalDate monthEnd
    );

    @Query(value = "SELECT * FROM tb_scheduling WHERE date BETWEEN ?1 AND ?2 ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) FROM tb_scheduling WHERE date BETWEEN ?1 AND ?2",
            nativeQuery = true)
    Page<Scheduling> findByDateBetween(
            LocalDate dateStart, LocalDate dateEnd, Pageable pageable
    );



}
