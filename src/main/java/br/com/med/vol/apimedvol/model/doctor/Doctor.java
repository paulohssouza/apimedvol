package br.com.med.vol.apimedvol.model.doctor;

import br.com.med.vol.apimedvol.model.address.Address;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Doctor")
@Table(name = "doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
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
    private boolean active;

    public Doctor(DoctorRegistrationData doctorRegistrationData) {
        this.name = doctorRegistrationData.name();
        this.email = doctorRegistrationData.email();
        this.phone = doctorRegistrationData.phone();
        this.crm = doctorRegistrationData.crm();
        this.specialty = doctorRegistrationData.specialty();
        this.address = new Address(doctorRegistrationData.address());
        this.active = true;
    }

    public void updateData(DoctorUpdateData doctorUpdateData) {
        if(doctorUpdateData.name() != null && !doctorUpdateData.name().isEmpty())
            this.name = doctorUpdateData.name();
        if(doctorUpdateData.phone() != null && !doctorUpdateData.phone().isEmpty())
            this.phone = doctorUpdateData.phone();
        if(doctorUpdateData.address() != null)
            this.address.updateData(doctorUpdateData.address());
    }

    public void inactive() {
        this.active = false;
    }
}
