package med.voll.api.domain.appointment.validation.scheduling;

import med.voll.api.domain.appointment.AppointmentException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import med.voll.api.domain.medic.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActiveMedic implements AppointmentSchedulingValidator {
    @Autowired
    private MedicRepository repository;

    @Override
    public void validate(AppointmentSchedulingDTO appointmentSchedulingDTO) {

        if (appointmentSchedulingDTO.idMedic() == null) {
            return;
        }

        var isMedicActive = repository.findActiveById(appointmentSchedulingDTO.idMedic());
        if (!isMedicActive) {
            throw new AppointmentException("Appointment cannot be scheduled with an excluded medic!");
        }
    }
}
