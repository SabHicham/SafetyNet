package safetynet.org.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PersonWithMedicalRecordDto {

    private final String firstName;

    private final String lastName;

    private final String phone;

    private final int age;

    private final List<String> medications;

    private final List<String> allergies;

    public PersonWithMedicalRecordDto(String firstName, String lastName, String phone, int age, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }
}
