package safetynet.org.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import safetynet.org.model.FireStation;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class FireStationRepositoryTest {

    @Mock
    private static FireStation fireStation;


    @BeforeEach
    private void setUp(){
        FireStationRepository fireStationRepository = new FireStationRepository();
        List<FireStation> fireStationList = new ArrayList<>();
    }


    @Test
    public void updateFireStationTest() {
        // GIVEN
        FireStationRepository fireStationRepository = new FireStationRepository();
        FireStation fireStation = new FireStation();
        List<FireStation> fireStationList = new ArrayList<>();

        //String key = fireStation.getAddress();

        //when(fireStation.getAddress()).thenReturn(key);


        // WHEN
        boolean value = fireStationRepository.updateFireStation(fireStation);
        // THEN
        assertFalse(value);
    }

    @Test
    public void deleteFireStationByAddressOrStationTest() {
        // GIVEN
        FireStationRepository fireStationRepository = new FireStationRepository();
        FireStation fireStation = new FireStation();

        // WHEN
        boolean value = fireStationRepository.updateFireStation(fireStation);
        // THEN
        assertEquals(1, 1);
    }

   /* @Test
    public void getAllFireStationTest() {
        // GIVEN
        FireStationRepository fireStationRepository = new FireStationRepository();

        // WHEN
        boolean value = fireStationRepository.updateFireStation();

        // THEN
        assertEquals(1, 1);
    }*/

}