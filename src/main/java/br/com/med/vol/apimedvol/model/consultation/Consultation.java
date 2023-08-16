package br.com.med.vol.apimedvol.model.consultation;

import br.com.med.vol.apimedvol.model.doctor.Doctor;
import br.com.med.vol.apimedvol.model.patient.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "consultation")
@Entity(name = "Consultation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "reason")
    private Reason reason;
    @Column(name = "status")
    private StatusConsultation statusConsultation;

    public void cancel(Reason reason) {
        this.reason = reason;
        this.statusConsultation = StatusConsultation.CANCELADO;
    }
}
