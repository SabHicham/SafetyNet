package safetynet.org.repository;

import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Repository;
import safetynet.org.dto.PersonDto;
import safetynet.org.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {
    private List<Person> personList = new ArrayList<>();

    public Boolean addPerson(Person person){

        return personList.add(person);
    }
public List<PersonDto> getAllPerson(){
        return personList.stream().map(person -> new PersonDto(person.getFirstName())).collect(Collectors.toList());
}

    public boolean deletePersonByEmail(String email) {
        personList = personList.stream().filter((person)-> !Objects.equals(person.getEmail(), email)).collect(Collectors.toList());
        return true;
    }
}
