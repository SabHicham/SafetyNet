package safetynet.org.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safetynet.org.dto.FireStationDto;
import safetynet.org.dto.PersonDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.Person;

import java.util.List;
import java.util.Optional;
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

    public List<String> communityEmail(String city) throws RessourceNotFoundException {
        //récupérer toutes les personnes par rapport à une ville
        List<PersonDto> listePersonByCity = personService.getPersonsByCity(city);
        List<String> listEmailOfPersonByCity = listePersonByCity.stream().map(PersonDto::getEmail).collect(Collectors.toList());
    return listEmailOfPersonByCity;
    }

    //http://localhost:8080/firestation?stationNumber=<station_number>
    //Cette url doit retourner une liste des personnes couvertes par la caserne
    // de pompiers correspondante. Donc, si le numéro de station = 1, elle doit renvoyer
    // les habitants couverts par la station numéro 1. La liste doit inclure
    // les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone.
    // De plus, elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants
    // (tout individu âgé de 18 ans ou moins) dans la zone desservie.
    public List<PersonDto> personByStationNumber(int stationNumber) throws RessourceNotFoundException {
        FireStationDto fireStationDto = fireStationService.getFireStationByNumber(stationNumber);
        List<PersonDto> persons = personService.getAllPerson().stream().filter(
                personDto -> fireStationDto.getAddress().equalsIgnoreCase(personDto.getAddress())
        ).collect(Collectors.toList());
        return persons;
    }
}
