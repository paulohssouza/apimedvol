package br.com.med.vol.apimedvol.repository;

import br.com.med.vol.apimedvol.model.consultation.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
