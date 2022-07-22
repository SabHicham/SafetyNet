package safetynet.org.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safetynet.org.dto.FireStationDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.FireStation;
import safetynet.org.repository.FireStationRepository;


import java.util.List;
import java.util.Optional;

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

    public FireStationDto getFireStationByNumber(int numberStation) throws RessourceNotFoundException {
        Optional<FireStationDto> fsd = fireStationRepository.getAllFireStation().stream().filter(fireStationDto
                -> fireStationDto.getStation() == numberStation).findFirst();
        if (fsd.isPresent()) {
            FireStationDto fireStation = fsd.get();
            return fireStation;
        }

        String error = String.format("Aucune station n'a été trouvée dont le numéro %s",numberStation);
        throw new RessourceNotFoundException(error);

    }
}