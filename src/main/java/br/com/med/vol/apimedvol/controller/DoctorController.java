package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.model.doctor.*;
import br.com.med.vol.apimedvol.repository.DoctorRepository;
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
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @PostMapping
    @Transactional
    public ResponseEntity insert(
            @RequestBody
            @Valid
            DoctorRegistrationData doctorRegistrationData,
            UriComponentsBuilder uriComponentsBuilder) {
        var doctor = new Doctor(doctorRegistrationData);
        doctorRepository.save(doctor);
        var uri = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailedMedicalData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorPublicData>> doctorListPublicData(
            @PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = doctorRepository.findAllByActiveTrue(pageable).map(DoctorPublicData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity doctorUpdate(@RequestBody @Valid DoctorUpdateData doctorUpdateData) {
        var doctor = doctorRepository.getReferenceById(doctorUpdateData.id());
        doctor.updateData(doctorUpdateData);
        return ResponseEntity.ok(new DetailedMedicalData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.inactive();
        return ResponseEntity.noContent().build();
    }
}
