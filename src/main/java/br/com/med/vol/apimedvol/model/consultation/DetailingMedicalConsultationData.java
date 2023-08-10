package br.com.med.vol.apimedvol.model.consultation;

import java.time.LocalDateTime;

public record DetailingMedicalConsultationData(
     Long id,
     Long doctorID,
     Long patientID,
     LocalDateTime dateTime
) {
}
