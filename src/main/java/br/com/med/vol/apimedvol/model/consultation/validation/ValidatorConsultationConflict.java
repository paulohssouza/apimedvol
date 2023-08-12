package br.com.med.vol.apimedvol.model.consultation.validation;

import br.com.med.vol.apimedvol.model.ValidationException;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import br.com.med.vol.apimedvol.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorConsultationConflict implements ValidatorAppointmentScheduling{
    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(SchedulingConsultationData schedulingConsultationData) {
        var doctorHasConsultation = consultationRepository.existsBYDoctorIdAndData
                (schedulingConsultationData.doctorID(), schedulingConsultationData.dateTime());
        if(doctorHasConsultation) {
            throw new ValidationException("Médico já possui consulta agendada nesse mesmo horário");
        }
    }
}
