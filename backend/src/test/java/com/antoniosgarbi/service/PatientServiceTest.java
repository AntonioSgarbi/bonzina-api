package com.antoniosgarbi.service;

import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.repository.PatientRepository;
import com.antoniosgarbi.util.PatientCreator;
import com.antoniosgarbi.util.PatientDTOCreator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    private PatientService service;
    @Mock
    private PatientRepository repository;

    @BeforeEach
    void setup() {
        service = new PatientService(repository);
    }

    @Test
    void insert() {
        given(repository.save(ArgumentMatchers.any(Patient.class))).willReturn(PatientCreator.createValidPatient());

        PatientDTO patientDTO = service.insert(PatientDTOCreator.createPatientDTO());

        assertNotNull(patientDTO);
        assertEquals(patientDTO.getId(), PatientDTOCreator.createPatientDTOWithId().getId());
        assertEquals(patientDTO.getName(), PatientDTOCreator.createPatientDTO().getName());
        assertEquals(patientDTO.getPhone(), PatientDTOCreator.createPatientDTO().getPhone());
        assertEquals(patientDTO.getEmail(), PatientDTOCreator.createPatientDTO().getEmail());
        assertEquals(patientDTO.getAddress(), PatientDTOCreator.createPatientDTO().getAddress());
        assertEquals(patientDTO.getBirthdate(), PatientDTOCreator.createPatientDTO().getBirthdate());
    }

    @Test
    void update() {
        PatientDTO patientDTO = PatientDTOCreator.createPatientDTOWithId();
//        String name = "Antônio";
//        String phone = "11222243333";
//        String email = "antonio@email.com";
//        String address = "random street";
//        LocalDate birthdate = LocalDate.of(2000, 01, 01);
//        PatientDTO patientDtoUpdated = new PatientDTO(patientDTO.getId(), name, phone, email, address, birthdate)
//        given(repository.update(ArgumentMatchers.any())).willReturn(PatientDTOCreator.createPatientDTOWithId()));

    }

    @Test
    void findAll() {
        PageImpl<Patient> page = new PageImpl<>(List.of(PatientCreator.createPatient()));
        BDDMockito.when(repository.findAll(ArgumentMatchers.any(PageRequest.class))).thenReturn(page);

        String expectedName = PatientCreator.createValidPatient().getName();

        Page<PatientDTO> patientPage = service.findAll(PageRequest.of(1,1));

        Assertions.assertNotNull(patientPage);

        Assertions.assertFalse(patientPage.isEmpty());

        Assertions.assertTrue(patientPage.getSize() == 1);

        Assertions.assertTrue(patientPage.toList().get(0).getName().equals(expectedName));
    }

    @Test
    void findByName() {
        PageImpl<Patient> page = new PageImpl<>(List.of(PatientCreator.createPatient()));
        BDDMockito.given(repository
                .findByNameContaining("antonio", PageRequest.of(1,1))).willReturn(page);


    }

    @Test
    void delete() {
    }

    @Test
    void count() {
    }
}