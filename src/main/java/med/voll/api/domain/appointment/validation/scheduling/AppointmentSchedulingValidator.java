package med.voll.api.domain.appointment.validation.scheduling;

import med.voll.api.domain.appointment.AppointmentSchedulingDTO;

public interface AppointmentSchedulingValidator {
    void validate(AppointmentSchedulingDTO appointmentSchedulingDTO);
}
