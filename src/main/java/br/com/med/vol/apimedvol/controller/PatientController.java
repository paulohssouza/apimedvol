package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.patient.PatientRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @PostMapping
    public void insert(@RequestBody PatientRegistrationData patientRegistrationData) {
        System.out.println(patientRegistrationData);
    }
}
