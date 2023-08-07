package br.com.med.vol.apimedvol.doctor;

import br.com.med.vol.apimedvol.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Doctor")
@Table(name = "doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;

    public Doctor(DoctorRegistrationData doctorRegistrationData) {
        this.name = doctorRegistrationData.name();
        this.email = doctorRegistrationData.email();
        this.phone = doctorRegistrationData.phone();
        this.crm = doctorRegistrationData.crm();
        this.specialty = doctorRegistrationData.specialty();
        this.address = new Address(doctorRegistrationData.address());
    }
}
