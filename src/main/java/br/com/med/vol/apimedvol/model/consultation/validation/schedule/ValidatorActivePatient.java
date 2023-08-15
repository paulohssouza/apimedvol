package br.com.med.vol.apimedvol.model.consultation.validation.schedule;

import br.com.med.vol.apimedvol.model.ValidationException;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import br.com.med.vol.apimedvol.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorActivePatient implements ValidatorAppointmentScheduling {
    @Autowired
    private PatientRepository patientRepository;

    public void validate(SchedulingConsultationData schedulingConsultationData) {
        var patientActive = patientRepository.findActiveById(schedulingConsultationData.patientID());

        if(!patientActive) {
            throw new ValidationException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}
