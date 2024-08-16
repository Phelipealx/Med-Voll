package med.voll.api.domain.appointment.validation.scheduling;

import med.voll.api.domain.appointment.AppointmentException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidateClinicOpeningHours implements AppointmentSchedulingValidator {
    @Override
    public void validate(AppointmentSchedulingDTO appointmentSchedulingDTO) {
        var appointmentDate = appointmentSchedulingDTO.date();

        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpening = appointmentDate.getHour() < 7;
        var afterClinicClosing = appointmentDate.getHour() > 18;
        if (sunday || beforeClinicOpening || afterClinicClosing) {
            throw new AppointmentException("Appointment outside clinic opening hours!");
        }
    }
}
