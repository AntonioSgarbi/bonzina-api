package com.antoniosgarbi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.antoniosgarbi.dto.DoctorDTO;
import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.dto.SchedulingDTO;
import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.entities.Scheduling;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;
import com.antoniosgarbi.repository.SchedulingRepository;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SchedulingService.class})
@ExtendWith(SpringExtension.class)
public class SchedulingServiceTest {
    @MockBean
    private SchedulingRepository schedulingRepository;

    @Autowired
    private SchedulingService schedulingService;

    @Test
    public void testFindAll() {
        when(this.schedulingRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Scheduling>(new ArrayList<Scheduling>()));
        assertTrue(this.schedulingService.findAll(null).toList().isEmpty());
        verify(this.schedulingRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testFindAll2() {
        Doctor doctor = new Doctor();
        doctor.setPeriod(Period.MORNING);
        doctor.setSpeciality("Speciality");
        doctor.setEmail("jane.doe@example.org");
        doctor.setClinic(Clinic.ONE);
        doctor.setId(1);
        doctor.setRegister(0);
        doctor.setName("Name");
        doctor.setPhone("4105551212");

        Patient patient = new Patient();
        patient.setEmail("jane.doe@example.org");
        patient.setId(1);
        patient.setName("Name");
        patient.setPhone("4105551212");
        patient.setAddress("42 Main St");
        patient.setBirthdate(LocalDate.ofEpochDay(1L));

        Scheduling scheduling = new Scheduling();
        scheduling.setDoctor(doctor);
        scheduling.setDate(LocalDate.ofEpochDay(1L));
        scheduling.setId(1);
        scheduling.setScheduled(LocalTime.of(1, 1));
        scheduling.setPatient(patient);

        ArrayList<Scheduling> schedulingList = new ArrayList<Scheduling>();
        schedulingList.add(scheduling);
        PageImpl<Scheduling> pageImpl = new PageImpl<Scheduling>(schedulingList);
        when(this.schedulingRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        List<SchedulingDTO> toListResult = this.schedulingService.findAll(null).toList();
        assertEquals(1, toListResult.size());
        SchedulingDTO getResult = toListResult.get(0);
        assertEquals("1970-01-02", getResult.getDate().toString());
        assertEquals("01:01", getResult.getScheduled().toString());
        assertEquals(1, getResult.getId().intValue());
        PatientDTO patient1 = getResult.getPatient();
        assertEquals("Name", patient1.getName());
        DoctorDTO doctor1 = getResult.getDoctor();
        assertEquals("Speciality", doctor1.getSpeciality());
        assertEquals(0, doctor1.getRegister().intValue());
        assertEquals("4105551212", doctor1.getPhone());
        assertEquals(Period.MORNING, doctor1.getPeriod());
        assertEquals("Name", doctor1.getName());
        assertEquals(1, doctor1.getId().intValue());
        assertEquals("jane.doe@example.org", doctor1.getEmail());
        assertEquals(Clinic.ONE, doctor1.getClinic());
        assertEquals(1, patient1.getId().intValue());
        assertEquals("jane.doe@example.org", patient1.getEmail());
        assertEquals("1970-01-02", patient1.getBirthdate().toString());
        assertEquals("42 Main St", patient1.getAddress());
        assertEquals("4105551212", patient1.getPhone());
        verify(this.schedulingRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testFindAll3() {
        Doctor doctor = new Doctor();
        doctor.setPeriod(Period.MORNING);
        doctor.setSpeciality("Speciality");
        doctor.setEmail("jane.doe@example.org");
        doctor.setClinic(Clinic.ONE);
        doctor.setId(1);
        doctor.setRegister(0);
        doctor.setName("Name");
        doctor.setPhone("4105551212");

        Patient patient = new Patient();
        patient.setEmail("jane.doe@example.org");
        patient.setId(1);
        patient.setName("Name");
        patient.setPhone("4105551212");
        patient.setAddress("42 Main St");
        patient.setBirthdate(LocalDate.ofEpochDay(1L));

        Scheduling scheduling = new Scheduling();
        scheduling.setDoctor(doctor);
        scheduling.setDate(LocalDate.ofEpochDay(1L));
        scheduling.setId(1);
        scheduling.setScheduled(LocalTime.of(1, 1));
        scheduling.setPatient(patient);

        Doctor doctor1 = new Doctor();
        doctor1.setPeriod(Period.MORNING);
        doctor1.setSpeciality("Speciality");
        doctor1.setEmail("jane.doe@example.org");
        doctor1.setClinic(Clinic.ONE);
        doctor1.setId(1);
        doctor1.setRegister(0);
        doctor1.setName("Name");
        doctor1.setPhone("4105551212");

        Patient patient1 = new Patient();
        patient1.setEmail("jane.doe@example.org");
        patient1.setId(1);
        patient1.setName("Name");
        patient1.setPhone("4105551212");
        patient1.setAddress("42 Main St");
        patient1.setBirthdate(LocalDate.ofEpochDay(1L));

        Scheduling scheduling1 = new Scheduling();
        scheduling1.setDoctor(doctor1);
        scheduling1.setDate(LocalDate.ofEpochDay(1L));
        scheduling1.setId(1);
        scheduling1.setScheduled(LocalTime.of(1, 1));
        scheduling1.setPatient(patient1);

        ArrayList<Scheduling> schedulingList = new ArrayList<Scheduling>();
        schedulingList.add(scheduling1);
        schedulingList.add(scheduling);
        PageImpl<Scheduling> pageImpl = new PageImpl<Scheduling>(schedulingList);
        when(this.schedulingRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);
        List<SchedulingDTO> toListResult = this.schedulingService.findAll(null).toList();
        assertEquals(2, toListResult.size());
        SchedulingDTO getResult = toListResult.get(0);
        assertEquals("01:01", getResult.getScheduled().toString());
        SchedulingDTO getResult1 = toListResult.get(1);
        assertEquals("01:01", getResult1.getScheduled().toString());
        assertEquals(1, getResult1.getId().intValue());
        assertEquals("1970-01-02", getResult.getDate().toString());
        assertEquals(1, getResult.getId().intValue());
        assertEquals("1970-01-02", getResult1.getDate().toString());
        DoctorDTO doctor2 = getResult.getDoctor();
        assertEquals(0, doctor2.getRegister().intValue());
        DoctorDTO doctor3 = getResult1.getDoctor();
        assertEquals("Speciality", doctor3.getSpeciality());
        assertEquals(0, doctor3.getRegister().intValue());
        assertEquals("4105551212", doctor3.getPhone());
        assertEquals(Period.MORNING, doctor3.getPeriod());
        assertEquals("Name", doctor3.getName());
        assertEquals(1, doctor3.getId().intValue());
        assertEquals("jane.doe@example.org", doctor3.getEmail());
        assertEquals(Clinic.ONE, doctor3.getClinic());
        assertEquals("4105551212", doctor2.getPhone());
        assertEquals(Period.MORNING, doctor2.getPeriod());
        assertEquals("Name", doctor2.getName());
        assertEquals(1, doctor2.getId().intValue());
        assertEquals("jane.doe@example.org", doctor2.getEmail());
        assertEquals(Clinic.ONE, doctor2.getClinic());
        PatientDTO patient2 = getResult.getPatient();
        assertEquals("Name", patient2.getName());
        assertEquals(1, patient2.getId().intValue());
        assertEquals("jane.doe@example.org", patient2.getEmail());
        assertEquals("1970-01-02", patient2.getBirthdate().toString());
        assertEquals("42 Main St", patient2.getAddress());
        PatientDTO patient3 = getResult1.getPatient();
        assertEquals("1970-01-02", patient3.getBirthdate().toString());
        assertEquals("42 Main St", patient3.getAddress());
        assertEquals("4105551212", patient2.getPhone());
        assertEquals("jane.doe@example.org", patient3.getEmail());
        assertEquals("Name", patient3.getName());
        assertEquals("4105551212", patient3.getPhone());
        assertEquals("Speciality", doctor2.getSpeciality());
        assertEquals(1, patient3.getId().intValue());
        verify(this.schedulingRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testFindAll4() {
        when(this.schedulingRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Scheduling>(new ArrayList<Scheduling>()));
        assertTrue(this.schedulingService.findAll(null).toList().isEmpty());
        verify(this.schedulingRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testInsert() {
        Doctor doctor = new Doctor();
        doctor.setPeriod(Period.MORNING);
        doctor.setSpeciality("Speciality");
        doctor.setEmail("jane.doe@example.org");
        doctor.setClinic(Clinic.ONE);
        doctor.setId(1);
        doctor.setRegister(1);
        doctor.setName("Name");
        doctor.setPhone("4105551212");

        Patient patient = new Patient();
        patient.setEmail("jane.doe@example.org");
        patient.setId(1);
        patient.setName("Name");
        patient.setPhone("4105551212");
        patient.setAddress("42 Main St");
        patient.setBirthdate(LocalDate.ofEpochDay(1L));

        Scheduling scheduling = new Scheduling();
        scheduling.setDoctor(doctor);
        scheduling.setDate(LocalDate.ofEpochDay(1L));
        scheduling.setId(1);
        scheduling.setScheduled(LocalTime.of(1, 1));
        scheduling.setPatient(patient);
        SchedulingRepository schedulingRepository = mock(SchedulingRepository.class);
        when(schedulingRepository.save((Scheduling) any())).thenReturn(scheduling);
        SchedulingService schedulingService = new SchedulingService(schedulingRepository);
        DoctorDTO doctorDTO = mock(DoctorDTO.class);
        when(doctorDTO.getPeriod()).thenReturn(Period.MORNING);
        when(doctorDTO.getClinic()).thenReturn(Clinic.ONE);
        when(doctorDTO.getSpeciality()).thenReturn("foo");
        when(doctorDTO.getRegister()).thenReturn(1);
        when(doctorDTO.getEmail()).thenReturn("foo");
        when(doctorDTO.getPhone()).thenReturn("foo");
        when(doctorDTO.getName()).thenReturn("foo");
        when(doctorDTO.getId()).thenReturn(1);

        SchedulingDTO schedulingDTO = new SchedulingDTO();
        schedulingDTO.setPatient(new PatientDTO());
        schedulingDTO.setDoctor(doctorDTO);
        SchedulingDTO actualInsertResult = schedulingService.insert(schedulingDTO);
        assertEquals("1970-01-02", actualInsertResult.getDate().toString());
        assertEquals("01:01", actualInsertResult.getScheduled().toString());
        assertEquals(1, actualInsertResult.getId().intValue());
        PatientDTO patient1 = actualInsertResult.getPatient();
        assertEquals("Name", patient1.getName());
        DoctorDTO doctor1 = actualInsertResult.getDoctor();
        assertEquals("Speciality", doctor1.getSpeciality());
        assertEquals(1, doctor1.getRegister().intValue());
        assertEquals("4105551212", doctor1.getPhone());
        assertEquals(Period.MORNING, doctor1.getPeriod());
        assertEquals("Name", doctor1.getName());
        assertEquals(1, doctor1.getId().intValue());
        assertEquals("jane.doe@example.org", doctor1.getEmail());
        assertEquals(Clinic.ONE, doctor1.getClinic());
        assertEquals(1, patient1.getId().intValue());
        assertEquals("jane.doe@example.org", patient1.getEmail());
        assertEquals("1970-01-02", patient1.getBirthdate().toString());
        assertEquals("42 Main St", patient1.getAddress());
        assertEquals("4105551212", patient1.getPhone());
        verify(schedulingRepository).save((Scheduling) any());
        verify(doctorDTO).getClinic();
        verify(doctorDTO).getEmail();
        verify(doctorDTO).getId();
        verify(doctorDTO).getName();
        verify(doctorDTO).getPeriod();
        verify(doctorDTO).getPhone();
        verify(doctorDTO).getRegister();
        verify(doctorDTO).getSpeciality();
    }

    @Test
    public void testUpdate() {
        doNothing().when(this.schedulingRepository)
                .update((Integer) any(), (LocalDate) any(), (LocalTime) any(), (com.antoniosgarbi.entities.Doctor) any(),
                        (com.antoniosgarbi.entities.Patient) any());
        DoctorDTO doctorDTO = mock(DoctorDTO.class);
        when(doctorDTO.getPeriod()).thenReturn(Period.MORNING);
        when(doctorDTO.getClinic()).thenReturn(Clinic.ONE);
        when(doctorDTO.getSpeciality()).thenReturn("foo");
        when(doctorDTO.getRegister()).thenReturn(1);
        when(doctorDTO.getEmail()).thenReturn("foo");
        when(doctorDTO.getPhone()).thenReturn("foo");
        when(doctorDTO.getName()).thenReturn("foo");
        when(doctorDTO.getId()).thenReturn(1);

        SchedulingDTO schedulingDTO = new SchedulingDTO();
        schedulingDTO.setPatient(new PatientDTO());
        schedulingDTO.setDoctor(doctorDTO);
        SchedulingDTO actualUpdateResult = this.schedulingService.update(schedulingDTO);
        assertNull(actualUpdateResult.getDate());
        assertNull(actualUpdateResult.getScheduled());
        assertNull(actualUpdateResult.getId());
        PatientDTO patient = actualUpdateResult.getPatient();
        assertNull(patient.getName());
        DoctorDTO doctor = actualUpdateResult.getDoctor();
        assertEquals("foo", doctor.getSpeciality());
        assertEquals(1, doctor.getRegister().intValue());
        assertEquals("foo", doctor.getPhone());
        assertEquals(Period.MORNING, doctor.getPeriod());
        assertEquals("foo", doctor.getName());
        assertEquals(1, doctor.getId().intValue());
        assertEquals("foo", doctor.getEmail());
        assertEquals(Clinic.ONE, doctor.getClinic());
        assertNull(patient.getId());
        assertNull(patient.getEmail());
        assertNull(patient.getBirthdate());
        assertNull(patient.getAddress());
        assertNull(patient.getPhone());
        verify(this.schedulingRepository).update((Integer) any(), (LocalDate) any(), (LocalTime) any(),
                (com.antoniosgarbi.entities.Doctor) any(), (com.antoniosgarbi.entities.Patient) any());
        verify(doctorDTO).getClinic();
        verify(doctorDTO).getEmail();
        verify(doctorDTO).getId();
        verify(doctorDTO).getName();
        verify(doctorDTO).getPeriod();
        verify(doctorDTO).getPhone();
        verify(doctorDTO).getRegister();
        verify(doctorDTO).getSpeciality();
    }

    @Test
    public void testDelete() {
        // TODO: This test is incomplete.
        //   Reason: No meaningful assertions found.
        //   To help Diffblue Cover to find assertions, please add getters to the
        //   class under test that return fields written by the method under test.
        //   See https://diff.blue/R004

        this.schedulingService.delete(1);
    }

    @Test
    public void testCount() {
        when(this.schedulingRepository.count()).thenReturn(3L);
        assertEquals(3L, this.schedulingService.count().longValue());
        verify(this.schedulingRepository).count();
    }
}

