package safetynet.org.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safetynet.org.model.FireStation;
import safetynet.org.repository.FireStationRepository;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;
    private static final String DATA_SOURCE_JSON = "src/main/resources/Data.json";

    @PostConstruct
    public void load() {
        try{
            /*// create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            // convert JSON array to list of persons, fireStations, medicalRecords
            persons = new ArrayList<>(Arrays.asList(mapper.readValue(Paths.get(PersonPath).toFile(), Person[].class)));
            fireStations = new ArrayList<>(Arrays.asList(mapper.readValue(Paths.get(FireStationsPath).toFile(), FireStation[].class)));
            medicalRecords = new ArrayList<>(Arrays.asList(mapper.readValue(Paths.get(MedicalRecordsPath).toFile(), MedicalRecord[].class)));
            // print Persons, FireStations, MedicalRecords...
            // Size of tables
            log.info("Size of Persons table >>>> {}", persons.size());
            log.info("Size of FireStations table >>>> {}", fireStations.size());
            log.info("Size of MedicalRecords table >>>> {}", medicalRecords.size());*/

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root;

            try {
                root = mapper.readTree(new FileInputStream(DATA_SOURCE_JSON));
                loadFireStationData(root);



            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }catch (Exception ex){
            log.error(">>>> ERROR:  {}",ex.getMessage());
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
}
