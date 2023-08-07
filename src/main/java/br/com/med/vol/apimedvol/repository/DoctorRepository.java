package br.com.med.vol.apimedvol.repository;

import br.com.med.vol.apimedvol.model.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
