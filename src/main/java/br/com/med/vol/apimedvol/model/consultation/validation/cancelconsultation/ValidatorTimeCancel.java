package br.com.med.vol.apimedvol.model.consultation.validation.cancelconsultation;

import br.com.med.vol.apimedvol.model.ValidationException;
import br.com.med.vol.apimedvol.model.consultation.CancelConsultationData;
import br.com.med.vol.apimedvol.repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorTimeCancel implements ValidatorCancelConsultation {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Override
    public void validate(CancelConsultationData cancelConsultationData) {
        var consultationDate = consultationRepository.getReferenceById(cancelConsultationData.consultationID());
        var now = LocalDateTime.now();
        var diferenceTime = Duration.between(now, consultationDate.getDate()).toHours();

        if(diferenceTime < 24) {
            throw new ValidationException("Cancelamento só é permitido com 24hs de antecedência.");
        }
    }
}
