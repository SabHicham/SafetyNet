package safetynet.org.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import safetynet.org.dto.FireStationDto;
import safetynet.org.dto.PersonDto;
import safetynet.org.model.FireStation;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class FireStationRepositoryTest {

    private FireStationRepository fireStationRepository;



    @BeforeEach
    private void setUp(){
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1 rue de la paix");
        fireStation.setStation(1);

        fireStationRepository = new FireStationRepository();
        fireStationRepository.addFireStation(fireStation);
    }


    @Test
    public void updateFireStationTest() {
        // GIVEN
        FireStation fireStation = new FireStation();
        fireStation.setAddress("1 rue de la paix");
        fireStation.setStation(1);



        // WHEN
        boolean value = fireStationRepository.updateFireStation(fireStation);
        // THEN
        assertTrue(value);
    }

    @Test
    public void updateFireStationTestReturnFalse() {
        // GIVEN
        FireStation fireStation = new FireStation();
        fireStation.setAddress("3 rue de la paix");
        fireStation.setStation(3);



        // WHEN
        boolean value = fireStationRepository.updateFireStation(fireStation);
        // THEN
        assertFalse(value);
    }

    @Test
    public void deletePersonTest() {
        // GIVEN




        // WHEN
        boolean value = fireStationRepository.deleteFireStationByAddressOrStation("1 rue de la paix", 1);

        // THEN
        assertTrue(value);

    }

    @Test
    public void deletePersonTest2() {
        // GIVEN




        // WHEN
        boolean value = fireStationRepository.deleteFireStationByAddressOrStation("2 rue de la paix", 2);

        // THEN
        assertTrue(value);

    }


    @Test
    public void getAllFireStationTest() {
        // GIVEN


        // WHEN
        List<FireStationDto> list = fireStationRepository.getAllFireStation();

        // THEN
        assertEquals(1, list.size());
    }

}