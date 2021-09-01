package com.antoniosgarbi.util;

import com.antoniosgarbi.dto.DoctorDTO;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;

public class DoctorDTOCreator {

    public static DoctorDTO createDoctorDTO() {

        DoctorDTO doctorDTO = new DoctorDTO(DoctorCreator.createDoctor(), false);

        return doctorDTO;
    }

    public static DoctorDTO createDoctorDTOWithId() {

        DoctorDTO doctorDTO = new DoctorDTO(DoctorCreator.createValidDoctor(), false);

        return doctorDTO;
    }

}
