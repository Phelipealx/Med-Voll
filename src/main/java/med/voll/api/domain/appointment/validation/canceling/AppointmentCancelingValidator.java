package med.voll.api.domain.appointment.validation.canceling;

import med.voll.api.domain.appointment.AppointmentCancelingDTO;

public interface AppointmentCancelingValidator {
    void validate(AppointmentCancelingDTO appointmentCancelingDTO);
}
