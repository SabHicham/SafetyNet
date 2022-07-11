
package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import safetynet.org.dto.FireStationDto;
import safetynet.org.model.FireStation;
import safetynet.org.repository.FireStationRepository;
import safetynet.org.service.FireStationService;


import java.util.List;
@Slf4j
@RestController
public class FireStationController {



    // Declare attributes
    @Autowired
    private FireStationService fireStationService;


    // Get HTTP Method
    @RequestMapping(value = "/firestation", method = RequestMethod.GET)
    @ResponseBody
    public List<FireStationDto> getFireStation(){
        return fireStationService.getAllFireStation();
    }


   // Post HTTP Method
    @RequestMapping(value = "/firestation", method = RequestMethod.POST)
    @ResponseBody
    public String addFireStation(@RequestBody FireStation fireStation){
        try{
            // Add new FireStation...
            fireStationService.addFirestation(fireStation);
            return "SUCCESS !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // Put HTTP Method
    @RequestMapping(value = "/firestation", method = RequestMethod.PUT)
    @ResponseBody
    public String updateFireStation(@RequestBody FireStation fireStation){
        // Update Existing FireStation...
        try{
            if(fireStationService.updateFireStation(fireStation)){
                return "UPDATED WITH SUCCESS !";
            }
            return "FIRE STATION NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // Delete HTTP Method
    @RequestMapping(value = "/firestation/station", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeFireStation(@PathVariable String address, int station){
        try{
            // Delete new FireStation...
            if (fireStationService.deleteFireStationByAddressOrStation(address, station)){
                return "REMOVED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

}

