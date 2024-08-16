package med.voll.api.domain.appointment.validation.scheduling;

import med.voll.api.domain.appointment.AppointmentException;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActivePatient implements AppointmentSchedulingValidator {
    @Autowired
    private PatientRepository repository;

    @Override
    public void validate(AppointmentSchedulingDTO appointmentSchedulingDTO) {
        var isPatientActive = repository.findActiveById(appointmentSchedulingDTO.idPatient());
        if (!isPatientActive) {
            throw new AppointmentException("Appointment cannot be scheduled with an excluded patient!");
        }
    }
}
