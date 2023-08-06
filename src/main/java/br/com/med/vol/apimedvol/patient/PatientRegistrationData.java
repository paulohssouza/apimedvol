package br.com.med.vol.apimedvol.patient;

import br.com.med.vol.apimedvol.address.AddressData;

public record PatientRegistrationData(String name, String email, String phone,
                                      String cpf, AddressData address) {
}
