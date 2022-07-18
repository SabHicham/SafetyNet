package safetynet.org.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import safetynet.org.dto.PersonDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.Person;
import safetynet.org.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

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


}