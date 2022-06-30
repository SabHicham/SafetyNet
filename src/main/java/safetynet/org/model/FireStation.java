package safetynet.org.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class FireStation {

    @JsonProperty("address")
    private String address;
    @JsonProperty("station")
    private int station;

}
