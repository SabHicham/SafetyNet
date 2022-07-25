package safetynet.org.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import safetynet.org.controller.PersonController;
import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.dto.PersonDto;
import safetynet.org.service.FireStationService;
import org.assertj.core.util.Lists;
import safetynet.org.service.PersonService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {PersonController.class, PersonService.class})
@EnableWebMvc
class PersonControllerTest {

    @MockBean
    private PersonService service;

    @Autowired
    private MockMvc mockMvc;



    @Test
    @DisplayName("GET /person ")
    public void getAllPersonTest() throws Exception{
        PersonDto personDto1 = new PersonDto("", "", "", "", "", "", "");
        PersonDto personDto2 = new PersonDto("", "", "", "", "", "", "");

        doReturn(Lists.newArrayList(personDto1,personDto2)).when(service).getAllPerson();

        mockMvc.perform(get("/person"))
                .andExpect(status().isOk());

        verify(service, times(1)).getAllPerson();
    }

    @Test
    @DisplayName("POST /person")
    public void postPersonTest() throws Exception {
        mockMvc.perform(post("/person").content(asJsonString(new PersonDto("","",
                        ", ", "", "", "", "")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        verify(service, times(1)).addPerson(any());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("PUT /person")
    public void putFireStationTest() throws Exception {
        mockMvc.perform(put("/person")
                .content(asJsonString(new PersonDto("", "", "",
                        "", "", "", "")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        verify(service, times(1)).updatePerson(any());
    }

    /*@Test
    @DisplayName("DELETE /person")
    public void deletePersonTest() throws Exception {
        mockMvc.perform(delete("/person")
                        .content(asJsonString(new PersonDto("", "", "", "", "", "", "")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());



        verify(service, times(1)).deletePerson("A", "B");
    }*/
}