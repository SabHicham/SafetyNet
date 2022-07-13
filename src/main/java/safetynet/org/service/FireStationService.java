package safetynet.org.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safetynet.org.dto.FireStationDto;
import safetynet.org.model.FireStation;
import safetynet.org.repository.FireStationRepository;


import java.util.List;

@Slf4j
@Service
public class FireStationService {

    @Autowired
    private FireStationRepository fireStationRepository;

    public boolean addFirestation(FireStation fireStation) {
        return this.fireStationRepository.addFireStation(fireStation);
    }

    public List<FireStationDto> getAllFireStation() {
        return fireStationRepository.getAllFireStation();
    }

    public boolean updateFireStation(FireStation fireStation) {
        return fireStationRepository.updateFireStation(fireStation);
    }

    public boolean deleteFireStationByAddressOrStation(String address, int station) {
        return fireStationRepository.deleteFireStationByAddressOrStation(address, station);
    }
}
