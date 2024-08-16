package med.voll.api.domain.appointment;

import med.voll.api.domain.appointment.validation.canceling.AppointmentCancelingValidator;
import med.voll.api.domain.appointment.validation.scheduling.AppointmentSchedulingValidator;
import med.voll.api.domain.medic.Medic;
import med.voll.api.domain.medic.MedicRepository;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentSchedulingValidator> appointmentSchedulingValidatorList;

    @Autowired
    private List<AppointmentCancelingValidator> appointmentCancelingValidatorsList;


    public DetailAppointmentScheduleDTO schedule(AppointmentSchedulingDTO appointmentSchedulingDTO) {
        if (!patientRepository.existsById(appointmentSchedulingDTO.idPatient())) {
            throw new AppointmentException("Patient ID does not exist!");
        }

        if (appointmentSchedulingDTO.idMedic() != null && !medicRepository.existsById(appointmentSchedulingDTO.idMedic())) {
            throw new AppointmentException("Medic ID does not exist!");
        }

        appointmentSchedulingValidatorList.forEach(v -> v.validate(appointmentSchedulingDTO));

        var patient = patientRepository.getReferenceById(appointmentSchedulingDTO.idPatient());
        var medic = chooseMedic(appointmentSchedulingDTO);
        if (medic == null) {
            throw new AppointmentException("Does not exist medic available in this date!");
        }

        var appointment = new Appointment(null, medic, patient, appointmentSchedulingDTO.date(), null);
        appointmentRepository.save(appointment);

        return new DetailAppointmentScheduleDTO(appointment);
    }

    private Medic chooseMedic(AppointmentSchedulingDTO appointmentSchedulingDTO) {
        if (appointmentSchedulingDTO.idMedic() != null) {
            return medicRepository.getReferenceById(appointmentSchedulingDTO.idMedic());
        }

        if (appointmentSchedulingDTO.specialty() == null) {
            throw new AppointmentException("Specialty is required when medic does not chosen!");
        }

        return medicRepository.chooseRandomMedicAvailableInDate(appointmentSchedulingDTO.specialty(), appointmentSchedulingDTO.date());
    }

    public void cancel(AppointmentCancelingDTO appointmentCancelingDTO) {
        if (!appointmentRepository.existsById(appointmentCancelingDTO.idAppointment())) {
            throw new AppointmentException("Appointment ID does not exist!");
        }

        appointmentCancelingValidatorsList.forEach(v -> v.validate(appointmentCancelingDTO));

        var appointment = appointmentRepository.getReferenceById(appointmentCancelingDTO.idAppointment());
        appointment.cancel(appointmentCancelingDTO.reason());
    }
}
