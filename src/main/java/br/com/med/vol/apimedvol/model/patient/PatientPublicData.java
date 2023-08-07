package br.com.med.vol.apimedvol.model.patient;

public record PatientPublicData(
        Long id,
        String name,
        String email,
        String cpf
) {
    public PatientPublicData(Patient patient) {
       this(
               patient.getId(),
               patient.getName(),
               patient.getEmail(),
               patient.getCpf()
       );
    }
}
