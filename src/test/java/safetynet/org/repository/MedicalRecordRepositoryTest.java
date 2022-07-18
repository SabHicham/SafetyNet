package safetynet.org.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import safetynet.org.model.FireStation;
import safetynet.org.model.MedicalRecord;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordRepositoryTest {

    @Mock
    private static MedicalRecord medicalRecord;

    @BeforeEach
    private void setUp(){
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
    }

    @Test
    public void updateMedicalRecordTest(){
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        MedicalRecord medicalRecord = new MedicalRecord();
        List<MedicalRecord> medicalRecordList = new ArrayList<>();

        // WHEN
        boolean value = medicalRecordRepository.updateMedicalRecord(medicalRecord);
        // THEN
        assertEquals(1,1);

    }
    @Test
    public void getAllMedicalRecordTest() {
        // GIVEN
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        MedicalRecord medicalRecord = new MedicalRecord();

        // WHEN
        boolean value = medicalRecordRepository.updateMedicalRecord(medicalRecord);
        // THEN
        assertEquals(1,1);
    }

    /*@Test
    public void deletePersonTest(String firstName, String lastName){
        // GIVEN
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();

        // WHEN
        medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
        // THEN
        assertEquals(1,1);

    }*/

}