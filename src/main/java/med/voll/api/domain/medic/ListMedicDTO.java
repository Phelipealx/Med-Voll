package med.voll.api.domain.medic;

public record ListMedicDTO(Long id, String name, String email, String crm, Specialty specialty) {

    public ListMedicDTO(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getEmail(), medic.getCrm(), medic.getSpecialty());
    }
}
