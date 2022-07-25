
package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.model.MedicalRecord;
import safetynet.org.service.MedicalRecordService;

import java.util.List;

@Slf4j
@RestController
public class MedicalRecordController {

    //Declare attributes
    @Autowired
    private MedicalRecordService medicalRecordService;

    // Get HTTP Method
    @RequestMapping(value = "/medicalRecord", method = RequestMethod.GET)
    @ResponseBody
    public List<MedicalRecordDto> getMedicalRecord(){
        return medicalRecordService.getAllMedicalRecord();

    }

    // Post HTTP Method
    @RequestMapping(value = "/medicalRecord", method = RequestMethod.POST)
    @ResponseBody
    public String addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        try{
            // Add new MedicalRecord...
            medicalRecordService.addMedicalRecord(medicalRecord);
            return "SUCCESS !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // Put HTTP Method
    @RequestMapping(value = "/medicalRecord", method = RequestMethod.PUT)
    @ResponseBody
    public String updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        //Update Existing MedicalRecord...
        try{
            if(medicalRecordService.updateMedicalRecord(medicalRecord)){
                return "UPDATED WITH SUCCESS !";
            }
            return "FIRE STATION NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    //Delete HTTP Method
    @RequestMapping(value = "/medicalRecord", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        try{
            //Delete new MedicalRecord...
            if (medicalRecordService.deleteMedicalRecord(medicalRecord.getFirstName(), medicalRecord.getLastName())){
                return "REMOVED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }
}

