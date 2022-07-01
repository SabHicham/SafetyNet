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

    // TODO: Post HTTP Method
    @RequestMapping(value = "/person", method = RequestMethod.POST)
    @ResponseBody
    public String addPerson(@RequestBody Person person){
        try{
            // TODO: Add new Person...
            personRepository.addPerson(person);
            return "SUCCESS !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

   /* // TODO: Put HTTP Method
    @RequestMapping(value = "/person", method = RequestMethod.PUT)
    @ResponseBody
    public String updatePerson(@RequestBody Person person){
        // TODO: Update Existing Person...
        try{
            if(genericUrlProviderDAO.updatePerson(person)){
                return "UPDATED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }*/

    // TODO: Delete HTTP Method

    @RequestMapping(value = "/person/{email}", method = RequestMethod.DELETE)
    @ResponseBody
    public String removePerson(@PathVariable String email){
        try{
            // TODO: Delete new Person...
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
