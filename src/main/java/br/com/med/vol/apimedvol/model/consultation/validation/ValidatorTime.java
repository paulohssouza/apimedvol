package br.com.med.vol.apimedvol.model.consultation.validation;

import br.com.med.vol.apimedvol.model.ValidationException;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorTime implements ValidatorAppointmentScheduling{

    public void validate(SchedulingConsultationData consultationData) {
        var consultationDate = consultationData.date();
        var now = LocalDateTime.now();
        var diferenceTime = Duration.between(now, consultationDate).toMinutes();

        if(diferenceTime < 30) {
            throw new ValidationException("Consulta deve ser agendad com antecedência mínima de 30 minutois");
        }
    }
}
