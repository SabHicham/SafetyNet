package safetynet.org.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import safetynet.org.dto.PersonDto;
import safetynet.org.model.Person;




import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class PersonRepositoryTest {

    private PersonRepository personRepository;
    @BeforeEach
    private void setUp() {
        Person person = new Person();
        person.setFirstName("hicham");
        person.setLastName("john");

        personRepository = new PersonRepository();
        personRepository.addPerson(person);

    }
    @Test
    public void updatePersonTest() {

        // GIVEN

             Person person = new Person();
             person.setFirstName("hicham");
             person.setLastName("john");
                // WHEN
                boolean value = personRepository.updatePerson(person);

                // THEN
                assertTrue(value);
        }

    @Test
    public void updatePersonTestReturnFalse() {

        // GIVEN

        Person person = new Person();
        person.setFirstName("richard");
        person.setLastName("john");
        // WHEN
        boolean value = personRepository.updatePerson(person);

        // THEN
        assertFalse(value);
    }
        @Test
        public void getAllPersonTest () {
            // GIVEN



            // WHEN

            List<PersonDto> list = personRepository.getAllPerson();

            // THEN
            assertEquals(1, list.size());

        }





    @Test
    public void deletePersonTest() {
        // GIVEN




                // WHEN
                boolean value = personRepository.deletePerson("hicham", "john");

                // THEN
        assertTrue(value);

        }

    @Test
    public void deletePersonTest2() {
        // GIVEN




        // WHEN
        boolean value = personRepository.deletePerson("hi", "john");

        // THEN
        assertTrue(value);

    }


}