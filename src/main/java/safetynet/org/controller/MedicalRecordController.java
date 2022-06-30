/*
package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dao.GenericUrlProviderDAO;
import safetynet.org.model.FireStation;
import safetynet.org.model.MedicalRecord;

import java.util.List;

@Slf4j
@RestController
public class MedicalRecordController {

    // TODO: Declare attributes
    private final GenericUrlProviderDAO genericUrlProviderDAO = GenericUrlProviderDAO.init();

    // TODO: Get HTTP Method
    @RequestMapping(value = "/medicalrecord", method = RequestMethod.GET)
    @ResponseBody
    public List<MedicalRecord> getMedicalRecords(){
        try {
            return GenericUrlProviderDAO.init().getMedicalRecords();
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            throw new NullPointerException();
        }
    }

    // TODO: Post HTTP Method
    @RequestMapping(value = "/medicalrecord", method = RequestMethod.POST)
    @ResponseBody
    public String addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        try{
            // TODO: Add new MedicalRecord...
            genericUrlProviderDAO.addMedicalRecord(medicalRecord);
            return "SUCCESS !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // TODO: Put HTTP Method
    @RequestMapping(value = "/medicalrecord", method = RequestMethod.PUT)
    @ResponseBody
    public String updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        // TODO: Update Existing MedicalRecord...
        try{
            if(genericUrlProviderDAO.updateMedicalRecord(medicalRecord)){
                return "UPDATED WITH SUCCESS !";
            }
            return "FIRE STATION NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // TODO: Delete HTTP Method
    @RequestMapping(value = "/medicalrecord", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        try{
            // TODO: Delete new MedicalRecord...
            if (genericUrlProviderDAO.deleteMedicalRecord(medicalRecord)){
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
