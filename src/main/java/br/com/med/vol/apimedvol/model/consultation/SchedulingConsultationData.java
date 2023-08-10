package br.com.med.vol.apimedvol.model.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SchedulingConsultationData(
        @NotNull
        Long doctorID,
        @NotNull
        Long patientID,
        @NotNull
        @Future
        LocalDateTime dateTime
) {
}
