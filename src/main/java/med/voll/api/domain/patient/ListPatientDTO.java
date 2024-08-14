package med.voll.api.domain.patient;

public record ListPatientDTO(Long id, String name, String email, String cpf) {
    public ListPatientDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
