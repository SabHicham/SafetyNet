package safetynet.org.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safetynet.org.model.FireStation;
import safetynet.org.model.MedicalRecord;
import safetynet.org.model.Person;
import safetynet.org.repository.FireStationRepository;
import safetynet.org.repository.MedicalRecordRepository;
import safetynet.org.repository.PersonRepository;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class JsonLoaderService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FireStationRepository fireStationRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    private static final String DATA_SOURCE_JSON = "src/main/resources/Data.json";
    @PostConstruct
    public void load(){



        try{

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root;

            try {
                root = mapper.readTree(new FileInputStream(DATA_SOURCE_JSON));
                loadPersonData(root);
                loadFireStationData(root);
                loadMedicalRecordData(root);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }catch (Exception ex){
            log.error(">>>> ERROR:  {}",ex.getMessage());
        }
    }
    public void loadPersonData(JsonNode root)
    {
        JsonNode personsList = root.path("persons");
        for (JsonNode nodePerson : personsList)
        {
            Person person = new Person();
            person.setFirstName(nodePerson.path("firstName").asText());
            person.setLastName(nodePerson.path("lastName").asText());
            person.setAddress(nodePerson.path("address").asText());
            person.setCity(nodePerson.path("city").asText());
            person.setZip(nodePerson.path("zip").asText());
            person.setPhone(nodePerson.path("phone").asText());
            person.setEmail(nodePerson.path("email").asText());
            personRepository.addPerson(person);
        }
    }

    public void loadFireStationData(JsonNode root)
    {
        JsonNode fireStationList = root.path("firestations");
        for (JsonNode nodeFireStation : fireStationList)
        {
            FireStation fireStation = new FireStation();

            fireStation.setAddress(nodeFireStation.path("address").asText());
            fireStation.setStation(nodeFireStation.path("station").asInt());

            fireStationRepository.addFireStation(fireStation);
        }
    }

    public void loadMedicalRecordData(JsonNode root)
    {
        JsonNode medicalRecordList = root.path("medicalrecords");
        for (JsonNode nodeMedicalRecord : medicalRecordList)
        {
            MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setFirstName(nodeMedicalRecord.path("firstName").asText());
            medicalRecord.setLastName(nodeMedicalRecord.path("lastName").asText());
            medicalRecord.setBirthdate(nodeMedicalRecord.path("birthdate").asText());
            medicalRecord.setMedications(nodeMedicalRecord.findValuesAsText("medications"));
            medicalRecord.setAllergies(nodeMedicalRecord.findValuesAsText("allergies"));

            medicalRecordRepository.addMedicalRecord(medicalRecord);
        }
    }

}
