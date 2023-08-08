package br.com.med.vol.apimedvol.model.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank(message = "{address.mandatory}")
        String address,
        @NotBlank(message = "{district.mandatory}")
        String district,
        @NotBlank(message = "{cep.mandatory}")
        @Pattern(regexp = "\\d{8}", message = "{cep.invalid}")
        String cep,
        @NotBlank(message = "{city.mandatory}")
        String city,
        @NotBlank(message = "{uf.mandatory}")
        String uf,
        String number,
        String complement) {
}
