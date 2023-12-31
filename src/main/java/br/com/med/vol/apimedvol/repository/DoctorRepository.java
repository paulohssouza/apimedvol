package br.com.med.vol.apimedvol.repository;

import br.com.med.vol.apimedvol.model.doctor.Doctor;
import br.com.med.vol.apimedvol.model.doctor.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select d from Doctor d
            where
            d.active = true
            and
            d.specialty = :specialty
            and
            d.id not in(
                select c.doctor.id from Consultation c
                where
                c.date = :localDateTime
            )
            order by rand()
            limit 1
            """)
    Doctor chooseFreeRandomDoctor(Specialty specialty, LocalDateTime localDateTime);

    @Query("""
            select d.active from Doctor d
            where d.id = :doctorID
            """)
    Boolean findActiveById(Long doctorID);
}
