package med.voll.api.domain;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.Patient;

public record DetailPatientDTO(Long id, String name, String email, String cpf, String phone, Address address) {

    public DetailPatientDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getPhone(), patient.getAddress());
    }
}
