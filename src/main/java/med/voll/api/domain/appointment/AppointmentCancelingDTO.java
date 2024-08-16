package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancelingDTO(
        @NotNull
        Long idAppointment,

        @NotNull
        ReasonCanceling reason
) {
}
