package com.antoniosgarbi.repository;

import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.entities.Scheduling;
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

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Scheduling SET date = :date, scheduled = :scheduled, "
            + "doctor = :doctor, patient = :patient WHERE id = :id")
    void update(
            @Param("id") Integer id,
            @Param("date") LocalDate date, @Param("scheduled") LocalTime scheduled,
            @Param("doctor") Doctor doctor, @Param("patient") Patient patient
    );
}
