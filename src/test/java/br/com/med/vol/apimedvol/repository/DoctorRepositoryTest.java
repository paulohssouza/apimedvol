package br.com.med.vol.apimedvol.repository;

import br.com.med.vol.apimedvol.model.address.AddressData;
import br.com.med.vol.apimedvol.model.consultation.Consultation;
import br.com.med.vol.apimedvol.model.doctor.Doctor;
import br.com.med.vol.apimedvol.model.doctor.DoctorRegistrationData;
import br.com.med.vol.apimedvol.model.doctor.Specialty;
import br.com.med.vol.apimedvol.model.patient.Patient;
import br.com.med.vol.apimedvol.model.patient.PatientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Médico aleatório que já tem uma consulta, deve devolver null.")
    void chooseFreeRandomDoctorScenario1() {
        var nextMonday10Hours = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("João", "joão@volmed.com", "235789", Specialty.CARDIOLOGIA);
        var patient = registerPatient("José", "jose@gmail.com", "23355566697");
        registerConsultation(doctor, patient, nextMonday10Hours);
        var doctorFree = doctorRepository.chooseFreeRandomDoctor(Specialty.CARDIOLOGIA, nextMonday10Hours);
        assertThat(doctorFree).isNull();
        System.out.println(doctorFree);
    }

    @Test
    @DisplayName("Devolve médico quando estiver disponível na data.")
    void chooseFreeRandomDoctorScenario2() {
        var nextMonday10Hours = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("João", "joão@volmed.com", "235789", Specialty.CARDIOLOGIA);
        var doctorFree = doctorRepository.chooseFreeRandomDoctor(Specialty.CARDIOLOGIA, nextMonday10Hours);
        assertThat(doctorFree).isEqualTo(doctor);
        System.out.println(doctorFree);
    }

    private void registerConsultation(Doctor doctor, Patient patient, LocalDateTime localDateTime) {
        testEntityManager.persist(new Consultation(null, doctor, patient, localDateTime, null, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty){
        var doctor = new Doctor(doctorData(name, email, crm, specialty));
        testEntityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf){
        var patient = new Patient(patientData(name, email, cpf));
        testEntityManager.persist(patient);
        return patient;
    }

    private DoctorRegistrationData doctorData(String name, String email, String crm, Specialty specialty) {
        return new DoctorRegistrationData(
                name,
                email,
                "61999729982",
                crm,
                specialty,
                addressData()
        );
    }

    private PatientRegistrationData patientData(String name, String email, String cpf) {
        return new PatientRegistrationData(
                name,
                email,
                "61999729982",
                cpf,
                addressData()
        );
    }

    private AddressData addressData() {
        return new AddressData(
                "Rua xpto",
                "Bairro",
                "00000000",
                "Cidade",
                "BR",
                null,
                null
        );
    }
}