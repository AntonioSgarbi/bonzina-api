package com.antoniosgarbi.util;

import com.antoniosgarbi.entities.Patient;

import java.time.LocalDate;

public class PatientCreator {

    public static Patient createPatient() {
        Patient patient = new Patient();

        patient.setName("Antônio Sgarbi");
        patient.setPhone("48988882222");
        patient.setEmail("antonio@email.com");
        patient.setAddress("R. Meu endereço, 100");
        patient.setBirthdate(LocalDate.of(1997, 12, 03));

        return patient;
    }

    public static Patient createValidPatient() {
        Patient patient = createPatient();
        patient.setId(1);

        return patient;
    }

}
