package med.voll.api.controller;

import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import med.voll.api.domain.appointment.DetailAppointmentScheduleDTO;
import med.voll.api.domain.medic.Specialty;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AppointmentSchedulingDTO> appointmentSchedulingDTOJacksonTester;

    @Autowired
    private JacksonTester<DetailAppointmentScheduleDTO> detailAppointmentScheduleDTOJacksonTester;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Should get 400 status code when have invalid data.")
    void scheduleScenarioOne() throws Exception {
        var response = mockMvc.perform(post("/appointments")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should get 200 status code when have valid data.")
    void scheduleScenarioOneTwo() throws Exception {
        var date = LocalDateTime.now().withHour(10).plusDays(1);
        var specialty = Specialty.CARDIOLOGY;

        var detailAppointmentScheduleDTO = new DetailAppointmentScheduleDTO(null, 2L, 5L, date);
        when(appointmentSchedule.schedule(any())).thenReturn(detailAppointmentScheduleDTO);

        var response = mockMvc
                .perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(appointmentSchedulingDTOJacksonTester
                                .write(
                                        new AppointmentSchedulingDTO(2L, 5L, date, specialty)
                                )
                                .getJson()))
                .andReturn()
                .getResponse();

        var jsonExpected = detailAppointmentScheduleDTOJacksonTester.write(
                detailAppointmentScheduleDTO
        ).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

    @Test
    void cancel() {
    }
}