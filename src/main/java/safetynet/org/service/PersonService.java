package safetynet.org.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safetynet.org.dto.PersonDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.Person;
import safetynet.org.repository.PersonRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public List<PersonDto> getAllPerson() {
        return personRepository.getAllPerson();
    }

    public void addPerson(Person person) {
        personRepository.addPerson(person);
    }

    public boolean updatePerson(Person person) {
        return personRepository.updatePerson(person);
    }


    public boolean deletePerson(String firstName, String lastName) {
        return personRepository.deletePerson(firstName, lastName);
    }


    public List<PersonDto> getPersonsByCity(String city) throws RessourceNotFoundException {
        List<PersonDto> persons =  personRepository.getAllPerson().stream()
                    .filter(p -> p.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());

        if(persons.isEmpty()){
            String error = String.format("Aucun résident trouvé pour la  ville %s",city);
            throw new RessourceNotFoundException(error);
        } else return persons;

    }

}



