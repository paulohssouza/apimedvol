package br.com.med.vol.apimedvol.model.doctor;

import br.com.med.vol.apimedvol.model.address.Address;

public record DoctorDetailedData(
        Long id,
        String name,
        String email,
        String crm,
        String phone,
        Specialty specialty,
        Address address
) {
    public DoctorDetailedData(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getPhone(),
                doctor.getSpecialty(),
                doctor.getAddress()
        );
    }
}
