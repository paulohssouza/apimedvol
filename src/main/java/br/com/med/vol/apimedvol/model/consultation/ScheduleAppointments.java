package br.com.med.vol.apimedvol.model.consultation;

import br.com.med.vol.apimedvol.model.ValidationException;
import br.com.med.vol.apimedvol.model.doctor.Doctor;
import br.com.med.vol.apimedvol.repository.ConsultationRepository;
import br.com.med.vol.apimedvol.repository.DoctorRepository;
import br.com.med.vol.apimedvol.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleAppointments {
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    public void schedule(SchedulingConsultationData schedulingConsultationData){
        if(!patientRepository.existsById(schedulingConsultationData.patientID())) {
            throw new ValidationException("Id do paciente informado não existe.");
        }
        if(schedulingConsultationData.doctorID() != null &&
                !doctorRepository.existsById(schedulingConsultationData.doctorID())) {
            throw new ValidationException("Id do médico informado não existe.");
        }

        var doctor = selectDoctor(schedulingConsultationData);
        var patient = patientRepository.getReferenceById(schedulingConsultationData.patientID());
        var consultation = new Consultation(null, doctor, patient, schedulingConsultationData.dateTime(), null);
        consultationRepository.save(consultation);
    }

    public void cancel(CancelConsultationData cancelConsultationData) {
        if(!consultationRepository.existsById(cancelConsultationData.consultationID())) {
            throw new ValidationException("Não existe consulta agendada com este ID");
        }
        var consultation = consultationRepository.getReferenceById(cancelConsultationData.consultationID());
        consultation.cancel(cancelConsultationData.reason());
    }

    private Doctor selectDoctor(SchedulingConsultationData schedulingConsultationData) {
        if(schedulingConsultationData.doctorID() != null) {
            return doctorRepository.getReferenceById(schedulingConsultationData.doctorID());
        }
        if(schedulingConsultationData.specialty() == null) {
            throw new ValidationException("Especialidade é obrigatória quando médico não for escolhido.");
        }
        return doctorRepository.chooseFreeRandomDoctor(schedulingConsultationData.specialty(),
                schedulingConsultationData.dateTime());
    }
}
