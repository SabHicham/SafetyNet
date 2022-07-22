package safetynet.org.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import safetynet.org.dto.FireStationDto;
import safetynet.org.service.FireStationService;
import org.assertj.core.util.Lists;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {FireStationController.class, FireStationService.class})
@EnableWebMvc
class FireStationControllerTest {

    @MockBean
    private FireStationService service;

    @Autowired
    private MockMvc mockMvc;



    @Test
    @DisplayName("GET /firestation ")
    public void getAllFireStationTest() throws Exception{
        FireStationDto fireStationDto1 = new FireStationDto("1 rue de Paris",1);
        FireStationDto fireStationDto2 = new FireStationDto("2 rue de Paris",2);

        doReturn(Lists.newArrayList(fireStationDto1,fireStationDto2)).when(service).getAllFireStation();

        mockMvc.perform(get("/firestation"))
                .andExpect(status().isOk());

        verify(service, times(1)).getAllFireStation();
    }

    @Test
    @DisplayName("POST /firestation")
    public void postFireStationTest() throws Exception {
        mockMvc.perform(post("/firestation").content(asJsonString(new FireStationDto("",0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        verify(service, times(1)).addFirestation(any());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("PUT /firestation")
    public void putFireStationTest() throws Exception {
        mockMvc.perform(put("/firestation")
                .content(asJsonString(new FireStationDto("", 0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        verify(service, times(1)).updateFireStation(any());
    }

    @Test
    @DisplayName("DELETE /firestation/station")
    public void deleteFireStationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation/station/{address}/{station}","A", 1))
                .andExpect(status().isOk());


        verify(service, times(1)).deleteFireStationByAddressOrStation("A", 1);
    }
}