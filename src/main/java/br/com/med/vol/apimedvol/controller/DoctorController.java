package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.model.doctor.*;
import br.com.med.vol.apimedvol.repository.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<DoctorPublicData> doctorListPublicData(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return doctorRepository.findAll(pageable).map(DoctorPublicData::new);
    }

    @PutMapping
    @Transactional
    public void doctorUpdate(@RequestBody @Valid DoctorUpdateData doctorUpdateData) {
        var doctor = doctorRepository.getReferenceById(doctorUpdateData.id());
        doctor.updateData(doctorUpdateData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        doctorRepository.deleteById(id);
    }
}
