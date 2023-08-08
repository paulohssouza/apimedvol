package br.com.med.vol.apimedvol.model.patient;

import br.com.med.vol.apimedvol.model.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientRegistrationData(
        @NotBlank(message = "{name.mandatory}")
        String name,
        @NotBlank(message = "{email.mandatory}")
        @Email(message = "{email.invalid}")
        String email,
        @NotBlank(message = "{phone.mandatory}")
        String phone,
        @NotBlank(message = "{cpf.mandatory}")
        @Pattern(regexp = "\\d{11}", message = "{cpf.invalid}")
        String cpf,
        @NotNull(message = "{address.mandatory}")
        @Valid
        AddressData address) {
}
