package br.com.med.vol.apimedvol.model.patient;

import br.com.med.vol.apimedvol.model.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateData(
        @NotNull(message = "{id.mandatory}")
        Long id,
        String name,
        String phone,
        AddressData address
) {
}
