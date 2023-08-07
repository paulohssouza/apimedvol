package br.com.med.vol.apimedvol.model.doctor;

public record DoctorPublicData(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public DoctorPublicData(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpecialty()
        );
    }
}
