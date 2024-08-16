package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailAppointmentScheduleDTO(Long id, Long idMedic, Long idPatient, LocalDateTime date) {
    public DetailAppointmentScheduleDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getMedic().getId(), appointment.getPatient().getId(), appointment.getDate());

    }
}
