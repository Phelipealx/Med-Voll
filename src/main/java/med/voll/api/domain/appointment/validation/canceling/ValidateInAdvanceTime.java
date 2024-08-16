package med.voll.api.domain.appointment.validation.canceling;

import med.voll.api.domain.appointment.AppointmentCancelingDTO;
import med.voll.api.domain.appointment.AppointmentException;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidateInAdvanceTimeCanceling")
public class ValidateInAdvanceTime implements AppointmentCancelingValidator{
    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentCancelingDTO appointmentCancelingDTO) {
        var appointment = repository.getReferenceById(appointmentCancelingDTO.idAppointment());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new AppointmentException("Appointment can only be canceled at least 24 hours in advance!");
        }
    }
}
