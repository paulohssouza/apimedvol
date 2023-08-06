package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.doctor.Doctor;
import br.com.med.vol.apimedvol.doctor.DoctorRegistrationData;
import br.com.med.vol.apimedvol.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void insert(@RequestBody DoctorRegistrationData doctorRegistrationData) {
        doctorRepository.save(new Doctor(doctorRegistrationData));
    }
}
