package com.antoniosgarbi.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.antoniosgarbi.dto.DoctorDTO;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;
import com.antoniosgarbi.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DoctorController.class})
@ExtendWith(SpringExtension.class)
public class DoctorControllerTest {
    @Autowired
    private DoctorController doctorController;

    @MockBean
    private DoctorService doctorService;

    @Test
    public void testFindAll() throws Exception {
        when(this.doctorService.findAll()).thenReturn(new ArrayList<DoctorDTO>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor");
        MockMvcBuilders.standaloneSetup(this.doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testFindAll2() throws Exception {
        ArrayList<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();
        doctorDTOList.add(new DoctorDTO());
        when(this.doctorService.findAll()).thenReturn(doctorDTOList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor");
        MockMvcBuilders.standaloneSetup(this.doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":null,\"name\":null,\"phone\":null,\"email\":null,\"register\":null,\"speciality\":null,\"clinic\":null,"
                                        + "\"period\":null,\"schedulings\":null}]"));
    }

    @Test
    public void testCount() throws Exception {
        when(this.doctorService.count()).thenReturn(3L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor/quantity");
        MockMvcBuilders.standaloneSetup(this.doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("3"));
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(this.doctorService).delete((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/doctor/{id}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.doctorController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testInsert() throws Exception {
        when(this.doctorService.findAll()).thenReturn(new ArrayList<DoctorDTO>());

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setPeriod(Period.MORNING);
        doctorDTO.setSpeciality("Speciality");
        doctorDTO.setEmail("jane.doe@example.org");
        doctorDTO.setClinic(Clinic.ONE);
        doctorDTO.setId(1);
        doctorDTO.setRegister(1);
        doctorDTO.setName("Name");
        doctorDTO.setPhone("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(doctorDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testInsert2() throws Exception {
        ArrayList<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();
        doctorDTOList.add(new DoctorDTO());
        when(this.doctorService.findAll()).thenReturn(doctorDTOList);

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setPeriod(Period.MORNING);
        doctorDTO.setSpeciality("Speciality");
        doctorDTO.setEmail("jane.doe@example.org");
        doctorDTO.setClinic(Clinic.ONE);
        doctorDTO.setId(1);
        doctorDTO.setRegister(1);
        doctorDTO.setName("Name");
        doctorDTO.setPhone("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(doctorDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":null,\"name\":null,\"phone\":null,\"email\":null,\"register\":null,\"speciality\":null,\"clinic\":null,"
                                        + "\"period\":null,\"schedulings\":null}]"));
    }

    @Test
    public void testUpdate() throws Exception {
        when(this.doctorService.findAll()).thenReturn(new ArrayList<DoctorDTO>());

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setPeriod(Period.MORNING);
        doctorDTO.setSpeciality("Speciality");
        doctorDTO.setEmail("jane.doe@example.org");
        doctorDTO.setClinic(Clinic.ONE);
        doctorDTO.setId(1);
        doctorDTO.setRegister(1);
        doctorDTO.setName("Name");
        doctorDTO.setPhone("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(doctorDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testUpdate2() throws Exception {
        ArrayList<DoctorDTO> doctorDTOList = new ArrayList<DoctorDTO>();
        doctorDTOList.add(new DoctorDTO());
        when(this.doctorService.findAll()).thenReturn(doctorDTOList);

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setPeriod(Period.MORNING);
        doctorDTO.setSpeciality("Speciality");
        doctorDTO.setEmail("jane.doe@example.org");
        doctorDTO.setClinic(Clinic.ONE);
        doctorDTO.setId(1);
        doctorDTO.setRegister(1);
        doctorDTO.setName("Name");
        doctorDTO.setPhone("4105551212");
        String content = (new ObjectMapper()).writeValueAsString(doctorDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":null,\"name\":null,\"phone\":null,\"email\":null,\"register\":null,\"speciality\":null,\"clinic\":null,"
                                        + "\"period\":null,\"schedulings\":null}]"));
    }
}

