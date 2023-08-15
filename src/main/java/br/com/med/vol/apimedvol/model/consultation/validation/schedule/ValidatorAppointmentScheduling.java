package br.com.med.vol.apimedvol.model.consultation.validation.schedule;

import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;

public interface ValidatorAppointmentScheduling {
    void validate(SchedulingConsultationData schedulingConsultationData);
}
