package safetynet.org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import safetynet.org.dto.PersonDto;
import safetynet.org.exception.RessourceNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {
    @InjectMocks
    private AlertService alertService;

    @Mock
    private PersonService personService;

    @Mock
    private MedicalRecordService medicalRecordService;
    @Mock
    private FireStationService fireStationService;


    @Test
    public void communityByEmail() throws RessourceNotFoundException {
        // GIVEN
        PersonDto personDto = new PersonDto("abcd", "efgh",
                "1 rue de la paix ", "Paris", "75000", "01020304",
                "hicham@email.com");

        when(personService.getPersonsByCity(any())).thenReturn(List.of(personDto));



        // WHEN
        List<String> list = alertService.communityEmail("Paris");

        // THEN
        assertEquals(1, list.size());

    }

}