package br.com.med.vol.apimedvol.model.consultation;

import java.time.LocalDateTime;

public record DetailingCancelConsultation(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date,
        Reason reason
) {
    public DetailingCancelConsultation(Consultation consultation) {
        this(
                consultation.getId(),
                consultation.getDoctor().getId(),
                consultation.getPatient().getId(),
                consultation.getDate(),
                consultation.getReason()
        );
    }
}
