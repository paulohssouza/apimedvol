package br.com.med.vol.apimedvol.repository;

import br.com.med.vol.apimedvol.model.doctor.Doctor;
import br.com.med.vol.apimedvol.model.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select p.active
            from Patient p
            where p.id = :patientID
            """)
    Boolean findActiveById(Long patientID);
}
