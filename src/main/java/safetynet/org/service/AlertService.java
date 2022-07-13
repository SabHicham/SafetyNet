package safetynet.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safetynet.org.dto.PersonDto;
import safetynet.org.model.Person;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService {
    @Autowired
    private PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;
    @Autowired
    private FireStationService fireStationService;


    //http://localhost:8080/communityEmail?city=<city>
    //Cette url doit retourner les adresses mail de tous les habitants de la ville.

    public List<String> communityEmail(String city){
        //récupérer toutes les personnes par rapport à une ville
        List<PersonDto> listePersonByCity = personService.getPersonsByCity(city);
        List<String> listEmailOfPersonByCity = listePersonByCity.stream().map(PersonDto::getEmail).collect(Collectors.toList());
    return listEmailOfPersonByCity;
    }
}
