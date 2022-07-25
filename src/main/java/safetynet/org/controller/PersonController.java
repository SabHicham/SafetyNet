package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dto.PersonDto;
import safetynet.org.dto.PersonWithMedicalRecordDto;
import safetynet.org.model.Person;
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
    @RequestMapping(value = "/person", method = RequestMethod.DELETE)
    @ResponseBody
    public String removePerson(@RequestBody Person person){
        try{
            //Delete new Person...
            if (personService.deletePerson(person.getFirstName(), person.getLastName())){
                return "REMOVED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }// Get HTTP Method

    @RequestMapping(value = "/personInfo", method = RequestMethod.GET)
    @ResponseBody
    public List<PersonWithMedicalRecordDto> getPersonInfo(@RequestParam(required = true) String firstName, @RequestParam(required = true) String lastName){
        return personService.getPersonInfo(firstName, lastName);
    }
}
