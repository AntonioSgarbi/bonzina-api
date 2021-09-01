package com.antoniosgarbi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.antoniosgarbi.entities.Patient;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PatientRepositoryTest {
    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testUpdate() {
        Patient patient = new Patient();
        patient.setEmail("jane.doe@example.org");
        patient.setId(1);
        patient.setName("Name");
        patient.setPhone("4105551212");
        patient.setAddress("42 Main St");
        patient.setBirthdate(LocalDate.ofEpochDay(1L));

        Patient patient1 = new Patient();
        patient1.setEmail("jane.doe@example.org");
        patient1.setId(1);
        patient1.setName("Name");
        patient1.setPhone("4105551212");
        patient1.setAddress("42 Main St");
        patient1.setBirthdate(LocalDate.ofEpochDay(1L));
        this.patientRepository.<Patient>save(patient);
        this.patientRepository.<Patient>save(patient1);
        this.patientRepository.update("foo", "foo", "foo", "foo", LocalDate.ofEpochDay(1L), 1);
        assertEquals(2, this.patientRepository.findAll().size());
    }

    @Test
    public void testFindByNameContaining() {
        Patient patient = new Patient();
        patient.setEmail("jane.doe@example.org");
        patient.setId(1);
        patient.setName("Name");
        patient.setPhone("4105551212");
        patient.setAddress("42 Main St");
        patient.setBirthdate(LocalDate.ofEpochDay(1L));

        Patient patient1 = new Patient();
        patient1.setEmail("jane.doe@example.org");
        patient1.setId(1);
        patient1.setName("Name");
        patient1.setPhone("4105551212");
        patient1.setAddress("42 Main St");
        patient1.setBirthdate(LocalDate.ofEpochDay(1L));
        this.patientRepository.<Patient>save(patient);
        this.patientRepository.<Patient>save(patient1);
        assertTrue(this.patientRepository.findByNameContaining("foo", null).toList().isEmpty());
    }
}

