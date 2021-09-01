package com.antoniosgarbi.util;

import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;

public class DoctorCreator {

    public static Doctor createDoctor() {
        Doctor doctor = new Doctor();

        doctor.setName("João");
        doctor.setPhone("48999989999");
        doctor.setEmail("joao@email.com");
        doctor.setRegister(123467);
        doctor.setSpeciality("cardiologista");
        doctor.setClinic(Clinic.ONE);
        doctor.setPeriod(Period.MORNING);

        return doctor;
    }

    public static Doctor createValidDoctor() {
        Doctor doctor = createDoctor();
        doctor.setId(1);
        return doctor;
    }

}
