package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.doctor.DoctorRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @PostMapping
    public void insert(@RequestBody DoctorRegistrationData doctorRegistrationData) {
        System.out.println(doctorRegistrationData);
    }
}
