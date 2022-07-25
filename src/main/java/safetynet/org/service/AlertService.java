package safetynet.org.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safetynet.org.dto.FireStationDto;
import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.dto.PersonDto;
import safetynet.org.dto.PersonWithMedicalRecordDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.MedicalRecord;

import java.util.List;
import java.util.Objects;
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
    //http://localhost:8080/childAlert?address=<address>
    //Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
    // La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres membres
    // du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.

    public JSONArray childByAddress(String address) throws RessourceNotFoundException {
        List<PersonDto> persons = personService.childByAddress(address);

        JSONArray jsonArray = new JSONArray();

        for (PersonDto personDto : persons) {
            MedicalRecordDto medicalRecordDto = medicalRecordService.getAllMedicalRecord().stream()
                    .filter(medicalRecord -> (personDto.getFirstName() + "." + personDto.getLastName()).equalsIgnoreCase(medicalRecord.getFirstName() + "." + medicalRecord.getLastName()))
                    .findAny().orElse(null);
            if (medicalRecordDto != null) {
                if (!personService.isMajor(medicalRecordDto.getBirthdate())) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("firstName", personDto.getFirstName());
                    jsonObject.put("lastName", personDto.getLastName());
                    jsonObject.put("address", personDto.getAddress());
                    jsonObject.put("phone", personDto.getPhone());
                    jsonArray.add(jsonObject);
                }

            }

        }
        return jsonArray;

    }
    // http://localhost:8080/phoneAlert?firestation=<firestation_number>
    // Cette url doit retourner une liste des numéros de téléphone des résidents desservis
    // par la caserne de pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à
    // des foyers spécifiques.

    public List<String> phoneByStation(int stationNumber) throws RessourceNotFoundException {
        List<String> stationAddress = fireStationService.getAddressesByStations(List.of(stationNumber));
        List<String> phones = personService.getAllPerson().stream().filter(
                        personDto -> stationAddress.contains(personDto.getAddress()))
                .map(PersonDto::getPhone).collect(Collectors.toList());
        return phones;


    }


    //http://localhost:8080/fire?address=<address>
    //Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi
    // que le numéro de la caserne de pompiers la desservant. La liste doit inclure le nom,
    // le numéro de téléphone, l'âge et les antécédents médicaux (médicaments, posologie et allergies)
    // de chaque personne.
    public JSONArray personStationAndRecordByAddress(String address) throws RessourceNotFoundException {
        List<PersonDto> personByAddress = personService.getPersonsByAddress(address);
        FireStationDto fireStationDto = fireStationService.getAllFireStation().stream()
                .filter(fireStation -> fireStation.getAddress().equalsIgnoreCase(address)).findAny().orElse(null);
        // condition ternaire : si fireStation est != null je retourne le numero de la station le cas écheant -1
        int stationNumber = fireStationDto != null ? fireStationDto.getStation() : -1;
        JSONArray jsonArray = new JSONArray();
        for (PersonDto personDto : personByAddress) {
            MedicalRecordDto medicalRecordDto = medicalRecordService.getAllMedicalRecord().stream()
                    .filter(medicalRecord -> (personDto.getFirstName() + "." + personDto.getLastName()).equalsIgnoreCase(medicalRecord.getFirstName() + "." + medicalRecord.getLastName()))
                    .findAny().orElse(null);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("stationNumber", stationNumber);
            jsonObject.put("firstName", personDto.getFirstName());
            jsonObject.put("phone", personDto.getPhone());
            //jsonObject.put("birthDate", medicalRecordDto.getBirthdate());
            //jsonObject.put("medications", medicalRecordDto.getMedications());
            //jsonObject.put("allergies", medicalRecordDto.getAllergies());
            jsonArray.add(jsonObject);

        }
        return jsonArray;

    }

    // http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    // Cette url doit retourner une liste de tous les foyers desservis par la caserne.
    // Cette liste doit regrouper les personnes par adresse. Elle doit aussi inclure le nom,
    // le numéro de téléphone et l'âge des habitants, et faire figurer leurs antécédents médicaux
    // (médicaments, posologie et allergies) à côté de chaque nom.
    public List<PersonWithMedicalRecordDto> foyerByStation(List<Integer> listStation) {
        List<String> stationAddress = fireStationService.getAddressesByStations(listStation);
        List<PersonWithMedicalRecordDto> personsWithMedicationRecord = personService.getAllPerson().stream()
                .filter(personDto -> stationAddress.contains(personDto.getAddress()))
                .map(personDto -> {
                    try {
                        MedicalRecordDto medicalRecordDto = medicalRecordService.getMedicalRecordFromFirstAndLastName(personDto.getFirstName(), personDto.getLastName());
                        return new PersonWithMedicalRecordDto(personDto.getFirstName(), personDto.getLastName(), personDto.getPhone(), medicalRecordDto.getBirthdate(), medicalRecordDto.getMedications(), medicalRecordDto.getAllergies());
                    } catch (RessourceNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toList());

        return personsWithMedicationRecord;
    }
}