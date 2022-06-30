/*
package safetynet.org.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.*;
import safetynet.org.dao.GenericUrlProviderDAO;
import safetynet.org.model.FireStation;
import safetynet.org.model.Person;

import java.util.List;

@Slf4j
@RestController
public class RootController {

    // Can Help : https://spring.io/guides/tutorials/rest/

    // TODO: Declare attributes
    private final GenericUrlProviderDAO genericUrlProviderDAO = GenericUrlProviderDAO.init();

    //http://localhost:8080/firestation?stationNumber=<station_number>

    */
/** Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
     * Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1.
     * La liste doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone.
     * De plus, elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou moins) dans la zone desservie.
     * *//*

    // TODO:
    @RequestMapping(value = "/firestation/stationNumber", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getPersonCoveredByStation(@RequestParam(required = true) int stationNumber){
        try{
            if((stationNumber > 0) || (stationNumber < genericUrlProviderDAO.getFireStations().size())){
                return genericUrlProviderDAO.getPersonCoveredByStation(stationNumber-1); // Params mince one to start from bottom...
            }
            return new JSONArray();

        }catch (Exception e){
            log.info(">>> ERROR {}", e.getMessage());
            throw new NullPointerException();
        }
    }
}
*/
