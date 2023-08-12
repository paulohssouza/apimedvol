package br.com.med.vol.apimedvol.model.consultation.validation;

import br.com.med.vol.apimedvol.model.ValidationException;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import br.com.med.vol.apimedvol.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorActiveDoctor implements ValidatorAppointmentScheduling{
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(SchedulingConsultationData schedulingConsultationData) {
        if(schedulingConsultationData.doctorID() == null) {
            return;
        }

        var active = doctorRepository.findActiveById(schedulingConsultationData.doctorID());
        if(!active) {
            throw new ValidationException("Consulta não pode ser agendada, médico não atuante.");
        }
    }
}
