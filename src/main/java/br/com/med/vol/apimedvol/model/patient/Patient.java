package br.com.med.vol.apimedvol.model.patient;

import br.com.med.vol.apimedvol.model.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Patient")
@Table(name = "patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Address address;
    private boolean active;

    public Patient(PatientRegistrationData patientRegistrationData) {
        this.name = patientRegistrationData.name();
        this.email = patientRegistrationData.email();
        this.phone = patientRegistrationData.phone();
        this.cpf = patientRegistrationData.cpf();
        this.address = new Address(patientRegistrationData.address());
        this.active = true;
    }

    public void updateData(PatientUpdateData patientUpdateData) {
        if(patientUpdateData.name() != null && !patientUpdateData.name().isEmpty())
            this.name = patientUpdateData.name();
        if(patientUpdateData.phone() != null && !patientUpdateData.phone().isEmpty())
            this.phone = patientUpdateData.phone();
        if(patientUpdateData.address() != null)
            this.address.updateData(patientUpdateData.address());
    }

    public void inactive() {
        this.active = false;
    }
}
