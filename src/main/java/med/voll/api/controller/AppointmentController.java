package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentCancelingDTO;
import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.appointment.AppointmentSchedulingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointments")
public class AppointmentController {
    @Autowired
    private AppointmentSchedule appointmentSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentSchedulingDTO appointmentSchedulingDTO) {
        var dto = appointmentSchedule.schedule(appointmentSchedulingDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentCancelingDTO appointmentCancelingDTO) {
        appointmentSchedule.cancel(appointmentCancelingDTO);
        return ResponseEntity.noContent().build();
    }

}
