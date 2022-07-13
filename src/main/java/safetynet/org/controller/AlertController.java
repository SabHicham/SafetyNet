package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  public List<String> communityEmail(@RequestParam(required = true) String city){
    List<String> emails = new ArrayList<>();
    try {
      if (city != null){
        return alertService.communityEmail(city);
      }
    }
    catch (Exception e){
      log.error("error {}",e.getMessage());
    }
    return emails;
  }

}
