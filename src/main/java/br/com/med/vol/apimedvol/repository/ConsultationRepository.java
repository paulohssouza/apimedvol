package br.com.med.vol.apimedvol.repository;

import br.com.med.vol.apimedvol.model.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;


public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsByDoctorIdAndDate(Long doctor_id, LocalDateTime date_time);

    Boolean existsByPatientIdAndDateBetween(Long patient_id, LocalDateTime date_time, LocalDateTime date_time2);
}
