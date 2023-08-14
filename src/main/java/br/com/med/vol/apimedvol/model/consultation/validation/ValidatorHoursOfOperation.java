package br.com.med.vol.apimedvol.model.consultation.validation;

import br.com.med.vol.apimedvol.model.ValidationException;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidatorHoursOfOperation implements ValidatorAppointmentScheduling{

    public void validate(SchedulingConsultationData schedulingConsultationData) {
        var consultationDate = schedulingConsultationData.date();
        var sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpen = consultationDate.getHour() < 7;
        var afterClose = consultationDate.getHour() > 18;

        if(sunday || beforeOpen || afterClose) {
            throw new ValidationException("O horário deve estar dentro do intervalo de atendimento da clínica.");
        }
    }
}
