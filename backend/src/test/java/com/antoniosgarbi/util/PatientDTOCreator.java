package com.antoniosgarbi.util;

import com.antoniosgarbi.dto.PatientDTO;

public class PatientDTOCreator {
    public static PatientDTO createPatientDTO() {
        PatientDTO patientDTO = new PatientDTO(PatientCreator.createPatient(), false);
        return patientDTO;
    }

    public static PatientDTO createPatientDTOWithId() {
        PatientDTO patientDTO = createPatientDTO();
        patientDTO.setId(1);
        return patientDTO;
    }

}
