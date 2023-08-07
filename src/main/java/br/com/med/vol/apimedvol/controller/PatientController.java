package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.model.patient.Patient;
import br.com.med.vol.apimedvol.model.patient.PatientPublicData;
import br.com.med.vol.apimedvol.model.patient.PatientRegistrationData;
import br.com.med.vol.apimedvol.model.patient.PatientUpdateData;
import br.com.med.vol.apimedvol.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;
    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid PatientRegistrationData patientRegistrationData) {
        patientRepository.save(new Patient(patientRegistrationData));
    }

    @GetMapping
    public Page<PatientPublicData> patientListPublicData(
            @PageableDefault(size = 10, sort = {"name"})Pageable pageable
            ) {
        return patientRepository.findAllByActiveTrue(pageable).map(PatientPublicData::new);
    }

    @PutMapping
    @Transactional
    public void patientUpdate(@RequestBody @Valid PatientUpdateData patientUpdateData) {
        var patient = patientRepository.getReferenceById(patientUpdateData.id());
        patient.updateData(patientUpdateData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.inactive();
    }
}
