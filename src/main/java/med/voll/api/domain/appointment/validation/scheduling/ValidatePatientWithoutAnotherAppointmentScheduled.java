package med.voll.api.domain.appointment.validation.scheduling;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.AppointmentException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientWithoutAnotherAppointmentScheduled implements AppointmentSchedulingValidator {
    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentSchedulingDTO appointmentSchedulingDTO) {
        var firstTime = appointmentSchedulingDTO.date().withHour(7);
        var lastTime = appointmentSchedulingDTO.date().withHour(18);
        var isPatientHasAnotherAppointment = repository.existsByPatientIdAndDateBetween(appointmentSchedulingDTO.idPatient(),
                firstTime, lastTime);
        if (isPatientHasAnotherAppointment) {
            throw new AppointmentException("Patient already has an appointment scheduled that day!");
        }
    }
}
