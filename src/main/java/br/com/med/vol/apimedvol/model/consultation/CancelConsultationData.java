package br.com.med.vol.apimedvol.model.consultation;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CancelConsultationData(
        @NotNull
        Long consultationID,
        @NotNull
        Reason reason
) {
}
