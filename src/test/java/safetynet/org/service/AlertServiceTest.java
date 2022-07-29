package safetynet.org.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import safetynet.org.dto.*;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.MedicalRecord;

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

    @Test
    public void personByStationNumberTest() throws RessourceNotFoundException {
        //GIVEN
        FireStationDto fireStationDto = new FireStationDto();
        fireStationDto.setAddress("1er rue");

        List<PersonDto> personDtoList  = List.of(
                new PersonDto("prenom1", "lastName1", "1er rue", "", "", "", ""),
                new PersonDto("prenom2", "lastName2", "2eme rue", "", "", "", "")
                );
        when(fireStationService.getFireStationByNumber(2)).thenReturn(fireStationDto);
        when(personService.getAllPerson()).thenReturn(personDtoList);

        //WHEN
        List<PersonDto> personDto = alertService.personByStationNumber(2).getPersons();
        //THEN
        assertEquals(1, personDto.size());
    }
    @Test
    public void childByAddressTest() throws RessourceNotFoundException {
        //GIVEN
        List<PersonDto> personDtoList  = List.of(
                new PersonDto("prenom1", "lastName1", "2eme rue", "", "", "", ""),
                new PersonDto("prenom2", "lastName2", "2eme rue", "", "", "", "")
        );

        List<MedicalRecordDto> medicalRecordDtoList = List.of(
                new MedicalRecordDto("prenom1", "lastName1", "02/02/2015", null, null),
                new MedicalRecordDto("prenom2", "lastName2", "02/02/1989", null, null)
        );
        when(personService.childByAddress("2eme rue")).thenReturn(personDtoList);
        when(personService.isMajor("02/02/2015")).thenReturn(false);
        when(personService.isMajor("02/02/1989")).thenReturn(true);
        when(medicalRecordService.getAllMedicalRecord()).thenReturn(medicalRecordDtoList);

        //WHEN

        JSONArray jsonArray = alertService.childByAddress("2eme rue");

        //THEN

        assertEquals(1, jsonArray.size());
    }

    @Test
    public void phoneByStationTest() throws RessourceNotFoundException {
        //GIVEN
        List<String> adresses = List.of("1er rue", "2eme");

        List<PersonDto> personDtoList  = List.of(
                new PersonDto("prenom1", "lastName1", "1er rue", "", "", "", ""),
                new PersonDto("prenom2", "lastName2", "2eme rue", "", "", "", "")
        );
        when(fireStationService.getAddressesByStations(List.of(1))).thenReturn(adresses);
        when(personService.getAllPerson()).thenReturn(personDtoList);

        //WHEN
        List<String> values = alertService.phoneByStation(1);

        //THEN
        assertEquals(1, values.size());
    }


    @Test
    public void personStationAndRecordByAddressTest() throws RessourceNotFoundException {
        //GIVEN
        List<PersonDto> personDtoList  = List.of(
                new PersonDto("prenom1", "lastName1", "2eme rue", "", "", "", ""),
                new PersonDto("prenom2", "lastName2", "2eme rue", "", "", "", "")
        );

        FireStationDto fireStationDto1 = new FireStationDto();
        fireStationDto1.setAddress("2eme rue");
        List<FireStationDto> fireStationDtoList = List.of(
                fireStationDto1
        );
        when(personService.getPersonsByAddress("2eme rue")).thenReturn(personDtoList);
        when(fireStationService.getAllFireStation()).thenReturn(fireStationDtoList);

        //WHEN
        JSONArray list = alertService.personStationAndRecordByAddress("2eme rue");

        //THEN

        assertEquals(2, list.size());

    }
    @Test
    public void foyerByStationTest() throws RessourceNotFoundException {
        //GIVEN
        List<String> adresses = List.of("1er rue", "2eme");

        List<PersonDto> personDtoList  = List.of(
                new PersonDto("prenom1", "lastName1", "1er rue", "", "", "", ""),
                new PersonDto("prenom2", "lastName2", "2eme rue", "", "", "", "")
        );

        MedicalRecordDto medicalRecordDto1 = new MedicalRecordDto("prenom1", "lastName1", "", null, null);
        when(fireStationService.getAddressesByStations(List.of(1))).thenReturn(adresses);
        when(personService.getAllPerson()).thenReturn(personDtoList);
        when(medicalRecordService.getMedicalRecordFromFirstAndLastName(any(), any())).thenReturn(medicalRecordDto1);

        //WHEN
        List<PersonWithMedicalRecordDto> list = alertService.foyerByStation(List.of(1));

        //THEN
        assertEquals(1, list.size());

    }
}