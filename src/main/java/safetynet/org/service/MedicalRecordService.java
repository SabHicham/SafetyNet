package safetynet.org.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safetynet.org.dto.MedicalRecordDto;
import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.MedicalRecord;

import safetynet.org.repository.MedicalRecordRepository;



import java.util.List;


@Slf4j
@Service
public class MedicalRecordService {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;



    public List<MedicalRecordDto> getAllMedicalRecord() {
        return medicalRecordRepository.getAllMedicalRecord();
    }


    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecordRepository.addMedicalRecord(medicalRecord);
    }

    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.updateMedicalRecord(medicalRecord);
    }

    public boolean deleteMedicalRecord(String firstName, String lastName) {
        return medicalRecordRepository.deleteMedicalRecord(firstName, lastName);
    }
    public MedicalRecordDto getMedicalRecordFromFirstAndLastName(String firstName, String lastName) throws RessourceNotFoundException {
        return medicalRecordRepository.getMedicalRecordFromFirstAndLastName(firstName, lastName);
    }
}