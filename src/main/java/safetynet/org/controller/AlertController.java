package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.service.AlertService;

import java.util.ArrayList;
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
}

