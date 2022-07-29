package safetynet.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonWithAgeCountDto {
    private int adultCount;
    private int childCount;
    private List<PersonDto> persons;

    public PersonWithAgeCountDto(List<PersonDto> persons, int adultCount, int childCount) {
        this.persons = persons;
        this.adultCount = adultCount;
        this.childCount = childCount;
    }

}
