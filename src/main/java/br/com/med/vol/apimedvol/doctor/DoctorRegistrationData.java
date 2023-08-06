package br.com.med.vol.apimedvol.doctor;

import br.com.med.vol.apimedvol.address.AddressData;

public record DoctorRegistrationData(String name, String email, String crm,
                                     Specialty specialty, AddressData address) {
}
