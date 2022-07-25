package safetynet.org.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PersonWithMedicalRecordDto {

    private final String firstName;

    private final String lastName;

    private final String phone;

    private final String birthdate;

    private final List<String> medications;

    private final List<String> allergies;

    public PersonWithMedicalRecordDto(String firstName, String lastName, String phone, String birthdate, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
    }
}
