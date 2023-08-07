package br.com.med.vol.apimedvol.doctor;

public record DoctorPublicData(
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public DoctorPublicData(Doctor doctor) {
        this(
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpecialty()
        );
    }
}
