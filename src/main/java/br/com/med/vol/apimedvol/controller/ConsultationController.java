package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.model.consultation.CancelConsultationData;
import br.com.med.vol.apimedvol.model.consultation.DetailingMedicalConsultationData;
import br.com.med.vol.apimedvol.model.consultation.ScheduleAppointments;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {
    @Autowired
    private ScheduleAppointments scheduleAppointments;
    @PostMapping
    @Transactional
    public ResponseEntity scheduleConsultation(@RequestBody @Valid SchedulingConsultationData schedulingAppointmentData) {
        var consultatioDetailedDTO = scheduleAppointments.schedule(schedulingAppointmentData);
        return ResponseEntity.ok(consultatioDetailedDTO);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelConsultation(@RequestBody @Valid CancelConsultationData cancelConsultationData) {
        var cancelConsultationDTO = scheduleAppointments.cancel(cancelConsultationData);
        return ResponseEntity.ok(cancelConsultationDTO);
    }
}
