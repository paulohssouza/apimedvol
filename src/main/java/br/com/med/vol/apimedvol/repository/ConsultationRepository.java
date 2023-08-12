package br.com.med.vol.apimedvol.repository;

import br.com.med.vol.apimedvol.model.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsBYDoctorIdAndData(Long doctorId, LocalDateTime localDateTime);

    Boolean existsByPatientIdAndDataBetween(Long aLong, LocalDateTime firstHour, LocalDateTime lastHour);
}
