package safetynet.org.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.FireStation;
import safetynet.org.model.MedicalRecord;
import safetynet.org.model.Person;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordRepositoryTest {




    private  MedicalRecordRepository medicalRecordRepository;

    @BeforeEach
    private void setUp() {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("hicham");
        medicalRecord.setLastName("john");

        medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordRepository.addMedicalRecord(medicalRecord);
    }


    @Test
    public void updateMedicalRecordTest(){

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("hicham");
        medicalRecord.setLastName("john");

        // WHEN
        boolean value = medicalRecordRepository.updateMedicalRecord(medicalRecord);
        // THEN
        assertTrue(value);

    }
    @Test
    public void updateMedicalRecordTest2(){

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("hi");
        medicalRecord.setLastName("john");

        // WHEN
        boolean value = medicalRecordRepository.updateMedicalRecord(medicalRecord);
        // THEN
        assertFalse(value);

    }
    @Test
    public void getAllMedicalRecordTest() {
        // GIVEN



        // WHEN


        List<MedicalRecordDto> list = medicalRecordRepository.getAllMedicalRecord();
        // THEN
        assertEquals(1, list.size());
    }

    @Test
    public void deletePersonTest(){
        // GIVEN


        // WHEN
        boolean value = medicalRecordRepository.deleteMedicalRecord("hicham", "john");



        // THEN
        assertTrue(value);

    }

    @Test
    public void deletePersonTest2(){
        // GIVEN


        // WHEN
        boolean value = medicalRecordRepository.deleteMedicalRecord("m", "john");



        // THEN
        assertTrue(value);

    }

    @Test
    public void getMedicalRecordFromFirstAndLastNameTest() throws RessourceNotFoundException {

        assertNotNull(medicalRecordRepository.getMedicalRecordFromFirstAndLastName("hicham", "john"));
    }

    @Test
    public void getMedicalRecordFromFirstAndLastNameErrorTest() {

        assertThatThrownBy(() -> medicalRecordRepository.getMedicalRecordFromFirstAndLastName("hicham2", "john")).isInstanceOf(RessourceNotFoundException.class);
    }

}