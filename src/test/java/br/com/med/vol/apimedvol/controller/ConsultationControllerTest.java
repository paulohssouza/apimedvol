package br.com.med.vol.apimedvol.controller;

import br.com.med.vol.apimedvol.model.consultation.DetailingMedicalConsultationData;
import br.com.med.vol.apimedvol.model.consultation.ScheduleAppointments;
import br.com.med.vol.apimedvol.model.consultation.SchedulingConsultationData;
import br.com.med.vol.apimedvol.model.doctor.Specialty;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JacksonTester<SchedulingConsultationData> consultationDataJacksonTester;
    @Autowired
    private JacksonTester<DetailingMedicalConsultationData> medicalConsultationDataJacksonTester;
    @MockBean
    private ScheduleAppointments scheduleAppointments;

    @Test
    @DisplayName("Deveria devolver código http 400 quando as informações são inválidas.")
    @WithMockUser
    void scheduleConsultationScenario1() throws Exception {
        var response = mockMvc.perform(post("/consultation")).andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando as informações são válidas.")
    @WithMockUser
    void scheduleConsultationScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGIA;
        when(scheduleAppointments.schedule(any())).thenReturn(new DetailingMedicalConsultationData(
                null, 2l, 5l, date
        ));
        var response = mockMvc
                .perform(
                        post("/consultation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(consultationDataJacksonTester.write(
                                        new SchedulingConsultationData(
                                                2l,
                                                5l,
                                                date,
                                                specialty
                                        )
                                ).getJson())
                )
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonExpected = medicalConsultationDataJacksonTester.write(
                new DetailingMedicalConsultationData(
                        null,
                        2l,
                        5l,
                        date
                )
        ).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}