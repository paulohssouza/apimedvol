package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.model.consultation.CancelConsultationData;
import br.com.med.vol.apimedvol.model.consultation.DetailingMedicalConsultationData;
import br.com.med.vol.apimedvol.model.consultation.ScheduleAppointments;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {
    @Autowired
    private ScheduleAppointments scheduleAppointments;
    @PostMapping
    @Transactional
    public ResponseEntity scheduleConsultation(@RequestBody @Valid SchedulingConsultationData schedulingAppointmentData) {
        scheduleAppointments.schedule(schedulingAppointmentData);
        System.out.println(schedulingAppointmentData);
        return ResponseEntity.ok(new DetailingMedicalConsultationData(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelConsultation(@RequestBody @Valid CancelConsultationData cancelConsultationData) {
        System.out.println(cancelConsultationData);
        return ResponseEntity.ok().build();    }
}
