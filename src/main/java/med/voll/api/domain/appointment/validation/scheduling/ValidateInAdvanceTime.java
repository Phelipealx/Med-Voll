package med.voll.api.domain.appointment.validation.scheduling;

import med.voll.api.domain.appointment.AppointmentException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidateInAdvanceTimeScheduling")
public class ValidateInAdvanceTime implements AppointmentSchedulingValidator {

    public void validate(AppointmentSchedulingDTO appointmentSchedulingDTO) {
        var appointmentDate = appointmentSchedulingDTO.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new AppointmentException("Appoint must be scheduled at least 30 minutes in advance time!");
        }
    }
}
