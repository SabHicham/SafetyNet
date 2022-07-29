package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import safetynet.org.dto.PersonDto;
import safetynet.org.dto.PersonWithAgeCountDto;
import safetynet.org.dto.PersonWithMedicalRecordDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.service.AlertService;

import java.util.List;

@Slf4j
@RestController
public class AlertController {

@Autowired
    private AlertService alertService;

    @RequestMapping(value = "/communityEmail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<String>> communityEmail(@RequestParam(required = true) String city) throws RessourceNotFoundException {

        return ResponseEntity.status(HttpStatus.OK).body(alertService.communityEmail(city));


    }
    @RequestMapping(value = "/firestation/stationNumber", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PersonWithAgeCountDto> personByStationNumber(@RequestParam(required = true) int stationNumber) throws RessourceNotFoundException {


        return ResponseEntity.status(HttpStatus.OK).body(alertService.personByStationNumber(stationNumber));


    }
    @RequestMapping(value = "/childAlert", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<JSONArray> childByAddress(@RequestParam(required = true) String address) throws RessourceNotFoundException {


        return ResponseEntity.status(HttpStatus.OK).body(alertService.childByAddress(address));


    }
    @RequestMapping(value = "/phoneAlert", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<String>> phoneByStation(@RequestParam(required = true) int firestation) throws RessourceNotFoundException {


        return ResponseEntity.status(HttpStatus.OK).body(alertService.phoneByStation(firestation));


    }

    @RequestMapping(value = "/fire", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<JSONArray> personAndStationByAddress(@RequestParam(required = true) String address) throws RessourceNotFoundException {


        return ResponseEntity.status(HttpStatus.OK).body(alertService.personStationAndRecordByAddress(address));


    }
   @RequestMapping(value = "/flood/stations", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<PersonWithMedicalRecordDto>> addressByStation(@RequestParam(required = true) List<Integer> stations) throws RessourceNotFoundException {


        return ResponseEntity.status(HttpStatus.OK).body(alertService.foyerByStation(stations));


    }

}

