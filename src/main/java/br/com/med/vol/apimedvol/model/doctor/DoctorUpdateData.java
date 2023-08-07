package br.com.med.vol.apimedvol.model.doctor;

import br.com.med.vol.apimedvol.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData address) {
}
