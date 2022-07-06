
package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.model.MedicalRecord;
import safetynet.org.repository.MedicalRecordRepository;

import java.util.List;

@Slf4j
@RestController
public class MedicalRecordController {

    //Declare attributes
    @Autowired
    private final MedicalRecordRepository medicalRecordRepository;

    // Get HTTP Method
    @RequestMapping(value = "/medicalrecord", method = RequestMethod.GET)
    @ResponseBody
    public List<MedicalRecordDto> getMedicalRecord(){
        return medicalRecordRepository.getAllMedicalRecord();
    }

    // Post HTTP Method
    @RequestMapping(value = "/medicalrecord", method = RequestMethod.POST)
    @ResponseBody
    public String addMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        try{
            // Add new MedicalRecord...
            medicalRecordRepository.addMedicalRecord(medicalRecord);
            return "SUCCESS !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    // Put HTTP Method
    @RequestMapping(value = "/medicalrecord", method = RequestMethod.PUT)
    @ResponseBody
    public String updateMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        //Update Existing MedicalRecord...
        try{
            if(medicalRecordRepository.updateMedicalRecord(medicalRecord)){
                return "UPDATED WITH SUCCESS !";
            }
            return "FIRE STATION NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }

    //Delete HTTP Method
    @RequestMapping(value = "/medicalrecord", method = RequestMethod.DELETE)
    @ResponseBody
    public String removeMedicalRecord(@RequestBody MedicalRecord medicalRecord){
        try{
            //Delete new MedicalRecord...
            if (medicalRecordRepository.deleteMedicalRecord(medicalRecord.getFirstName())){
                return "REMOVED WITH SUCCESS !";
            }
            return "PERSON NOT FOUND !";
        }catch (Exception e){
            log.error(">>> ERROR: {}", e.getMessage());
            return "ERROR !";
        }
    }
}

