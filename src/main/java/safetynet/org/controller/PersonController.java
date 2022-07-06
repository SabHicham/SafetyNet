package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dto.PersonDto;
import safetynet.org.model.Person;
import safetynet.org.repository.PersonRepository;

import java.util.List;
@Slf4j
@RestController
public class PersonController {

    // Can Help : https://spring.io/guides/tutorials/rest/

    // Declare attributes
    @Autowired
   private PersonRepository personRepository;


    // Get HTTP Method

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonDto> getPerson(){
        return personRepository.getAllPerson();
    }

    // "Post HTTP Method
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    @ResponseBody
    public String addPerson(@RequestBody Person person){
        try{
            // Add new Person...
            personRepository.addPerson(person);
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
            if(personRepository.updatePerson(person)){
                return "UPDATED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // Delete HTTP Method
    @RequestMapping(value = "/person/{email}", method = RequestMethod.DELETE)
    @ResponseBody
    public String removePerson(@PathVariable String email){
        try{
            // Delete new Person...
            if (personRepository.deletePersonByEmail(email)){
                return "REMOVED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }
}
