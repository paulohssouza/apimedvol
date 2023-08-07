package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.model.patient.Patient;
import br.com.med.vol.apimedvol.model.patient.PatientRegistrationData;
import br.com.med.vol.apimedvol.model.patient.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
