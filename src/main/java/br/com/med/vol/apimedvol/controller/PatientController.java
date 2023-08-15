package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.model.doctor.DoctorDetailedData;
import br.com.med.vol.apimedvol.model.patient.*;
import br.com.med.vol.apimedvol.repository.PatientRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;
    @PostMapping
    @Transactional
    public ResponseEntity insert(
            @RequestBody
            @Valid
            PatientRegistrationData patientRegistrationData,
            UriComponentsBuilder uriComponentsBuilder) {
        var patient = new Patient(patientRegistrationData);
        patientRepository.save(patient);
        var uri = uriComponentsBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailedPatientData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientPublicData>> patientListPublicData(
            @PageableDefault(size = 10, sort = {"name"})Pageable pageable
            ) {
        var page = patientRepository.findAllByActiveTrue(pageable).map(PatientPublicData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity destailsPatientData(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailedPatientData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity patientUpdate(@RequestBody @Valid PatientUpdateData patientUpdateData) {
        var patient = patientRepository.getReferenceById(patientUpdateData.id());
        patient.updateData(patientUpdateData);
        return ResponseEntity.ok(new DetailedPatientData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.inactive();
        return ResponseEntity.noContent().build();
    }
}
