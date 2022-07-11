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

    public boolean addFirestation(FireStation fireStation) {
        return this.fireStationRepository.addFireStation(fireStation);
    }

}
