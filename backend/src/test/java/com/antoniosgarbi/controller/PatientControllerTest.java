package com.antoniosgarbi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.entities.Patient;
import com.antoniosgarbi.repository.PatientRepository;
import com.antoniosgarbi.service.PatientService;

import java.time.LocalDate;

import java.util.ArrayList;

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

@ContextConfiguration(classes = {PatientController.class})
@ExtendWith(SpringExtension.class)
public class PatientControllerTest {
    @Autowired
    private PatientController patientController;

    @MockBean
    private PatientService patientService;

    @Test
    public void testConstructor() {
        // TODO: This test is incomplete.
        //   Reason: Nothing to assert: the constructed class does not have observers (e.g. getters or public fields).
        //   Add observers (e.g. getters or public fields) to the class.
        //   See https://diff.blue/R002

        new PatientController(new PatientService(mock(PatientRepository.class)));
    }

    @Test
    public void testFindAll() {
        PatientRepository patientRepository = mock(PatientRepository.class);
        when(patientRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Patient>(new ArrayList<Patient>()));
        ResponseEntity<Page<PatientDTO>> actualFindAllResult = (new PatientController(
                new PatientService(patientRepository))).findAll(null);
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualFindAllResult.toString());
        assertTrue(actualFindAllResult.getBody().toList().isEmpty());
        assertTrue(actualFindAllResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindAllResult.getStatusCode());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        verify(patientRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testFindAll2() {
        Patient patient = new Patient();
        patient.setEmail("jane.doe@example.org");
        patient.setId(1);
        patient.setName("Name");
        patient.setPhone("4105551212");
        patient.setAddress("42 Main St");
        patient.setBirthdate(LocalDate.ofEpochDay(1L));

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(patient);
        PatientRepository patientRepository = mock(PatientRepository.class);
        when(patientRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Patient>(patientList));
        ResponseEntity<Page<PatientDTO>> actualFindAllResult = (new PatientController(
                new PatientService(patientRepository))).findAll(null);
        assertEquals("<200 OK OK,Page 1 of 1 containing com.antoniosgarbi.dto.PatientDTO instances,[]>",
                actualFindAllResult.toString());
        assertEquals(1, actualFindAllResult.getBody().toList().size());
        assertTrue(actualFindAllResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindAllResult.getStatusCode());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        verify(patientRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testFindAll3() {
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

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(patient1);
        patientList.add(patient);
        PatientRepository patientRepository = mock(PatientRepository.class);
        when(patientRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Patient>(patientList));
        ResponseEntity<Page<PatientDTO>> actualFindAllResult = (new PatientController(
                new PatientService(patientRepository))).findAll(null);
        assertEquals("<200 OK OK,Page 1 of 1 containing com.antoniosgarbi.dto.PatientDTO instances,[]>",
                actualFindAllResult.toString());
        assertEquals(2, actualFindAllResult.getBody().toList().size());
        assertTrue(actualFindAllResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindAllResult.getStatusCode());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        verify(patientRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testFindByName() {
        PatientRepository patientRepository = mock(PatientRepository.class);
        when(patientRepository.findByNameContaining((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Patient>(new ArrayList<Patient>()));
        ResponseEntity<Page<PatientDTO>> actualFindByNameResult = (new PatientController(
                new PatientService(patientRepository))).findByName("Name", null);
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualFindByNameResult.toString());
        assertTrue(actualFindByNameResult.getBody().toList().isEmpty());
        assertTrue(actualFindByNameResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindByNameResult.getStatusCode());
        assertTrue(actualFindByNameResult.getHeaders().isEmpty());
        verify(patientRepository).findByNameContaining((String) any(), (org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testFindByName2() {
        Patient patient = new Patient();
        patient.setEmail("jane.doe@example.org");
        patient.setId(1);
        patient.setName("Name");
        patient.setPhone("4105551212");
        patient.setAddress("42 Main St");
        patient.setBirthdate(LocalDate.ofEpochDay(1L));

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(patient);
        PatientRepository patientRepository = mock(PatientRepository.class);
        when(patientRepository.findByNameContaining((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Patient>(patientList));
        ResponseEntity<Page<PatientDTO>> actualFindByNameResult = (new PatientController(
                new PatientService(patientRepository))).findByName("Name", null);
        assertEquals("<200 OK OK,Page 1 of 1 containing com.antoniosgarbi.dto.PatientDTO instances,[]>",
                actualFindByNameResult.toString());
        assertEquals(1, actualFindByNameResult.getBody().toList().size());
        assertTrue(actualFindByNameResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindByNameResult.getStatusCode());
        assertTrue(actualFindByNameResult.getHeaders().isEmpty());
        verify(patientRepository).findByNameContaining((String) any(), (org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testFindByName3() {
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

        ArrayList<Patient> patientList = new ArrayList<Patient>();
        patientList.add(patient1);
        patientList.add(patient);
        PatientRepository patientRepository = mock(PatientRepository.class);
        when(patientRepository.findByNameContaining((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Patient>(patientList));
        ResponseEntity<Page<PatientDTO>> actualFindByNameResult = (new PatientController(
                new PatientService(patientRepository))).findByName("Name", null);
        assertEquals("<200 OK OK,Page 1 of 1 containing com.antoniosgarbi.dto.PatientDTO instances,[]>",
                actualFindByNameResult.toString());
        assertEquals(2, actualFindByNameResult.getBody().toList().size());
        assertTrue(actualFindByNameResult.hasBody());
        assertEquals(HttpStatus.OK, actualFindByNameResult.getStatusCode());
        assertTrue(actualFindByNameResult.getHeaders().isEmpty());
        verify(patientRepository).findByNameContaining((String) any(), (org.springframework.data.domain.Pageable) any());
    }

    @Test
    public void testCount() throws Exception {
        when(this.patientService.count()).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patient/quantity");
        MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    @Test
    public void testUpdate() {
        PatientRepository patientRepository = mock(PatientRepository.class);
        doNothing().when(patientRepository)
                .update((String) any(), (String) any(), (String) any(), (String) any(), (LocalDate) any(), (Integer) any());
        PatientController patientController = new PatientController(new PatientService(patientRepository));
        ResponseEntity<PatientDTO> actualUpdateResult = patientController.update(new PatientDTO());
        assertTrue(actualUpdateResult.getHeaders().isEmpty());
        assertTrue(actualUpdateResult.hasBody());
        assertEquals(HttpStatus.OK, actualUpdateResult.getStatusCode());
        PatientDTO body = actualUpdateResult.getBody();
        assertNull(body.getEmail());
        assertNull(body.getBirthdate());
        assertNull(body.getAddress());
        assertNull(body.getPhone());
        assertNull(body.getId());
        assertNull(body.getName());
        verify(patientRepository).update((String) any(), (String) any(), (String) any(), (String) any(), (LocalDate) any(),
                (Integer) any());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(this.patientService).delete((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/patient/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDelete2() throws Exception {
        doNothing().when(this.patientService).delete((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/patient/{id}", 1);
        deleteResult.contentType("Not all who wander are lost");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.patientController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}

