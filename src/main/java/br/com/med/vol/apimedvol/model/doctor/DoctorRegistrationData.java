package br.com.med.vol.apimedvol.model.doctor;

import br.com.med.vol.apimedvol.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegistrationData(
        @NotBlank(message = "{name.mandatory}")
        String name,
        @NotBlank(message = "{email.mandatory}")
        @Email(message = "{email.invalid}")
        String email,
        @NotBlank(message = "{phone.mandatory}")
        String phone,
        @NotBlank(message = "{crm.mandatory}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalid}")
        String crm,
        @NotNull(message = "{specialty.mandatory}")
        Specialty specialty,
        @NotNull(message = "{address.mandatory}")
        @Valid
        AddressData address) {
}
