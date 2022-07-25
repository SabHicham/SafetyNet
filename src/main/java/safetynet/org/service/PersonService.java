package safetynet.org.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.dto.PersonDto;
import safetynet.org.dto.PersonWithMedicalRecordDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.Person;
import safetynet.org.repository.PersonRepository;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MedicalRecordService medicalRecordService;


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
    public List<PersonDto> getPersonsByAddress(String address) throws RessourceNotFoundException {
        List<PersonDto> persons =  personRepository.getAllPerson().stream()
                .filter(p -> p.getAddress().equalsIgnoreCase(address)).collect(Collectors.toList());

        if(persons.isEmpty()){
            String error = String.format("Aucun résident trouvé pour l'address %s",address);
            throw new RessourceNotFoundException(error);
        } else return persons;

    }
    public boolean isMajor(String dateString){
        // format 02/18/2012
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDayDate = LocalDate.parse(dateString, formatter);
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthDayDate, now);
        log.info(">>> AGE  =>{}", age.getYears());
        if(age.getYears() >= 18){
            return true;
        }
        return false;
    }
    public List<PersonDto> childByAddress(String address) throws RessourceNotFoundException {
        // récuperer toutes les personnes appartenant a l'address passer en parametres
        List<PersonDto> persons = personRepository.getAllPerson().stream()
                .filter(personDto -> personDto.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toList());
        if(persons.isEmpty()){
            String error = String.format("Aucune personne trouvée pour l'address %s",address);
            throw new RessourceNotFoundException(error);
        } else return persons;
    }
    //http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    //Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents
    // médicaux (médicaments, posologie, allergies) de chaque habitant. Si plusieurs personnes
    // portent le même nom, elles doivent toutes apparaître.
    public List<PersonWithMedicalRecordDto> getPersonInfo(String firstname, String lastname) {
        List<PersonWithMedicalRecordDto> personsWithMedicationRecord = getAllPerson().stream()
                .filter(personDto -> Objects.equals(firstname, personDto.getFirstName()) && Objects.equals(lastname, personDto.getLastName()))
                .map(personDto -> {
                    try {
                        MedicalRecordDto medicalRecordDto = medicalRecordService.getMedicalRecordFromFirstAndLastName(personDto.getFirstName(), personDto.getLastName());
                        return new PersonWithMedicalRecordDto(personDto.getFirstName(), personDto.getLastName(), personDto.getPhone(), medicalRecordDto.getBirthdate(), medicalRecordDto.getMedications(), medicalRecordDto.getAllergies());
                    } catch (RessourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toList());

        return personsWithMedicationRecord;
    }



}



