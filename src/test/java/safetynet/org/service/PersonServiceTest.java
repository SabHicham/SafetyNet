package safetynet.org.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.dto.PersonDto;
import safetynet.org.dto.PersonWithMedicalRecordDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.Person;
import safetynet.org.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalRecordService medicalRecordService;

    @Test
    void testGetAllPerson() {

        PersonDto personDto = new PersonDto(
                "John",
                "Bob",
                "2 rue des sources",
                "New-york",
                "75000",
                "01020304",
                "john@gmail.com"
        );

        List<PersonDto> personList = new ArrayList<>();
        personList.add(personDto);

        when(personRepository.getAllPerson()).thenReturn(personList);

        // WHEN
        List<PersonDto> personDtoList = personService.getAllPerson();
        // THEN
        assertEquals(1,personDtoList.size());
    }

    @Test
    void testAddPerson() {

        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Bob");
        person.setAddress("address");
        person.setCity(("city"));
        person.setZip("zip");
        person.setPhone("a");
        person.setEmail("email");

        // WHEN
        personService.addPerson(person);
        // THEN
        verify(personRepository, times(1)).addPerson(person);
    }


    @Test
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
    }

    @Test
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


    }
    @Test
    void deletePersonTest(){
        // WHEN
        personService.deletePerson("John","Bob");
        // THEN
        verify(personRepository, times(1)).deletePerson("John","Bob");
    }

    @Test
    void updatePersonTest(){
        Person person = new Person();
        personService.updatePerson(person);

        // THEN
        verify(personRepository, times(1)).updatePerson(person);

    }

    @Test
    void elseReturnTest() throws RessourceNotFoundException {
        //GIVEN

        PersonDto personDto = new PersonDto("abcd", "efgh",
                "1 rue de la paix ", "Paris", "75000", "01020304",
                "hicham@email.com");

        when(personRepository.getAllPerson()).thenReturn(List.of(personDto));

        //WHEN

        List<PersonDto> list = personService.getPersonsByCity("Paris");


        //THEN

        assertEquals(1, list.size());
    }


    @Test
    void childByAddressTest() throws RessourceNotFoundException {
        //GIVEN

        PersonDto personDto = new PersonDto("abcd", "efgh",
                "1 rue de la paix", "Paris", "75000", "01020304",
                "hicham@email.com");
        PersonDto personDto2 = new PersonDto("abcd", "efgh",
                "2 rue de la paix", "Paris", "75000", "01020304",
                "hicham@email.com");

        when(personRepository.getAllPerson()).thenReturn(List.of(personDto, personDto2));

        assertEquals(1, personService.childByAddress("1 rue de la paix").size());
    }

    @Test
    void childByAddressErrorTest() throws RessourceNotFoundException {
        //GIVEN

        PersonDto personDto = new PersonDto("abcd", "efgh",
                "1 rue de la paix", "Paris", "75000", "01020304",
                "hicham@email.com");
        PersonDto personDto2 = new PersonDto("abcd", "efgh",
                "2 rue de la paix", "Paris", "75000", "01020304",
                "hicham@email.com");

        when(personRepository.getAllPerson()).thenReturn(List.of(personDto, personDto2));

        assertThatThrownBy(() -> personService.childByAddress("1 rue")).isInstanceOf(RessourceNotFoundException.class);
    }
    @Test
    void isMajorTrueTest() {
        assertTrue(personService.isMajor("09/06/2000"));
    }
    @Test
    void isMajorFalseTest() {
        assertFalse(personService.isMajor("09/06/2022"));
    }


    @Test
    void getPersonsByAddressTest() throws RessourceNotFoundException {
        //GIVEN

        PersonDto personDto = new PersonDto("abcd", "efgh",
                "1 rue de la paix", "Paris", "75000", "01020304",
                "hicham@email.com");
        PersonDto personDto2 = new PersonDto("abcd", "efgh",
                "2 rue de la paix", "Paris", "75000", "01020304",
                "hicham@email.com");

        when(personRepository.getAllPerson()).thenReturn(List.of(personDto, personDto2));

        assertEquals(1, personService.getPersonsByAddress("1 rue de la paix").size());
    }

    @Test
    void getPersonsByAddressErrorTest() throws RessourceNotFoundException {
        //GIVEN

        PersonDto personDto = new PersonDto("abcd", "efgh",
                "3 rue de la paix", "Paris", "75000", "01020304",
                "hicham@email.com");
        PersonDto personDto2 = new PersonDto("abcd", "efgh",
                "2 rue de la paix", "Paris", "75000", "01020304",
                "hicham@email.com");

        when(personRepository.getAllPerson()).thenReturn(List.of(personDto, personDto2));


        assertThatThrownBy(() -> personService.getPersonsByAddress("1 rue")).isInstanceOf(RessourceNotFoundException.class);
    }

    @Test
    public void foyerByStationTest() throws RessourceNotFoundException {
        //GIVEN

        List<PersonDto> personDtoList  = List.of(
                new PersonDto("prenom1", "lastName1", "1er rue", "", "", "", ""),
                new PersonDto("prenom2", "lastName2", "2eme rue", "", "", "", "")
        );

        MedicalRecordDto medicalRecordDto1 = new MedicalRecordDto("prenom1", "lastName1", "", null, null);
        when(personService.getAllPerson()).thenReturn(personDtoList);
        when(medicalRecordService.getMedicalRecordFromFirstAndLastName(any(), any())).thenReturn(medicalRecordDto1);

        //WHEN
        List<PersonWithMedicalRecordDto> list = personService.getPersonInfo("prenom1", "lastName1");

        //THEN
        assertEquals(1, list.size());

    }

    @Test
    public void foyerByStationErrorTest() throws RessourceNotFoundException {
        //GIVEN

        List<PersonDto> personDtoList  = List.of(
                new PersonDto("prenom1", "lastName1", "1er rue", "", "", "", ""),
                new PersonDto("prenom2", "lastName2", "2eme rue", "", "", "", "")
        );

        MedicalRecordDto medicalRecordDto1 = new MedicalRecordDto("prenom1", "lastName1", "", null, null);
        when(personService.getAllPerson()).thenReturn(personDtoList);


        assertEquals(0, personService.getPersonInfo("prenom3", "lastName1").size());


    }

}