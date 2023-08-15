package br.com.med.vol.apimedvol.model.consultation;

import java.time.LocalDateTime;

public record DetailingMedicalConsultationData(
     Long id,
     Long doctorID,
     Long patientID,
     LocalDateTime date
) {
    public DetailingMedicalConsultationData(Consultation consultation) {
        this(
                consultation.getId(),
                consultation.getDoctor().getId(),
                consultation.getPatient().getId(),
                consultation.getDate()
        );
    }
}
