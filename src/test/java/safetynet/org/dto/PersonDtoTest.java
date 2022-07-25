package safetynet.org.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDtoTest {
    @Test
    public void setterTest(){
        PersonDto personDto = new PersonDto("","", "", "", "","", "");
        personDto.setFirstName("A");
        personDto.setLastName("B");
        personDto.setAddress("c");
        personDto.setCity("D");
        personDto.setZip("E");
        personDto.setPhone("s");
        personDto.setEmail("x");

        assertEquals("A", personDto.getFirstName());
        assertEquals("B", personDto.getLastName());
        assertEquals("c", personDto.getAddress());
        assertEquals("D", personDto.getCity());
        assertEquals("E", personDto.getZip());
        assertEquals("s", personDto.getPhone());
        assertEquals("x", personDto.getEmail());
    }
}