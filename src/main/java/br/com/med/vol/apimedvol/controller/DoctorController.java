package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.doctor.Doctor;
import br.com.med.vol.apimedvol.doctor.DoctorRegistrationData;
import br.com.med.vol.apimedvol.doctor.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    @Transactional
    public void insert(@RequestBody @Valid DoctorRegistrationData doctorRegistrationData) {
        doctorRepository.save(new Doctor(doctorRegistrationData));
    }
}
