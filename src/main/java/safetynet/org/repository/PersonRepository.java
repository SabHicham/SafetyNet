package safetynet.org.repository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import safetynet.org.dto.PersonDto;
import safetynet.org.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class PersonRepository {
    private List<Person> personList = new ArrayList<>();

    public Boolean addPerson(Person person){

        return personList.add(person);
    }
public List<PersonDto> getAllPerson(){
        return personList.stream().map(person -> new PersonDto(person.getFirstName(), person.getLastName(), person.getAddress(),
                person.getCity(), person.getZip(), person.getPhone(), person.getEmail())).collect(Collectors.toList());
}

    public boolean deletePerson(String firstName, String lastName) {
        personList = personList.stream().filter((person)-> !Objects.equals(person.getFirstName(),firstName)&& !Objects.equals(person.getLastName(),lastName)).collect(Collectors.toList());
        return true;
    }

    public boolean updatePerson(Person person) {
        try {
            int i=0;
            for(Person p:personList){
                String key = person.getLastName()+"."+person.getFirstName();
                String key1 = p.getLastName()+"."+p.getFirstName();
                log.info(">>> ERROR: {} {}", key, key1);
                if (key.equalsIgnoreCase(key1)){
                    personList.get(i).setAddress(person.getAddress());
                    personList.get(i).setCity(person.getCity());
                    personList.get(i).setEmail(person.getEmail());
                    personList.get(i).setPhone(person.getPhone());
                    personList.get(i).setZip(person.getZip());
                    return true;
                }
                i++;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

}
