package safetynet.org.repository;

import org.springframework.stereotype.Repository;
import safetynet.org.dto.FireStationDto;
import safetynet.org.model.FireStation;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FireStationRepository {

    private List<FireStation> fireStationList = new ArrayList<>();

    public Boolean addFireStation(FireStation fireStation){

        return fireStationList.add(fireStation);
    }

    public List<FireStationDto> getAllFireStation(){
        return fireStationList.stream().map(fireStation -> new FireStationDto(fireStation.getAddress())).collect(Collectors.toList());
    }
}
