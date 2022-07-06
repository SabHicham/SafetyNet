package safetynet.org.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import safetynet.org.dto.FireStationDto;
import safetynet.org.model.FireStation;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class FireStationRepository {

    private List<FireStation> fireStationList = new ArrayList<>();

    public Boolean addFireStation(FireStation fireStation){

        return fireStationList.add(fireStation);
    }

    public List<FireStationDto> getAllFireStation(){
        return fireStationList.stream().map(fireStation -> new FireStationDto(fireStation.getAddress(), fireStation.getStation())).collect(Collectors.toList());
    }

    public boolean updateFireStation(FireStation fireStation) {
        try {
            int i=0;
            for(FireStation f:fireStationList){
                String key = fireStation.getAddress();
                String key1 = f.getAddress();
                log.info(">>> ERROR: {} {}", key, key1);
                if (key.equalsIgnoreCase(key1)){
                    fireStationList.get(i).setAddress(fireStation.getAddress());
                    fireStationList.get(i).setStation(fireStation.getStation());

                    return true;
                }
                i++;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    public boolean deleteFireStationByAddressOrStation(String address, int station) {
        fireStationList = fireStationList.stream().filter((fireStation)-> !Objects.equals(fireStation.getAddress(), address)).collect(Collectors.toList());
        fireStationList = fireStationList.stream().filter((fireStation)-> !Objects.equals(fireStation.getStation(), station)).collect(Collectors.toList());
        return true;
    }
}
