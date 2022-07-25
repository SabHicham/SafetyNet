package safetynet.org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import safetynet.org.dto.MedicalRecordDto;

import safetynet.org.model.MedicalRecord;

import safetynet.org.repository.MedicalRecordRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceTest {
    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    void getAllMedicalRecordTest() {


        MedicalRecordDto medicalRecordDto = new MedicalRecordDto(
                "John",
                "Boyd",
                "03/06/1984",
                List.of("x", "x"),
                List.of("x", "x")

                );

        List<MedicalRecordDto> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecordDto);

        when(medicalRecordRepository.getAllMedicalRecord()).thenReturn(medicalRecordList);

        // WHEN
        List<MedicalRecordDto> medicalRecordDtoList = medicalRecordService.getAllMedicalRecord();

        // THEN
        assertEquals(1,medicalRecordDtoList.size());
    }

    @Test
    void addMedicalRecordTest() {

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Bob");
        medicalRecord.setBirthdate("03/06/1984");
        medicalRecord.setMedications(List.of("x", "x"));
        medicalRecord.setAllergies(List.of("x", "x"));



        // WHEN
        medicalRecordService.addMedicalRecord(medicalRecord);
        // THEN
        verify(medicalRecordRepository, times(1)).addMedicalRecord(medicalRecord);
    }


    /*@Test
    void testGetPersonByCity(){

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
