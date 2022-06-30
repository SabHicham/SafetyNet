
/*package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dao.GenericUrlProviderDAO;
import safetynet.org.model.FireStation;

import java.util.List;
@Slf4j
@RestController
public class FireStationController {

    // Can Help : https://spring.io/guides/tutorials/rest/

    // TODO: Declare attributes
    private final GenericUrlProviderDAO genericUrlProviderDAO = GenericUrlProviderDAO.init();

    // TODO: Get HTTP Method (To be deleted at the end, only to understand how it works 😊 !)
    @RequestMapping(value = "/firestation", method = RequestMethod.GET)
    @ResponseBody
    public List<FireStation> getFireStation(){
        return genericUrlProviderDAO.getFireStations();
    }

    // TODO: Post HTTP Method
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
    }

}
*/
