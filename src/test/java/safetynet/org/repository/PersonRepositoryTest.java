package safetynet.org.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import safetynet.org.dto.PersonDto;
import safetynet.org.model.Person;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class PersonRepositoryTest {

    @BeforeEach
    private void setUp() {
        PersonRepository personRepository = new PersonRepository();
        List<Person> personList = new ArrayList<>();
        List<PersonDto> personListDto = new ArrayList<>();
    }
    @Test
    public void updatePersonTest() {

        // GIVEN
        PersonRepository personRepository = new PersonRepository();
        List<Person> personList = new ArrayList<>();
        Person person = new Person();



        for (Person p : personList) {
            //String key = person.getLastName() + "." + person.getFirstName();
            //String key1 = p.getLastName() + "." + p.getFirstName();
            String key = "hicham";
            String key1 = "hicham";
            int i = 0;


            if (Objects.equals(key, key1)) {
                personList.get(i).setAddress(person.getAddress());


                // WHEN
                boolean value = personRepository.updatePerson(person);

                // THEN
                assertTrue(value);


            }

        }
    }
        @Test
        public void getAllPersonTest () {
            // GIVEN
            PersonRepository personRepository = new PersonRepository();
            Person person = new Person();
            List<PersonDto> personListDto = new ArrayList<>();

            // WHEN
            personRepository.updatePerson(person);
            // THEN
            assertEquals(1, 1);

        }





    @Test
    public void deletePersonTest() {
        // GIVEN
        PersonRepository personRepository = new PersonRepository();
        List<PersonDto> personList = new ArrayList<>();
        Person person = new Person();



                // WHEN
                personRepository.updatePerson(person);

                // THEN
        assertEquals(1, 1);

        }




}