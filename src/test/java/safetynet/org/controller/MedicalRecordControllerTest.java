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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.dto.PersonDto;
import safetynet.org.service.FireStationService;
import org.assertj.core.util.Lists;
import safetynet.org.service.MedicalRecordService;
import safetynet.org.service.PersonService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {MedicalRecordController.class, MedicalRecordService.class})
@EnableWebMvc
class MedicalRecordControllerTest {

    @MockBean
    private MedicalRecordService service;

    @Autowired
    private MockMvc mockMvc;



    @Test
    @DisplayName("GET /medicalRecord")
    public void getAllMedicalRecordTest() throws Exception{
        MedicalRecordDto medicalRecordDto1 = new MedicalRecordDto("", "", "", List.of(), List.of());
        MedicalRecordDto medicalRecordDto2 = new MedicalRecordDto("", "", "", List.of(), List.of());

        doReturn(Lists.newArrayList(medicalRecordDto1,medicalRecordDto2)).when(service).getAllMedicalRecord();

        mockMvc.perform(get("/medicalRecord"))
                .andExpect(status().isOk());

        verify(service, times(1)).getAllMedicalRecord();
    }

    @Test
    @DisplayName("POST /medicalRecord")
    public void postMedicalRecordTest() throws Exception {
        mockMvc.perform(post("/medicalRecord").content(asJsonString(new MedicalRecordDto("","",
                        ", ", List.of(), List.of())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        verify(service, times(1)).addMedicalRecord(any());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("PUT /MedicalRecord")
    public void putMedicalRecordTest() throws Exception {
        mockMvc.perform(put("/medicalRecord")
                .content(asJsonString(new MedicalRecordDto("", "", "",
                        List.of(), List.of())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        verify(service, times(1)).updateMedicalRecord(any());
    }

    @Test
    @DisplayName("DELETE /medicalRecord")
    public void deleteMedicalRecordTest() throws Exception {
        mockMvc.perform(delete("/medicalRecord")
                .content(asJsonString(new MedicalRecordDto("A","B",
                        ", ", List.of(), List.of())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(service, times(1)).deleteMedicalRecord("A", "B");
    }
}