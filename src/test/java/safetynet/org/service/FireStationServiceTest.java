package safetynet.org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import safetynet.org.dto.FireStationDto;
import safetynet.org.model.FireStation;

import safetynet.org.repository.FireStationRepository;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FireStationServiceTest {
    @InjectMocks
    private FireStationService fireStationService;

    @Mock
    private FireStationRepository fireStationRepository;

    @Test
    void getAllFireStationTest() {

        FireStationDto fireStationDto = new FireStationDto(
                "1509 Culver St",
                 3
        );

        List<FireStationDto> fireStationList = new ArrayList<>();
        fireStationList.add(fireStationDto);

        when(fireStationRepository.getAllFireStation()).thenReturn(fireStationList);

        // WHEN
        List<FireStationDto> fireStationDtoList = fireStationService.getAllFireStation();
        // THEN
        assertEquals(1,fireStationDtoList.size());
    }

    @Test
    void addFireStationTest() {

        FireStation fireStation = new FireStation();

        fireStation.setAddress("1509 Culver St");
        fireStation.setStation(3);


        // WHEN
        fireStationService.addFirestation(fireStation);
        // THEN
        verify(fireStationRepository, times(1)).addFireStation(fireStation);
    }


    /*@Test
    void upDateFireStationTest(){

        PersonDto personDto = new PersonDto(
                "John",
                "Bob",
                "2 rue des sources",
                "Paris",
                "75000",
                "01020304",
                "john@gmail.com"
        );

        List<PersonDto> personList = new ArrayList<>();
        personList.add(personDto);

        String city = "Paris";
    }*/

    /*@Test
    void testGetPersonByCityWithNoResident() {

        PersonDto personDto = new PersonDto(
                "John",
                "Bob",
                "2 rue des sources",
                "Mexico",
                "75000",
                "01020304",
                "john@gmail.com"
        );

        List<PersonDto> personList = new ArrayList<>();
        personList.add(personDto);

        String paris = "Paris";

        when(personRepository.getAllPerson()).thenReturn(personList);

        List<PersonDto> personDtoList = personService.getAllPerson();

        assertThrows(RessourceNotFoundException.class, ()-> {
            personService.getPersonsByCity(paris);
        });


    }*/


}

