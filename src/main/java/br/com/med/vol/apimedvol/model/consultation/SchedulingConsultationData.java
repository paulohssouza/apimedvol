package br.com.med.vol.apimedvol.model.consultation;

import br.com.med.vol.apimedvol.model.doctor.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SchedulingConsultationData(
        Long doctorID,
        @NotNull
        Long patientID,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty
) {
}
