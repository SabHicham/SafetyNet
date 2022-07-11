package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dto.PersonDto;
import safetynet.org.model.Person;
import safetynet.org.repository.PersonRepository;
import safetynet.org.service.PersonService;

import java.util.List;
@Slf4j
@RestController
public class PersonController {

    // Can Help : https://spring.io/guides/tutorials/rest/

    // Declare attributes
    @Autowired
   private PersonService personService;


    // Get HTTP Method

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonDto> getPerson(){
        return personService.getAllPerson();
    }

    // "Post HTTP Method
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    @ResponseBody
    public String addPerson(@RequestBody Person person){
        try{
            // Add new Person...
            personService.addPerson(person);
            return "SUCCESS !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

   // Put HTTP Method
    @RequestMapping(value = "/person", method = RequestMethod.PUT)
    @ResponseBody
    public String updatePerson(@RequestBody Person person){
        // Update Existing Person...
        try{
            if(personService.updatePerson(person)){
                return "UPDATED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // Delete HTTP Method
    // exemple: /person/firstName+lastName
    @RequestMapping(value = "/person/{nameInformation}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> removePerson(@PathVariable String nameInformation){
        String[] tab = nameInformation.split("\\+");
        String firstName = tab[0];
        String lastName = tab[1];
            // Delete new Person...
            if (personService.deletePerson(firstName, lastName)){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("REMOVED WITH SUCCESS !");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PERSON NOT FOUND !");

    }
}
