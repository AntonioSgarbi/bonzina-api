package com.antoniosgarbi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testUpdate() {
        Doctor doctor = new Doctor();
        doctor.setPeriod(Period.MORNING);
        doctor.setSpeciality("Speciality");
        doctor.setEmail("jane.doe@example.org");
        doctor.setClinic(Clinic.ONE);
        doctor.setId(1);
        doctor.setRegister(1);
        doctor.setName("Name");
        doctor.setPhone("4105551212");

        Doctor doctor1 = new Doctor();
        doctor1.setPeriod(Period.MORNING);
        doctor1.setSpeciality("Speciality");
        doctor1.setEmail("jane.doe@example.org");
        doctor1.setClinic(Clinic.ONE);
        doctor1.setId(1);
        doctor1.setRegister(1);
        doctor1.setName("Name");
        doctor1.setPhone("4105551212");
        this.doctorRepository.<Doctor>save(doctor);
        this.doctorRepository.<Doctor>save(doctor1);
        this.doctorRepository.update("foo", "foo", "foo", 1, "foo", Clinic.ONE, Period.MORNING, 1);
        assertEquals(2, this.doctorRepository.findAll().size());
    }
}

