package com.antoniosgarbi.service;

import com.antoniosgarbi.dto.DoctorDTO;
import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;
import com.antoniosgarbi.exception.BadRequestException;
import com.antoniosgarbi.repository.DoctorRepository;
import com.antoniosgarbi.util.DoctorCreator;
import com.antoniosgarbi.util.DoctorDTOCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    private DoctorService doctorService;
    @Mock
    private DoctorRepository doctorRepository;


    @BeforeEach
    void setup() {

        doctorService = new DoctorService(doctorRepository);

        BDDMockito.lenient().when(doctorRepository.save(ArgumentMatchers.any(Doctor.class)))
                        .thenReturn(DoctorCreator.createValidDoctor());

        BDDMockito.lenient().when(doctorRepository.findAll())
                .thenReturn(List.of(DoctorCreator.createDoctor()));

        BDDMockito.lenient().doNothing().when(doctorRepository).deleteById(ArgumentMatchers.eq(1));

        BDDMockito.lenient().doThrow(BadRequestException.class).when(doctorRepository).deleteById(2);

        BDDMockito.lenient().when(doctorRepository.count()).thenReturn(1L) ;

    }

    @Test
    @DisplayName("save returns anime when successful")
    void insert() {
        DoctorDTO doctor = doctorService.insert(DoctorDTOCreator.createDoctorDTO());
        Assertions.assertThat(doctor).isNotNull();
        Assertions.assertThat(doctor.getId()).isNotNull().isEqualTo(DoctorCreator.createValidDoctor().getId());
        Assertions.assertThat(doctor.getName()).isNotNull().isEqualTo(DoctorCreator.createDoctor().getName());
        Assertions.assertThat(doctor.getPhone()).isNotNull().isEqualTo(DoctorCreator.createDoctor().getPhone());
        Assertions.assertThat(doctor.getEmail()).isNotNull().isEqualTo(DoctorCreator.createDoctor().getEmail());
        Assertions.assertThat(doctor.getRegister()).isNotNull().isEqualTo(DoctorCreator.createDoctor().getRegister());
        Assertions.assertThat(doctor.getSpeciality()).isNotNull().isEqualTo(DoctorCreator.createDoctor().getSpeciality());
        Assertions.assertThat(doctor.getClinic()).isNotNull().isEqualTo(DoctorCreator.createDoctor().getClinic());
        Assertions.assertThat(doctor.getPeriod()).isNotNull().isEqualTo(DoctorCreator.createDoctor().getPeriod());

    }

    @Test
    @DisplayName("findAll returns list of doctor when successful")
    void findAll() {
        String expectedName = DoctorCreator.createValidDoctor().getName();

        List<DoctorDTO> list = doctorService.findAll();

        Assertions.assertThat(list)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(list.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("update is void and update all the given fields on parameters")
    void update() {
        String name = "Antônio";
        String phone = "11222243333";
        String email = "antonio@email.com";
        Integer register = 555555555;
        String speciality = "neurologista";
        Clinic clinic = Clinic.TWO;
        Period period = Period.AFTERNOON;

        DoctorDTO doctor = DoctorDTOCreator.createDoctorDTO();
        doctor = doctorService.insert(doctor);

        DoctorDTO doctorUpdated = new DoctorDTO(doctor.getId(), name, phone, email, register, speciality, clinic, period);

        doctor = doctorService.update(doctorUpdated);

        Assertions.assertThat(doctor.getName()).isEqualTo(name);
        Assertions.assertThat(doctor.getPhone()).isEqualTo(phone);
        Assertions.assertThat(doctor.getEmail()).isEqualTo(email);
        Assertions.assertThat(doctor.getRegister()).isEqualTo(register);
        Assertions.assertThat(doctor.getSpeciality()).isEqualTo(speciality);
        Assertions.assertThat(doctor.getClinic()).isEqualTo(clinic);
        Assertions.assertThat(doctor.getPeriod()).isEqualTo(period);

        Assertions.assertThatCode(() ->doctorService.update(DoctorDTOCreator.createDoctorDTOWithId()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete object if id exists")
    void delete() {
        Assertions.assertThatCode(() ->doctorService.delete(1))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("delete throws badRequestException if id dont exists")
    void deleteThrowsException(){
        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> doctorService.delete(2));
    }

    @Test
    @DisplayName("count returns a long")
    void count() {
        Assertions.assertThat(doctorService.count()).isGreaterThanOrEqualTo(0L);
    }
}