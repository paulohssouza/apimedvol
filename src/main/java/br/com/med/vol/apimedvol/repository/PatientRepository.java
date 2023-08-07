package br.com.med.vol.apimedvol.repository;

import br.com.med.vol.apimedvol.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
