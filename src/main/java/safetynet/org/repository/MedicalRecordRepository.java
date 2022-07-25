package safetynet.org.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import safetynet.org.dto.MedicalRecordDto;

import safetynet.org.exception.RessourceNotFoundException;
import safetynet.org.model.MedicalRecord;



import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class MedicalRecordRepository {

    private List<MedicalRecord> medicalRecordList = new ArrayList<>();

    public Boolean addMedicalRecord(MedicalRecord medicalRecord){

        return medicalRecordList.add(medicalRecord);
    }
    public List<MedicalRecordDto> getAllMedicalRecord() {
        return medicalRecordList.stream().map(medicalRecord -> new MedicalRecordDto(medicalRecord.getFirstName(),
                medicalRecord.getLastName(), medicalRecord.getBirthdate(), medicalRecord.getMedications(),
                medicalRecord.getAllergies())).collect(Collectors.toList());

    }



    public boolean updateMedicalRecord(MedicalRecord medicalRecord) {
        try {
            int i=0;
            for(MedicalRecord m:medicalRecordList){
                String key = medicalRecord.getLastName()+"."+medicalRecord.getFirstName();
                String key1 = m.getLastName()+"."+m.getFirstName();
                log.info(">>> ERROR: {} {}", key, key1);
                if (key.equalsIgnoreCase(key1)){
                    medicalRecordList.get(i).setBirthdate(medicalRecord.getBirthdate());
                    medicalRecordList.get(i).setMedications(medicalRecord.getMedications());
                    medicalRecordList.get(i).setAllergies(medicalRecord.getAllergies());

                    return true;
                }
                i++;
            }
    }
        catch (Exception e){
            return false;
        }
        return false;

}

    public boolean deleteMedicalRecord(String firstName, String lastName) {
        medicalRecordList = medicalRecordList.stream().filter((medicalRecord)
                -> !Objects.equals(medicalRecord.getFirstName(), firstName)&& !Objects.equals (medicalRecord.getLastName(), lastName)).collect(Collectors.toList());
        return true;
    }

    public MedicalRecordDto getMedicalRecordFromFirstAndLastName(String firstName, String lastName) throws RessourceNotFoundException {
        Optional<MedicalRecord> medicalRecordOptional  = medicalRecordList.stream().filter((medicalRecord)
                -> Objects.equals(medicalRecord.getFirstName(), firstName)&& Objects.equals (medicalRecord.getLastName(), lastName))
                .findFirst();
        if (medicalRecordOptional.isPresent()){
            final MedicalRecord mr = medicalRecordOptional.get();
            return  new MedicalRecordDto(mr.getFirstName(),
                    mr.getLastName(), mr.getBirthdate(), mr.getMedications(),
                    mr.getAllergies());
        }
        String error = String.format("Aucune medicalRecord n'a été trouvée pour %s %s",firstName, lastName );
        throw new RessourceNotFoundException(error);
    }
}
