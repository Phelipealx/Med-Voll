package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.DetailPatientDTO;
import med.voll.api.domain.medic.ListMedicDTO;
import med.voll.api.domain.patient.ListPatientDTO;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.RegisterPatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterPatientDTO patientDTO, UriComponentsBuilder uriComponentsBuilder) {
        var patient = new Patient(patientDTO);
        repository.save(patient);

        var uri = uriComponentsBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailPatientDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<ListPatientDTO>> list(@PageableDefault(size = 5, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(ListPatientDTO::new);
        return ResponseEntity.ok(page);
    }
}
