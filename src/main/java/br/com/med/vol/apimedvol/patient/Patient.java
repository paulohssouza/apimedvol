package br.com.med.vol.apimedvol.patient;

import br.com.med.vol.apimedvol.address.Address;
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

    public Patient(PatientRegistrationData patientRegistrationData) {
        this.name = patientRegistrationData.name();
        this.email = patientRegistrationData.email();
        this.phone = patientRegistrationData.phone();
        this.cpf = patientRegistrationData.cpf();
        this.address = new Address(patientRegistrationData.address());
    }
}
