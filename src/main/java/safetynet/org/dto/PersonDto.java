package safetynet.org.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDto {

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String zip;

    private String phone;

    private String email;

    public PersonDto(String lastName){
        this.lastName=lastName;
    }
}


