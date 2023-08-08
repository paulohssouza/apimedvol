package br.com.med.vol.apimedvol.model.patient;

import br.com.med.vol.apimedvol.model.address.Address;

public record DetailedPatientData(
        Long id,
        String name,
        String email,
        String phone,
        String cpf,
        Address address
) {
    public DetailedPatientData(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getCpf(),
                patient.getAddress()
        );
    }
}
