package safetynet.org.dto;


import lombok.Data;

@Data
public class FireStationDto {

    private String address;

    private int station;

    public FireStationDto(String address) {
        this.address = address;

    }

}
