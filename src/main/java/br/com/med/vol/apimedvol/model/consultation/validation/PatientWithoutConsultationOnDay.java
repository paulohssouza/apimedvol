package br.com.med.vol.apimedvol.model.consultation.validation;

import br.com.med.vol.apimedvol.model.ValidationException;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import br.com.med.vol.apimedvol.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutConsultationOnDay implements ValidatorAppointmentScheduling{
    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(SchedulingConsultationData schedulingConsultationData) {
        var firstHour = schedulingConsultationData.date().withHour(7);
        var lastHour = schedulingConsultationData.date().withHour(18);
        var patientHasConsultation = consultationRepository.existsByPatientIdAndDateBetween(
                schedulingConsultationData.patientID(), firstHour, lastHour);
        if(patientHasConsultation) {
            throw new ValidationException("Paciente já possui uma consulta agendada neste dia.");
        }
    }
}
