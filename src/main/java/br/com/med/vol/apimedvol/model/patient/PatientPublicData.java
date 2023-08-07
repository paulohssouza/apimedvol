package br.com.med.vol.apimedvol.model.patient;

public record PatientPublicData(
        String name,
        String email,
        String cpf
) {
    public PatientPublicData(Patient patient) {
       this(
               patient.getName(),
               patient.getEmail(),
               patient.getCpf()
       );
    }
}
