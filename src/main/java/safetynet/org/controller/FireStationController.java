
package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dto.FireStationDto;
import safetynet.org.model.FireStation;
import safetynet.org.repository.FireStationRepository;
import safetynet.org.repository.PersonRepository;

import java.util.List;
@Slf4j
@RestController
public class FireStationController {



    // Declare attributes
    @Autowired
    private FireStationRepository fireStationRepository;


    // Get HTTP Method
    @RequestMapping(value = "/firestation", method = RequestMethod.GET)
    @ResponseBody
    public List<FireStationDto> getFireStation(){
        return fireStationRepository.getAllFireStation();
    }


   /* // TODO: Post HTTP Method
    @RequestMapping(value = "/firestation", method = RequestMethod.POST)
    @ResponseBody
    public String addFireStation(@RequestBody FireStation fireStation){
        try{
            // TODO: Add new FireStation...
            genericUrlProviderDAO.addFireStation(fireStation);
            return "SUCCESS !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // TODO: Put HTTP Method
    @RequestMapping(value = "/firestation", method = RequestMethod.PUT)
    @ResponseBody
    public String updateFireStation(@RequestBody FireStation fireStation){
        // TODO: Update Existing FireStation...
        try{
            if(genericUrlProviderDAO.updateFireStation(fireStation)){
                return "UPDATED WITH SUCCESS !";
            }
            return "FIRE STATION NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // TODO: Delete HTTP Method
    @RequestMapping(value = "/firestation", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeFireStation(@RequestBody FireStation fireStation){
        try{
            // TODO: Delete new FireStation...
            if (genericUrlProviderDAO.deleteFireStation(fireStation)){
                return "REMOVED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }*/

}

