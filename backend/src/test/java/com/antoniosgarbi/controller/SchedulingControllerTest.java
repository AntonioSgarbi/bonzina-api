package com.antoniosgarbi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.antoniosgarbi.service.SchedulingService;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {SchedulingController.class})
@ExtendWith(SpringExtension.class)
public class SchedulingControllerTest {
    @Autowired
    private SchedulingController schedulingController;

    @MockBean
    private SchedulingService schedulingService;

    @Test
    public void testFindAll() {
        SchedulingRepository schedulingRepository = mock(SchedulingRepository.class);
        when(schedulingRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Scheduling>(new ArrayList<Scheduling>()));
        ResponseEntity<Page<SchedulingDTO>> actualFindAllResult = (new SchedulingController(
                new SchedulingService(schedulingRepository))).findAll(null);
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualFindAllResult.toString());
        assertTrue(actualFindAllResult.getBody().toList().isEmpty());
        assertTrue(actualFindAllResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindAllResult.getStatusCode());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        verify(schedulingRepository).findAll((org.springframework.data.domain.Pageable) any());
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
        SchedulingRepository schedulingRepository = mock(SchedulingRepository.class);
        when(schedulingRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Scheduling>(schedulingList));
        ResponseEntity<Page<SchedulingDTO>> actualFindAllResult = (new SchedulingController(
                new SchedulingService(schedulingRepository))).findAll(null);
        assertEquals("<200 OK OK,Page 1 of 1 containing com.antoniosgarbi.dto.SchedulingDTO instances,[]>",
                actualFindAllResult.toString());
        List<SchedulingDTO> toListResult = actualFindAllResult.getBody().toList();
        assertEquals(1, toListResult.size());
        assertTrue(actualFindAllResult.hasBody());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualFindAllResult.getStatusCode());
        SchedulingDTO getResult = toListResult.get(0);
        assertEquals("1970-01-02", getResult.getDate().toString());
        assertEquals("01:01", getResult.getScheduled().toString());
        assertEquals(1, getResult.getId().intValue());
        PatientDTO patient1 = getResult.getPatient();
        assertEquals("4105551212", patient1.getPhone());
        DoctorDTO doctor1 = getResult.getDoctor();
        assertEquals("Speciality", doctor1.getSpeciality());
        assertEquals(0, doctor1.getRegister().intValue());
        assertEquals("4105551212", doctor1.getPhone());
        assertEquals(Period.MORNING, doctor1.getPeriod());
        assertEquals("Name", doctor1.getName());
        assertEquals(1, doctor1.getId().intValue());
        assertEquals("jane.doe@example.org", doctor1.getEmail());
        assertEquals(Clinic.ONE, doctor1.getClinic());
        assertEquals("Name", patient1.getName());
        assertEquals(1, patient1.getId().intValue());
        assertEquals("jane.doe@example.org", patient1.getEmail());
        assertEquals("1970-01-02", patient1.getBirthdate().toString());
        assertEquals("42 Main St", patient1.getAddress());
        verify(schedulingRepository).findAll((org.springframework.data.domain.Pageable) any());
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
        SchedulingRepository schedulingRepository = mock(SchedulingRepository.class);
        when(schedulingRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Scheduling>(schedulingList));
        ResponseEntity<Page<SchedulingDTO>> actualFindAllResult = (new SchedulingController(
                new SchedulingService(schedulingRepository))).findAll(null);
        assertEquals("<200 OK OK,Page 1 of 1 containing com.antoniosgarbi.dto.SchedulingDTO instances,[]>",
                actualFindAllResult.toString());
        List<SchedulingDTO> toListResult = actualFindAllResult.getBody().toList();
        assertEquals(2, toListResult.size());
        assertTrue(actualFindAllResult.hasBody());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualFindAllResult.getStatusCode());
        SchedulingDTO getResult = toListResult.get(1);
        assertEquals("01:01", getResult.getScheduled().toString());
        SchedulingDTO getResult1 = toListResult.get(0);
        assertEquals("01:01", getResult1.getScheduled().toString());
        assertEquals(1, getResult1.getId().intValue());
        assertEquals("1970-01-02", getResult.getDate().toString());
        assertEquals(1, getResult.getId().intValue());
        assertEquals("1970-01-02", getResult1.getDate().toString());
        DoctorDTO doctor2 = getResult1.getDoctor();
        assertEquals(0, doctor2.getRegister().intValue());
        assertEquals("4105551212", doctor2.getPhone());
        assertEquals(Period.MORNING, doctor2.getPeriod());
        assertEquals("Name", doctor2.getName());
        assertEquals(1, doctor2.getId().intValue());
        assertEquals("jane.doe@example.org", doctor2.getEmail());
        assertEquals(Clinic.ONE, doctor2.getClinic());
        DoctorDTO doctor3 = getResult.getDoctor();
        assertEquals(0, doctor3.getRegister().intValue());
        assertEquals("4105551212", doctor3.getPhone());
        assertEquals(Period.MORNING, doctor3.getPeriod());
        assertEquals("Name", doctor3.getName());
        assertEquals(1, doctor3.getId().intValue());
        assertEquals("jane.doe@example.org", doctor3.getEmail());
        assertEquals(Clinic.ONE, doctor3.getClinic());
        PatientDTO patient2 = getResult.getPatient();
        assertEquals("4105551212", patient2.getPhone());
        assertEquals("Name", patient2.getName());
        assertEquals(1, patient2.getId().intValue());
        assertEquals("jane.doe@example.org", patient2.getEmail());
        assertEquals("1970-01-02", patient2.getBirthdate().toString());
        assertEquals("42 Main St", patient2.getAddress());
        PatientDTO patient3 = getResult1.getPatient();
        assertEquals("jane.doe@example.org", patient3.getEmail());
        assertEquals("1970-01-02", patient3.getBirthdate().toString());
        assertEquals("42 Main St", patient3.getAddress());
        assertEquals(1, patient3.getId().intValue());
        assertEquals("4105551212", patient3.getPhone());
        assertEquals("Speciality", doctor3.getSpeciality());
        assertEquals("Name", patient3.getName());
        verify(schedulingRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testCount() throws Exception {
        when(this.schedulingService.count()).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/scheduling/quantity");
        MockMvcBuilders.standaloneSetup(this.schedulingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    @Test
    public void testUpdate() {
        SchedulingService schedulingService = mock(SchedulingService.class);
        when(schedulingService.update((SchedulingDTO) any())).thenReturn(new SchedulingDTO());
        SchedulingController schedulingController = new SchedulingController(schedulingService);
        ResponseEntity<SchedulingDTO> actualUpdateResult = schedulingController.update(new SchedulingDTO());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        assertTrue(actualUpdateResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        verify(schedulingService).update((SchedulingDTO) any());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(this.schedulingService).delete((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/scheduling/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.schedulingController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

