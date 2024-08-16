package med.voll.api.domain.appointment.validation.scheduling;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.AppointmentException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateMedicWithAnotherAppointmentAtSameTime implements AppointmentSchedulingValidator {
    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validate(AppointmentSchedulingDTO appointmentSchedulingDTO) {
        var isMedicHasAnotherAppointmentAtSameTime = repository.existsByMedicIdAndDateAndReasonCancelingIsNull(appointmentSchedulingDTO.idMedic(),
                appointmentSchedulingDTO.date());
        if (isMedicHasAnotherAppointmentAtSameTime) {
            throw new AppointmentException("Medic already has another appointment scheduled at the same time!");
        }
    }
}
